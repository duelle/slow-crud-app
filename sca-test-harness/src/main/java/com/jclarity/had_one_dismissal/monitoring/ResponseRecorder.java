package com.jclarity.had_one_dismissal.monitoring;

import org.apache.http.HttpResponse;

public interface ResponseRecorder {

    public void record(HttpResponse response);

    public void report();
}
