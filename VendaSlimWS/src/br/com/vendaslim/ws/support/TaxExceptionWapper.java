package br.com.vendaslim.ws.support;


public class TaxExceptionWapper{
	private String code;
	private String message;

	public TaxExceptionWapper(final TaxException ex){
		this.code = ex.getCode();
		this.message = ex.getMessage();
	}

	public TaxExceptionWapper(final String code, final String message){
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setCode(final String code) {
		this.code = code;
	}

	public void setMessage(final String message) {
		this.message = message;
	}


}
