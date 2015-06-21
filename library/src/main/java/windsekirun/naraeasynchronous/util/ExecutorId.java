package windsekirun.naraeasynchronous.util;

/**
 * NaraeTask
 * Class: ExecutorId
 * Created by WindSekirun on 15. 6. 22..
 */
public class ExecutorId {

    private final int mThreadPoolSize;
    private final String mTaskType;

    public ExecutorId(int threadPoolSize, String taskType) {
        mThreadPoolSize = threadPoolSize;
        mTaskType = taskType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutorId executorId = (ExecutorId) o;
        return mThreadPoolSize == executorId.mThreadPoolSize && mTaskType.equals(executorId.mTaskType);
    }

    @Override
    public int hashCode() {
        return 31 * mThreadPoolSize + mTaskType.hashCode();
    }
}