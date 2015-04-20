package com.example.kohey.pureeandroidsample;

import com.cookpad.puree.outputs.PureeOutput;

/**
 * Created by kohey on 2015/04/20.
 */

import com.cookpad.puree.outputs.PureeOutput;
import com.cookpad.puree.outputs.OutputConfiguration;
import org.json.JSONObject;
import android.util.Log;

public class OutLogcat extends PureeOutput {

    public static final String TYPE = "logcat";

    @Override
    public String type() {
        return TYPE;
    }

    @Override
    public OutputConfiguration configure(OutputConfiguration conf) {
        return conf;
    }

    @Override
    public void emit(JSONObject jsonLog) {
        Log.d(TYPE, jsonLog.toString());
    }

}
