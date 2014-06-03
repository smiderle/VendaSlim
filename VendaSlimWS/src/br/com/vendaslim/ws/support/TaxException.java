/**
 * 
 */
package br.com.vendaslim.ws.support;

/**
 * @author ladair.junior
 *
 */
public class TaxException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7971601421958485239L;
	private String code;


	public TaxException(final String message) {
		super(message);
	}


	public TaxException(final String message, final Throwable cause) {
		super(message, cause);
	}



	public TaxException(final Throwable cause) {
		super(cause);
	}

	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}


}
