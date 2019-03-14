package ie.tcd.wayfinder.envmetrics.exceptions;

public class ValueNotAcceptedException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5476268285027351062L;
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ValueNotAcceptedException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
