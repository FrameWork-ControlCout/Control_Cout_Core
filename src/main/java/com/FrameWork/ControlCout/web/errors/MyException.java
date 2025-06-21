package com.FrameWork.ControlCout.web.errors;

public final class MyException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyException() {
        super();
    }

    public MyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MyException(final String message) {
        super(message);
    }

    public MyException(final Throwable cause) {
        super(cause);
    }

}
