package com.jclarity.crud_common.api;

public interface PerformanceProblemsMXBean {

    public static final String ADDRESS = "com.jclarity.had_one_dismissal:type=PerformanceProblems";
    
    public static final boolean DEFAULT_SAVING_LOADED_DATA = true;
    public static final boolean DEFAULT_DEADLOCK_ENABLED = true;
    public static final boolean DEFAULT_SAVING_DB_QUERIES = true;
    public static final Database DEFAULT_DATABASE_TYPE = Database.IN_MEMORY;

    public boolean isDeadlockEnabled();

    public void setDeadlockEnabled(boolean enabled);

    public boolean isSavingDBQueries();

    public void setSavingDBQueries(boolean batching);

    public Database getDatabaseType();

    public void setDatabaseType(Database database);

    public String getRootLoggingLevel();

    public void setRootLoggingLevel(String level);

    public boolean isSavingLoadedData();

    public void setSavingLoadedData(boolean savingLoadedData);

}
