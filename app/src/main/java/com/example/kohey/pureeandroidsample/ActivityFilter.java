package com.example.kohey.pureeandroidsample;

import com.cookpad.puree.PureeFilter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kohey on 2015/04/20.
 */
public class ActivityFilter implements PureeFilter {
    public JSONObject apply(JSONObject jsonLog) throws JSONException {
        // ログがActivityFilterを通過した際に、ログのkeyにtimeを追加する
        jsonLog.put("date", System.currentTimeMillis());
        return jsonLog;
    }
}
