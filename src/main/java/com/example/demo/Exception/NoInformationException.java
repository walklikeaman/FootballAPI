package com.example.demo.Exception;

public class NoInformationException extends Throwable {

    public NoInformationException() {
        super("No connection to provider and no stored data");
    }
}
