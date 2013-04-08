package com.jclarity.crud_common.api;

public interface PerformanceProblemsMXBean {

    public static final String ADDRESS = "com.jclarity.had_one_dismissal:type=PerformanceProblems";

    public boolean isDeadlockEnabled();

    public void setDeadlockEnabled(boolean enabled);

    public boolean isBatchingDBQueries();

    public void setBatchingDBQueries(boolean batching);

    public Database getDatabaseType();

    public void setDatabaseType(Database database);

}