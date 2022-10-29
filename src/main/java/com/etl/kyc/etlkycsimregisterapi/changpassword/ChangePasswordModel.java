package com.etl.kyc.etlkycsimregisterapi.changpassword;

public class ChangePasswordModel {

    private String resultCode;
    private String resultMsg;
    private String transactionID;
    private String ExtraPara;
    private String newpassword;
    private String oldpassword;

    private String datetime;

    public ChangePasswordModel() {
        // TODO Auto-generated constructor stub
    }

    public String getResultCode() {
        return resultCode;
    }
    public String getDatetime() {
        return resultMsg;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getExtraPara() {
        return ExtraPara;
    }

    public void setExtraPara(String extraPara) {
        ExtraPara = extraPara;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

}
