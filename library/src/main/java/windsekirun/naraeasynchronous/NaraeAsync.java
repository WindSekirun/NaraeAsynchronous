package windsekirun.naraeasynchronous;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import windsekirun.naraeasynchronous.util.BackgroundThreadExecutor;
import windsekirun.naraeasynchronous.util.CpuCoreInfo;
import windsekirun.naraeasynchronous.util.RandomAttributes;

/**
 * NaraeAsynchronous
 * Class: NaraeAsync
 * Created by WindSekirun on 15. 6. 22..
 * 이 코드의 일부는 https://github.com/android/platform_frameworks_base/blob/master/core/java/android/os/AsyncTask.java 에서 발췌하였습니다.
 */
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@SuppressWarnings("ALL")
public class NaraeAsync {
    private static final int DEFAULT_POOL_SIZE = CpuCoreInfo.getCoresCount() + 1;
    private static final String DEFAULT_TASK_TYPE = "Narae.MOE_" + RandomAttributes.getRandomTaskType();
    private Runnable runnable;
    private int settingPoolSize = DEFAULT_POOL_SIZE;
    private String settingTaskType = DEFAULT_TASK_TYPE;
    private BackgroundThreadExecutor bte = new BackgroundThreadExecutor();

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    static Executor THREAD_POOL_EXECUTOR;
    static Executor SERIAL_EXECUTOR = new SerialExecutor();
    Executor sDefaultExecutor = SERIAL_EXECUTOR;
    private static BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(128);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "NaraeAsync #" + mCount.getAndIncrement());
        }
    };

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
        bte = new BackgroundThreadExecutor();
        bte.setTaskType(tasktype);
        bte.setThreadPoolSize(poolsize);
        bte.execute(runnable);
    }

    public void executeOnExecutor() {
        executeOnExecutor(runnable);
    }

    public void executeOnExecutor(Runnable runnable) {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, DEFAULT_POOL_SIZE, 1, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        SERIAL_EXECUTOR.execute(runnable);
    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();
        Runnable mActive;

        @Override
        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                @Override
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }
}