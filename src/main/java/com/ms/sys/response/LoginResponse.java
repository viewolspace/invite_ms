package com.ms.sys.response;

import com.alibaba.fastjson.JSONObject;
import com.ms.common.BaseResponse;

/**
 * Created by leo on 2017/12/21.
 */
public class LoginResponse extends BaseResponse {

	private JSONObject data;

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
}
