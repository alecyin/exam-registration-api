package com.exam.registration.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yhf
 * @classname MsgUtils
 * @description TODO
 * @date 2019/11/27
 **/
public class MsgUtils {
    enum ResCode {
        SUCCESS(20000),FAIL(60204);

        private int code;

        ResCode(Integer code){
            this.code = code;
        }

        @Override
        public String toString() {
            return String.valueOf(this.code);
        }
    }

    public static String success(Object... data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code);
        jsonObject.put("message", "success");
        jsonObject.put("data", Objects.nonNull(data) ? data : "{}");
        return jsonObject.toJSONString();
    }

    public static String success(JSONObject data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code);
        jsonObject.put("message", "success");
        jsonObject.put("data", data);
        return jsonObject.toJSONString();
    }

    public static String querySuccess(Object data, long pageTotal) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.SUCCESS.code);
        jsonObject.put("message", "success");
        jsonObject.put("data", data);
        jsonObject.put("pageTotal", pageTotal);
        return jsonObject.toJSONString();
    }

    public static String fail(String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ResCode.FAIL.code);
        jsonObject.put("message", msg);
        return jsonObject.toJSONString();
    }

}
