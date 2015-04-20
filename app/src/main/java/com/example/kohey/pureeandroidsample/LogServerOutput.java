package com.example.kohey.pureeandroidsample;

import android.util.Log;

import com.cookpad.puree.async.AsyncResult;
import com.cookpad.puree.outputs.OutputConfiguration;
import com.cookpad.puree.outputs.PureeBufferedOutput;
import org.json.JSONArray;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kohey on 2015/04/20.
 */
public class LogServerOutput extends PureeBufferedOutput {

    private static final String TYPE = "action_log";

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public OutputConfiguration configure(OutputConfiguration conf) {
            // ログをログ収集サーバにflushする間隔
            conf.setFlushIntervalMillis(10000);
            // ローカルに最大何個のログを貯めるのか
            conf.setLogsPerRequest(4);
            // ログをflushするのを間違えた際に繰り返す回数
            conf.setMaxRetryCount(2);
        return conf;

    }

    @Override
    public void emit(JSONArray jsonArray, final AsyncResult result) {

        HttpURLConnection con = null;
        OutputStream out = null;
        InputStream in = null;
        BufferedReader reader = null;
        URL url = null;

        try {
            // ローカルで動いているtd-agentのinput用のipに向ける
            url = new URL("http://192.168.59.103:8888/log.http");
            con = (HttpURLConnection)url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");
            con.connect();

            // 取得したログを書き込む
            out = new BufferedOutputStream(con.getOutputStream());
            byte[] outputBytes = jsonArray.toString().getBytes("UTF-8");
            out.write(outputBytes);
            out.flush();

            // レスポンスを取得
            in = new BufferedInputStream(con.getInputStream());
            reader = new BufferedReader(new InputStreamReader(in));
            String results = reader.readLine();
            Log.d("output_results:", "result: " + jsonArray);
            Log.d("output", "output:" + results); // 特にtd-agent側から結果を出力していないのでnull

        } catch (IOException e) {
            Log.e("error", "error:" + e);

        } finally {
            result.success();
        }
    }
}
