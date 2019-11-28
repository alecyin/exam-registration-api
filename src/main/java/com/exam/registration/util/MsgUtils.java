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
		SUCCESS(1),FAIL(0);

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
		jsonObject.put("msg", "success");
		jsonObject.put("data", "{}");
		if (Objects.nonNull(data)) {
			jsonObject.put("data", data);
		}
		return jsonObject.toJSONString();
	}

	public static String fail(String msg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", ResCode.FAIL.code);
		jsonObject.put("msg", msg);
		return jsonObject.toJSONString();
	}

}
