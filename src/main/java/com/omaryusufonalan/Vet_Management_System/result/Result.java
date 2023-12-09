package com.omaryusufonalan.Vet_Management_System.result;

import lombok.Getter;

@Getter
public class Result {
    private final boolean status;
    private final String message;
    private final String code;

    public Result(boolean status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
