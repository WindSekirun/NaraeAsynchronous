package windsekirun.naraeasynchronous.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import windsekirun.naraeasynchronous.NaraeInterface;
import windsekirun.naraeasynchronous.NaraeTask;
import windsekirun.naraeasynchronous.util.RandomAttributes;


/**
 * NaraeAsynchronous Demo
 * Class: NaraeTaskTest
 * Created by WindSekirun on 15. 6. 22..
 */
public class NaraeTaskTestActivity extends AppCompatActivity {
    TextView resultBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NaraeTask Test Activity");
        toolbar.setTitleTextColor(0xffffffff);
        resultBox = (TextView) findViewById(R.id.textView);
        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NaraeTask naraeTask = new NaraeTask(new NT(), 8);
                naraeTask.execute();
                resultBox.setText("");
                TextAppend("Starting WORKING");

                new NaraeTask(new NaraeInterface<String>() {

                    @Override
                    public String doInBackground() {
                        return null;
                    }

                    @Override
                    public void onPostExecute(String s) {

                    }
                }).execute();


            }
        });
    }

    public void TextAppend(String text) {
        resultBox.setText(String.valueOf(resultBox.getText()) + System.getProperty("line.separator") + text + System.getProperty("line.separator"));
    }

    public class NT implements NaraeInterface<String> {

        @Override
        public String doInBackground() {
            String generated = "";

            for (int i = 0; i < 100; i++) {
                generated = generated + " " + RandomAttributes.getRandomTaskType();
            }

            return generated;
        }

        @Override
        public void onPostExecute(String s) {
            TextAppend("Working done");
            TextAppend("Result:");
            TextAppend(s);
        }
    }
}
