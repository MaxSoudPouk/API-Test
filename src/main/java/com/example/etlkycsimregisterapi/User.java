package com.example.etlkycsimregisterapi;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;


import org.springframework.expression.spel.support.StandardTypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//@Entity
//@Table(name = "tb_test")
public class User{
//	  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
	String sql;
	
	private String user_id;
	String first_name;
	String last_name;
	private String resultcode;
	private String resultdes;
	// @Column(name="user_name")
	private String user_name;
	private String user_password;
	private String Token;
	
//declare for querydb
	String userid="";
	
    String getusername =""; 
    String getpassword = "";
	String firstname = "";
	String lastname = "";

	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

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


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	//==============================================
	

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public User Queryuser(String dbuser, String dbpass) throws NoSuchAlgorithmException {
		// System.out.println("username="+ dbuser);
		// System.out.println("password="+ dbpass);
//==================SHA265===============
//		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//		byte hashBytes[] = messageDigest.digest(dbpass.getBytes(StandardCharsets.UTF_8));
//		BigInteger noHash = new BigInteger(1, hashBytes);
//		String hashStr = noHash.toString(16);
		
//		
		CHA256 char256 = new CHA256();
		String hashStr=char256.CHA256(dbpass);
		
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		con = dbconnect.connectDB();
		User user = new User();

		try {
			// sql="SELECT * FROM tb_user_info WHERE role_id IN (2,3) AND status_id=4 AND
			// user_name='user_name'";
			//ArrayList<String> user = new ArrayList<String>();
			sql = "SELECT user_id,user_name,user_password,first_name,last_name FROM tb_user_info t WHERE t.role_id IN (2,3) AND t.status_id=4 AND t.user_name='" + dbuser
					+ "' and t.user_password='" + hashStr + "'";
			
			System.out.println("Sql=" + sql);
			
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			
			// ============================


			while (rs.next()) {
				
				//rs.getBlob(1);
				//user_id = rs.getString("user_id");
				userid = rs.getString("user_id");
				getusername=rs.getString("user_name");
				getpassword=rs.getString("user_password");
				firstname=rs.getString("first_name");
				lastname=rs.getString("last_name");
			}
			
			
			System.out.println("dbuser=" + dbuser);
			System.out.println("encodersha256=" + hashStr);
			System.out.println("password=" + getpassword);
			System.out.println("firstname=" + firstname);
			
			System.out.println("lastname=" + lastname);
			
			System.out.println("userid===============" + userid);
			System.out.println("getusername===============" + getusername);
			//=================jwt endcode======
			
			
			 long currentime = System.currentTimeMillis();
			 Date now = new Date(currentime);
			createjwt jwt = new createjwt();
			
			
			String jwttoken = jwt.createJWT(userid, getusername, "etlkyc", currentime);
			
			System.out.println("jwttoken===============" + jwttoken);
			//==================jwt decode=====================
		    Claims  Claims =jwt.decodeJWT(jwttoken,userid);
			
		
			//System.out.println("jwttoken=" + Claims);
			
			//if ((dbuser.equals(getusername)) && (hashStr.equals(getpassword))) {
				if (dbuser.equals(getusername)) {

				System.out.println("Successss");
				
			    user.setUser_id(userid);
				user.setFirst_name(firstname);
				user.setLast_name(lastname);
				user.setResultcode("405000000");
				user.setResultdes("Login Success");
				user.setToken(jwttoken);

				con.close();
				rs.close();
				p.close();

				return user;

			} else {

				user.setResultcode("001");
				user.setResultdes("Login fail");
				System.out.println("fffffffffffffffffffffff");

				con.close();
				rs.close();
				p.close();
				return user;

			}

		}

		// ===================

		// ==================

		// Catch block is used for exception
		catch (SQLException e) {
			
			user.setResultcode("0013");
			user.setResultdes("System Error");
			
			// Print exception pop-up on the screen
			//System.out.println(e);
		}
		return user;

		// return "User [user_name=" + user_name + ", user_password=" + user_password +
		// "]";
	}
	
	
}
