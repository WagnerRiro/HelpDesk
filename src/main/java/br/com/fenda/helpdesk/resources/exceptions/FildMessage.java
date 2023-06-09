package br.com.fenda.helpdesk.resources.exceptions;

import java.io.Serializable;

public class FildMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fildName;
    private String message;

    public FildMessage() {
        super();
    }

    public FildMessage(String fildName, String message) {
        super();
        this.fildName = fildName;
        this.message = message;
    }

    public String getFildName() {
        return fildName;
    }

    public void setFildName(String fildName) {
        this.fildName = fildName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
