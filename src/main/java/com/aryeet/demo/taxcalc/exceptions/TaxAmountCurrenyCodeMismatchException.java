package com.aryeet.demo.taxcalc.exceptions;

public class TaxAmountCurrenyCodeMismatchException extends RuntimeException{
    private static final long serialVersionUID = 7718828512143293558L;
    private final ErrorCode code;

    public TaxAmountCurrenyCodeMismatchException(String message, Throwable cause, ErrorCode code) {
        super(message, cause);
        this.code = code;
    }

    public TaxAmountCurrenyCodeMismatchException(ErrorCode code) {
        super();
        this.code = code;
    }

    public TaxAmountCurrenyCodeMismatchException(String message, ErrorCode code) {
        super("ErrorCode=" + code.getCode() + "," + message);
        this.code = code;
    }

    public TaxAmountCurrenyCodeMismatchException(Throwable cause, ErrorCode code) {
        super(cause);
        this.code = code;
    }
    public ErrorCode getCode() {
        return this.code;
    }
}
