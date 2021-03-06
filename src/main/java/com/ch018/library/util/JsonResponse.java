package com.ch018.library.util;

import java.util.Map;

public class JsonResponse {
    private String status = null;
    private Map<String,String> errorsMap;
    private Object result = null;
    
    public String getStatus() {
            return status;
    }
    
    public void setStatus(String status) {
            this.status = status;
    }
    
    public Object getResult() {
            return result;
    }
    
    public void setResult(Object result) {
            this.result = result;
    }
    
    public Map<String,String> getErrorsMap() {
        return errorsMap;
    }
    public void setErrorsMap(Map<String,String> errorsMap) {
        this.errorsMap = errorsMap;
    }
    
    @Override
    public String toString() {
    	return "JsonResponse[status: " + getStatus() + ", result: " + getResult() + ", Errors: " + getErrorsMap() + "]";
    }

}
