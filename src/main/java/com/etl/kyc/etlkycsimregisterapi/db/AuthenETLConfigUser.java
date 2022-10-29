package com.etl.kyc.etlkycsimregisterapi.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;


public class AuthenETLConfigUser implements Callable<String> {

	java.sql.PreparedStatement pstmt;
	ResultSet rs;
	DatabaseConnectionPool dbConnectionPool;
	private String userId;
	private String userPass;
	Connection connection1;

	public AuthenETLConfigUser(final String user_id, final String name_Pass) {
		this.userId = user_id;
		this.userPass = name_Pass;
	}

	@Override
	public String call() {
		String strRetunr = null;
		Statement statementtAuth = null;
		ResultSet resultSettAuth = null;
		Connection conntAuth = null;
		String sqlCheckPrepaid = "SELECT user_id,first_name,last_name from tb_user_info  where user_name='" + this.userId
				+ "' and user_password = '" + this.userPass + "' and role_id in(2,3)  LIMIT 1";

//		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar c = Calendar.getInstance();

		System.out.println("DQL:: " + sqlCheckPrepaid);

//		int foundUser = 0;

		try {

			dbConnectionPool = new DatabaseConnectionPool(
					Config.driverServr,
					Config.dburlServr,
					Config.dbUserNameServr,
					Config.dbPasswordServr
			);
			connection1 = dbConnectionPool.getConnection();
			pstmt = connection1.prepareStatement(sqlCheckPrepaid);
			rs = (ResultSet) pstmt.executeQuery();

			while (rs != null && rs.next()) {
				String userID = rs.getString("user_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				
				String retrn = userID+"|"+ first_name +" "+ last_name;
				
				return retrn;
			}

		} catch (Exception e) {
			return null;
		} finally {

			try {

				dbConnectionPool.freeConnection(connection1);
				// release resources
				// dbConnectionPool.destroy();

				if (conntAuth != null) {
					conntAuth.close();
				}

				if (statementtAuth != null) {
					statementtAuth.close();
				}

				if (resultSettAuth != null) {
					resultSettAuth.close();
				}
			} catch (Exception e3) {

			}

		}
		return null;
	}

}
