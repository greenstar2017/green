package com.green.framework.exception.error;

/**
 * 业务异常
 * 
 * yuanhualiang
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 3190256515490831676L;
	private String message;
	private Object params[];
	private ErrorEntity errorEntity;

	public BusinessException() {
	}

	public String getMessage() {
		return message;
	}

	public ErrorEntity getErrorEntity() {
		return errorEntity;
	}

	public Object[] getParams() {
		return params;
	}

	public static BusinessException error(ErrorEntity errorEntity) {
		BusinessException businessException = new BusinessException();
		if (errorEntity != null) {
			businessException.message = errorEntity.getErrorMessage();
			businessException.errorEntity = errorEntity;
		}
		return businessException;
	}

	public static BusinessException error(ErrorEntity errorEntity,
			String message, Object params[]) {
		BusinessException businessException = error(errorEntity);
		businessException.message = (new StringBuilder())
				.append(businessException.message).append(",").append(message)
				.toString();
		businessException.params = params;
		return businessException;
	}
}