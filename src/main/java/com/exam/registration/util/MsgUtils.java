package com.exam.registration.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname MsgUtils
 **/
public class MsgUtils {

    public static String success(Object... data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("message", "success");
        jsonObject.put("data", Objects.nonNull(data) ? data : "{}");
        return jsonObject.toJSONString();
    }

    public static String success(JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("message", "success");
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

    public static String querySuccess(Object data, long pageTotal) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code());
        jsonObject.put("message", "success");
        jsonObject.put("data", data);
        jsonObject.put("pageTotal", pageTotal);
        return jsonObject.toJSONString();
    }

    public static String fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.FAIL.code());
        jsonObject.put("message", msg);
        return jsonObject.toJSONString();
    }

    public static String noLogin() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.NOLOGIN.code());
        return jsonObject.toJSONString();
    }

}
