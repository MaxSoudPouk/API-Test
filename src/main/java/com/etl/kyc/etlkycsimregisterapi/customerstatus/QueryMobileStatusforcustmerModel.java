package com.etl.kyc.etlkycsimregisterapi.customerstatus;

public class QueryMobileStatusforcustmerModel {

	public QueryMobileStatusforcustmerModel() {
		// TODO Auto-generated constructor stub
	}

	private String resultCode;
	private String resultMsg;
	private String transactionID;
	private String ExtraPara;
	private String mobileStatus;
	private String mobileStatusDes;

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

	public String getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(String mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public String getMobileStatusDes() {
		return mobileStatusDes;
	}

	public void setMobileStatusDes(String mobileStatusDes) {
		this.mobileStatusDes = mobileStatusDes;
	}

}
