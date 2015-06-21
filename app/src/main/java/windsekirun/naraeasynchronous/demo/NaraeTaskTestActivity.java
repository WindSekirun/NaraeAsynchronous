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
        toolbar.setTitle("NaraeTask 테스트 액티비티");
        toolbar.setTitleTextColor(0xffffffff);
        resultBox = (TextView) findViewById(R.id.textView);
        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NaraeTask naraeTask = new NaraeTask(new NT(), 8);
                naraeTask.execute();
                resultBox.setText("");
                TextAppend("작업을 시작합니다");
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
            TextAppend("메인 스레드에서의 작업이 완료되었습니다!");
            TextAppend("작업된 내용의 결과는 아래와 같습니다.");
            TextAppend(s);
        }
    }
}
