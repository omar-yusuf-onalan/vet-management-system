package com.omaryusufonalan.Vet_Management_System.result;

import lombok.Getter;

@Getter
public class ResultWithData<T> extends Result {
    private final T data;
    public ResultWithData(boolean status, String message, String code, T data) {
        super(status, message, code);
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultWithData{" +
                "data=" + data +
                '}';
    }
}
