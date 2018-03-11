package com.sims.exception;


import com.sims.CommonConstant;

public class NotFoundException extends SimsException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class clazz, String entityId) {
        this(clazz.getSimpleName(), entityId);
    }

    public NotFoundException(String entityName, String entityId) {
        super(String.format(CommonConstant.LOCALE, "%s with Id %s not found.", entityName, entityId));
    }

    @Override
    public String getErrorCode() {
        return "not_found";
    }
}
