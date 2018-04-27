package com.example.demo.matchBean;


/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 20:16 2018-04-24
 */
public class JsonString{
    public String reason;
    public Result result;
    public String error_code;

    public JsonString(String reason, Result result, String error_code) {
        this.reason = reason;
        this.result = result;
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}


