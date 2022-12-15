package com.etl.kyc.etlkycsimregisterapi.WebPortalSimRegister;

public class WebPortalSimregisterModel {
    private String resultCode;
    private String resultMsg;

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

    public String getExtraPara() {
        return ExtraPara;
    }

    public void setExtraPara(String extraPara) {
        ExtraPara = extraPara;
    }
}
