package com.sims.exception;


public class ReductionTooLargeException extends SimsException {

    public ReductionTooLargeException() {
        super("Reduction value must be the same or less than the available product quantity");
    }

    @Override
    public String getErrorCode() {
        return "reduction_too_large";
    }
}
