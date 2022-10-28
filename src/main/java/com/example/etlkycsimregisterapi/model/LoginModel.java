package com.example.etlkycsimregisterapi.model;

public class LoginModel {

	public LoginModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	String resultCode;
	String resultMsg;
	String userID;
	String userName;
	String firstName;
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	String token;
	String extraPara;
	
	
	
	
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
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExtraPara() {
		return extraPara;
	}
	public void setExtraPara(String extraPara) {
		this.extraPara = extraPara;
	}
	
	
	

}
