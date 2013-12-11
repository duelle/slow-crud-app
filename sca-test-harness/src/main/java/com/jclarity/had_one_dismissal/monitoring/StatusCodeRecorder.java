package com.jclarity.had_one_dismissal.monitoring;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multiset.Entry;

public class StatusCodeRecorder implements ResponseRecorder {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusCodeRecorder.class);
    private static final int REPORT_PERIOD = 100;

    private final Multiset<Integer> codeCounts;

    public StatusCodeRecorder() {
        codeCounts = ConcurrentHashMultiset.create();
    }

    @Override
    public void record(HttpResponse response) {
        Integer code = response.getStatusLine().getStatusCode();
        codeCounts.add(code);
        if (readyToReport()) {
            report();
        }
    }
    
    @Override
    public void report() {
        LOGGER.info("Status code counts:");
        for (Entry<Integer> code : codeCounts.entrySet()) {
            LOGGER.info(code.toString());
        }
    }

    private boolean readyToReport() {
        return (size() % REPORT_PERIOD) == 0;
    }

    private int size() {
        return codeCounts.size();
    }

}
