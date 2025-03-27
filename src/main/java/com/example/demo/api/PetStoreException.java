package com.example.demo.api;

import lombok.Getter;

@Getter
public class PetStoreException extends RuntimeException {
    private final int code;

    public PetStoreException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public Error toError() {
        return new Error(getMessage());
    }
}
