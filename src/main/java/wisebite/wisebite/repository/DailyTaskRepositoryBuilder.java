package wisebite.wisebite.repository;

import wisebite.wisebite.database.DailyTaskDAO;

public class DailyTaskRepositoryBuilder {
    private DailyTaskDAO dailyTaskDAO;

    public DailyTaskRepositoryBuilder setDailyTaskDAO(DailyTaskDAO dailyTaskDAO) {
        this.dailyTaskDAO = dailyTaskDAO;
        return this;
    }

    public DailyTaskRepository createDailyTaskRepository() {
        return new DailyTaskRepository(dailyTaskDAO);
    }
}