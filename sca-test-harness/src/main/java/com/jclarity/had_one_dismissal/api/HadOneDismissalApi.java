package com.jclarity.had_one_dismissal.api;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Suppliers;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.google.common.io.InputSupplier;

public class HadOneDismissalApi implements Closeable {

    private static Logger LOGGER = LoggerFactory.getLogger(HadOneDismissalApi.class);

    public static String HOST = System.getProperty("jclarity.hod.host", "127.0.0.1");

    private static String PORT = System.getProperty("jclarity.hod.port","8080");

    private static String URL = "http://" + HOST + ":" + PORT + "/had_one_dismissal/";

    private static String COMPANY_JOB_URL = URL + "companyandjob";
    private static String LOGIN_URL       = URL + "resources/j_spring_security_check";
    private static String LOGOUT_URL      = URL + "resources/j_spring_security_logout";
    private static String POPULATE_DB     = URL + "populate/index";

    private final BasicCookieStore cookies;

    private final Executor executor;

    private DefaultHttpClient httpClient;

    public HadOneDismissalApi() {
        this.cookies = new BasicCookieStore();
        httpClient = new DefaultHttpClient();
        this.executor = Executor.newInstance(httpClient)
                                 .cookieStore(cookies);
    }

    private static String companyAndJobByJobId(int id) {
        return COMPANY_JOB_URL + "/" + id;
    }

    public void createCompanyAndJob(String name, String title, int salaryLowerBound, int salaryUpperBound, String description) throws ClientProtocolException, IOException {
        LOGGER.debug("createCompanyAndJob");
        execute(Request .Post(COMPANY_JOB_URL)
                .bodyForm(new BasicNameValuePair("company.name", name),
                        new BasicNameValuePair("job.title", title),
                        new BasicNameValuePair("job.salaryLowerBound", Integer.toString(salaryLowerBound)),
                        new BasicNameValuePair("job.salaryUpperBound", Integer.toString(salaryUpperBound)),
                        new BasicNameValuePair("job.description", description))
              .socketTimeout(1000)
              .connectTimeout(1000));
    }

    public void deleteCompanyAndJobById(int id) throws ClientProtocolException, IOException {
        LOGGER.debug("deleteCompanyAndJobById");
        execute(Request.Delete(companyAndJobByJobId(id)));
    }

    public void login(String username, String password) throws ClientProtocolException, IOException {
        LOGGER.debug("login");
        Request request = Request.Post(LOGIN_URL)
                                 .bodyForm(new BasicNameValuePair("j_username", username),
                                           new BasicNameValuePair("j_password", password));

        execute(request);
    }

    public void logout() throws ClientProtocolException, IOException {
        LOGGER.debug("logout");
        execute(Request.Get(LOGOUT_URL));
    }

    public void populateDb() throws ClientProtocolException, IOException {
        LOGGER.debug("populateDb");
        execute(Request.Get(POPULATE_DB));
    }

    private void execute(Request request) throws ClientProtocolException, IOException {
        request.addHeader("Connection", "keep-alive");
        executor.execute(request).discardContent();
    }

    @Override
    public void close() {
        httpClient.getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
        httpClient.getConnectionManager().shutdown();
        
        //force connection finalizers to be called
        System.gc();
    }

}