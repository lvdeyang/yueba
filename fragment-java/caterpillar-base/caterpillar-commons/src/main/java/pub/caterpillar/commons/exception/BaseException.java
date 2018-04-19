package pub.caterpillar.commons.exception;

import pub.caterpillar.commons.exception.code.enumeration.StatusCode;

/**
 * 异常基类
 * lvdeyang 2017年6月25日
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 1L;
	
	//状态码
	private StatusCode code;
	
	public BaseException(StatusCode code, String message){
		super(message);
		this.code = code;
	}
	
	public StatusCode getCode(){
		return this.code;
	}
	
}
