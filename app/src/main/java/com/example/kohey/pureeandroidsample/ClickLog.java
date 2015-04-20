package com.example.kohey.pureeandroidsample;

/**
 * Created by kohey on 2015/04/20.
 */

import com.cookpad.puree.JsonConvertible;
import com.google.gson.*;
import com.google.gson.annotations.*;
import com.google.gson.stream.*;

public class ClickLog extends JsonConvertible {

    @SerializedName("btn_label") private String btn_label;
    @SerializedName("action") private String action;
    @SerializedName("result") private String result;

    public ClickLog(String action, String btn_label, String result) {
        this.btn_label = btn_label;
        this.result = result;
        this.action = action;
    }
}
