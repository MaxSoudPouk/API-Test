package com.etl.kyc.etlkycsimregisterapi.smsotp;

public class otpModel {

    private String resultCode;
    private String resultMsg;
    private String smsresultcode;
    private String transactionNo;
    private String UserID;
    private String Token;



    public String getSmsresultcode() {
        return smsresultcode;
    }

    public void setSmsresultcode(String smsresultcode) {
        this.smsresultcode = smsresultcode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }


}