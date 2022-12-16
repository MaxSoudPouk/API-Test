package com.etl.kyc.etlkycsimregisterapi.smsotp;

public class otpresponModel {

    private String resultCode;
    private String resultMsg;

    private String transactionNo;
//    private String Token;
    private String ExtraPara;



    public String getExtraPara() {
        return ExtraPara;
    }

    public void setExtraPara(String extraPara) {
        ExtraPara = extraPara;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
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
//
//    public String getToken() {
//        return Token;
//    }
//
//    public void setToken(String Token) {
//        this.Token = Token;
//    }

}