package windsekirun.naraeasynchronous;

import windsekirun.naraeasynchronous.util.MainThreadExecutor;

/**
 * NaraeAsynchronous
 * Class: NaraeMain
 * Created by WindSekirun on 15. 6. 22..
 */
public class NaraeMain {
    Runnable runnable;

    public NaraeMain(Runnable runnable) {
        this.runnable = runnable;
    }

    public void execute() {
        MainThreadExecutor mte = new MainThreadExecutor();
        mte.execute(runnable);
    }
}
