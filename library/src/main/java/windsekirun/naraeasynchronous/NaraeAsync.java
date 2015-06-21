package windsekirun.naraeasynchronous;

import windsekirun.naraeasynchronous.util.BackgroundThreadExecutor;
import windsekirun.naraeasynchronous.util.CpuCoreInfo;
import windsekirun.naraeasynchronous.util.RandomAttributes;

/**
 * NaraeAsynchronous
 * Class: NaraeAsync
 * Created by WindSekirun on 15. 6. 22..
 */
public class NaraeAsync {
    private static final int DEFAULT_POOL_SIZE = CpuCoreInfo.getCoresCount() + 1;
    private static final String DEFAULT_TASK_TYPE = "Narae.MOE_" + RandomAttributes.getRandomTaskType();

    private int settingPoolSize = DEFAULT_POOL_SIZE;
    private String settingTaskType = DEFAULT_TASK_TYPE;

    Runnable runnable;

    public NaraeAsync(Runnable runnable) {
        this.runnable = runnable;
    }

    public NaraeAsync(Runnable runnable, int poolSize) {
        this.runnable = runnable;
        settingPoolSize = poolSize;
    }

    public NaraeAsync(Runnable runnable, String taskType) {
        this.runnable = runnable;
        settingTaskType = taskType;
    }

    public NaraeAsync(Runnable runnable, int poolSize, String taskType) {
        this.runnable = runnable;
        settingPoolSize = poolSize;
        settingTaskType = taskType;
    }

    public void execute() {
        execute(runnable, settingPoolSize, settingTaskType);
    }

    public void execute(Runnable runnable, int poolsize, String tasktype) {
        BackgroundThreadExecutor bte = new BackgroundThreadExecutor();
        bte.setTaskType(tasktype);
        bte.setThreadPoolSize(poolsize);
        bte.execute(runnable);
    }
}