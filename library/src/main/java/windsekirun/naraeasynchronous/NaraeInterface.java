package windsekirun.naraeasynchronous;

/**
 * NaraeAsynchronous
 * Class: NaraeTask
 * Created by WindSekirun on 15. 6. 22..
 */

public interface NaraeInterface<End> {
    End doInBackground();

    void onPostExecute(End end);
}