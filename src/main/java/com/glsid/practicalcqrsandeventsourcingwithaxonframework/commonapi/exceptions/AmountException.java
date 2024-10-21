package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.exceptions;

public class AmountException extends RuntimeException {
    public AmountException(String specificality) {
        super(specificality);
    }
}
