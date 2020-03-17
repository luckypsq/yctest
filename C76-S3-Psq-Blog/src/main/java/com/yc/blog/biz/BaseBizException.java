package com.yc.blog.biz;

public class BaseBizException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//错误的名称
	private String name;
	//错误编码
	private int code ;
	public BaseBizException(String name, int code,String msg) {
		super(msg);
		this.name = name;
		this.code = code;
	}

	public BaseBizException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseBizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BaseBizException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BaseBizException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseBizException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
