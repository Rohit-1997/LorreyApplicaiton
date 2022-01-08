package com.transportManagement.lorryApplication.response;

public class BaseResponse {
    private String status;
    private String errorMessage;
    private  int statusCode;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
