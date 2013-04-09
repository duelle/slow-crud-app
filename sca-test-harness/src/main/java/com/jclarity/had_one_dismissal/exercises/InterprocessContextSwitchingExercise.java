package com.jclarity.had_one_dismissal.exercises;

import static com.jclarity.crud_common.api.Database.EXTERNAL_DAEMON;
import static com.jclarity.crud_common.api.Database.IN_MEMORY;
import static java.lang.Integer.parseInt;


public class InterprocessContextSwitchingExercise extends CompanyAndJobExercise {

    public InterprocessContextSwitchingExercise(String enqueueJobsCount, String deleteJobsCount) {
        super(parseInt(enqueueJobsCount), parseInt(deleteJobsCount));
    }

    @Override
    public void init() {
        performanceProblemsMXBean.setDeadlockEnabled(false);
        performanceProblemsMXBean.setDatabaseType(EXTERNAL_DAEMON);
    }

    @Override
    public void reset() {
        performanceProblemsMXBean.setDatabaseType(IN_MEMORY);
    }

}
