package com.sims.exception;


public class ReservationTooLargeException extends SimsException {

    public ReservationTooLargeException() {
        super("Reservation value + current reserved value must be the same or less than the available product " +
                "quantity");
    }

    @Override
    public String getErrorCode() {
        return "reservation_too_large";
    }
}
