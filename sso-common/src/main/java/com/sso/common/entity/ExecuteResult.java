package com.sso.common.entity;

import com.sso.common.constant.GlobalConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Description:[全局返回结果]</p>
 * Create on 2019/7/6
 *
 * @author learrings
 */
@Getter
@Slf4j
public class ExecuteResult<T> implements Serializable {

	private T data;
	private Integer code;
	private String message;
	private List<String> errorMessages = new ArrayList<>();

	private ExecuteResult() {
		log.debug("不暴露无参构造");
	}

	public static <T> ExecuteResult<T> ok(Integer code, T data, String message) {
		ExecuteResult<T> result = new ExecuteResult<>();
		result.code = code;
		result.data = data;
		result.message = message;
		return result;
	}

	public static <T> ExecuteResult<T> fail(Integer code, T data, List<String> errorMsgList) {
		ExecuteResult<T> result = new ExecuteResult<>();
		result.code = code;
		if (!CollectionUtils.isEmpty(errorMsgList)) {
			result.errorMessages.addAll(errorMsgList);
		}
		result.data = data;
		return result;
	}

	public boolean isSuccess() {
		return this.errorMessages.isEmpty();
	}

	public static <T> ExecuteResult<T> ok(T data) {
		return ok(data, null);
	}

	public static <T> ExecuteResult<T> ok(T data, String message) {
		return ok(GlobalConstant.EXECUTE_RESULT_SUCCESS_CODE, data, message);
	}

	public static <T> ExecuteResult<T> fail(String errorMsg) {
		return fail(null, errorMsg);
	}

	public static <T> ExecuteResult<T> fail(T data, String errorMsg) {
		return fail(GlobalConstant.EXECUTE_RESULT_ERROR_CODE, data, Collections.singletonList(errorMsg));
	}

}
