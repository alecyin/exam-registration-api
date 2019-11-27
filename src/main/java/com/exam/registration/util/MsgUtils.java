package com.exam.registration.util;

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

	public static Map<String, Object> success(Object... data) {
		Map<String, Object> result = new HashMap<>();
		result.put("code", ResCode.SUCCESS);
		result.put("msg", "success");
		result.put("data", "{}");
		if (Objects.nonNull(data)) {
			result.put("data", data);
		}
		return result;
	}

	public static Map<String, Object> fail(String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ret", ResCode.FAIL);
		result.put("msg", msg);
		return result;
	}

}
