package com.example.etlkycsimregisterapi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class SIM_status {
	
	private String resultcode;
	private String resultdes;
	private Integer simstatus;
	//private String Token;
	
	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getResultdes() {
		return resultdes;
	}

	public void setResultdes(String resultdes) {
		this.resultdes = resultdes;
	}


	public Integer getSimstatus() {
		return simstatus;
	}

	public void setSimstatus(Integer simstatus) {
		this.simstatus = simstatus;
	}
	
	
	


	public  SIM_status QuerySIM(String msisdn, String datetime, String token) throws NoSuchAlgorithmException {
		createjwt Decodejwt = new createjwt();
		boolean boodecode = Decodejwt.jwtboolean;
		
		User getuser =new User();
		String userid =getuser.getUser_id();
		//boolean jwtboo=Decodejwt.jwtboolean;
		
		
		
		System.out.println("jwtboo====="+ boodecode);
		
		
		
	 
		//System.out.println("user====="+ user);
		
		
		//jwt.decodeJWT(token,user);
		
		System.out.println("Token====="+ token);
		System.out.println("datetime====="+ datetime);
		//===================================
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		con = dbconnect.connectDB();
		
		SIM_status sim = new SIM_status();
		int simvalue = 0;

		try {
			
			  if (boodecode==true) {
					  
			// sql="SELECT * FROM tb_user_info WHERE role_id IN (2,3) AND status_id=4 AND
			// user_name='user_name'";
			String sql = "SELECT status_id from tb_subscriber_info s WHERE s.msisdn='"+ msisdn +"' and s.status_id='2'";
			//String sql = "SELECT status_id from tb_subscriber_info s WHERE s.msisdn='"+ msisdn +"'";
			System.out.println("Sql=" + sql);
			
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			while (rs.next()) {
				// int id = rs.getInt("id");
				simvalue = rs.getInt("status_id");

			}
			
			System.out.println("SIM Status=" + simvalue);
			
	          if (simvalue==2) {

				System.out.println("Successss");
				
				sim.setResultcode("405000000");
				sim.setResultdes("Number already approval");
				sim.setSimstatus(simvalue);

				con.close();
				rs.close();
				p.close();

				return sim;

		} else {
			sim.setResultcode("100000");
			sim.setResultdes("Check Status number");
			sim.setSimstatus(simvalue);

			}

		}else {
			sim.setResultcode("100001");
			sim.setResultdes("Token key not Correct");
		}

		
		 } 
		

		// Catch block is used for exception
		catch (SQLException e) {
			sim.setResultcode("002");
			sim.setResultdes("Query SIM status fail");
			System.out.println("Query SIM Fail");

			return sim;
			// Print exception pop-up on the screen
			//System.out.println(e);
		}
		
	
		
		return sim;

	}
	
	

}
