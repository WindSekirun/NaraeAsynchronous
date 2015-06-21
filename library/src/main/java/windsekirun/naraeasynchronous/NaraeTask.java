package windsekirun.naraeasynchronous;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import windsekirun.naraeasynchronous.util.CpuCoreInfo;

/**
 * NaraeAsynchronous
 * Class: NaraeCall
 * Created by WindSekirun on 15. 6. 22..
 */
@SuppressWarnings("unchecked")
public class NaraeTask {
    private static final String LOG_TAG = "NaraeTask";

    private static final int DEFAULT_POOL_SIZE = CpuCoreInfo.getCoresCount() + 1;
    private int settingPoolSize = DEFAULT_POOL_SIZE;
    private NaraeInterface nt;

    FutureTask<Object> doingTask = new FutureTask<>(new DoingTask());

    public NaraeTask(NaraeInterface naraeInterface) {
        nt = naraeInterface;
    }

    public NaraeTask(NaraeInterface naraeInterface, int poolSize) {
        settingPoolSize = poolSize;
        nt = naraeInterface;
    }

    public void execute() {
        ExecutorService executorService = Executors.newFixedThreadPool(settingPoolSize);
        executorService.execute(doingTask);

        while (true) {
            if (doingTask.isDone()) {
                NaraeMain endWork = new NaraeMain(new EndTask());
                endWork.execute();

                Log.d(LOG_TAG, "ExecutorService closing..");
                executorService.shutdown();
                return;
            }
        }
    }

    private class DoingTask implements Callable<Object> {

        @Override
        public Object call() throws Exception {
            return nt.doInBackground();
        }
    }

    private class EndTask implements Runnable {

        @Override
        public void run() {
            try {
                nt.onPostExecute(doingTask.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
