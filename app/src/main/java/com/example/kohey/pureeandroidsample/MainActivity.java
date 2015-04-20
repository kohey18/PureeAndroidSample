package com.example.kohey.pureeandroidsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Context;

import com.cookpad.puree.Puree;
import com.cookpad.puree.PureeConfiguration;
import com.cookpad.puree.PureeFilter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Puree初期化
        Puree.initialize(buildConfiguration(this));
    }

    public void onClick(View v) {
        // クリックされたボタンオブジェクトを取得
        Button btn = (Button)findViewById(v.getId());
        // 答えた結果をランダムで返す
        String results[] = {"marvelous", "fantastic", "excellent", "good"};

        Puree.send(new ClickLog("click", (String) btn.getText(), results[(int) (Math.random() * 4)]));
        // -> (ex) ログの例
        // {"action": "click", "btn_label": "1", "result": "marvelous"}
    }

    // Pureeの設定ファイル
    public static PureeConfiguration buildConfiguration(Context context) {
        PureeFilter activityFilter = new ActivityFilter();
        // Puree.sendの際にClickLogクラス経由だと、
        // ログはactivityFilterを通って、LogServerOutputにBufferされる
        return new PureeConfiguration.Builder(context)
                .source(ClickLog.class).filter(activityFilter).to(new LogServerOutput())
                .build();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
