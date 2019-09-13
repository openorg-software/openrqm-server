package org.openrqm.api;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-09-12T19:15:09.451Z")

public class ApiException extends Exception {
    private static final long serialVersionUID = 1L;
    private int code;

    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
