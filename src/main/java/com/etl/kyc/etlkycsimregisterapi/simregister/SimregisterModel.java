package com.etl.kyc.etlkycsimregisterapi.simregister;

public class SimregisterModel {
    private String resultCode;
    private String resultMsg;
    private String transactionID;
    private String ExtraPara;

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


}
