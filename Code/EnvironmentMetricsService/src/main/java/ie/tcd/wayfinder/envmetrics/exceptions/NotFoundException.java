package ie.tcd.wayfinder.envmetrics.exceptions;

public class NotFoundException extends ApiException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6451460679585645077L;
	private int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public NotFoundException(int code, String msg) {
		super(code, msg);
		this.code = code;
	}
}
