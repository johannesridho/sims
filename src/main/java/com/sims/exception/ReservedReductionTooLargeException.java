package com.sims.exception;


public class ReservedReductionTooLargeException extends SimsException {

    public ReservedReductionTooLargeException() {
        super("Reduction value must be the same or less than the reserved product");
    }

    @Override
    public String getErrorCode() {
        return "reserved_reduction_too_large";
    }
}
