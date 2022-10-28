package com.example.etlkycsimregisterapi.quesry;

import com.example.etlkycsimregisterapi.db.DatabaseConnectionPool;

import java.sql.Connection;
import java.sql.ResultSet;


public class Querymobilestatus {

    java.sql.PreparedStatement pstmt;
    ResultSet rs;
    DatabaseConnectionPool dbConnectionPool;
    Connection connection1;
    private String mobilenumber;

    //public  querymodel Querymobilestatus(final String mobilenumber) {
    //	this.mobilenumber = mobilenumber;


    //@Override
    //public String call() {
    //

    //public  querymodel QuerySIM() throws NoSuchAlgorithmException {

//		System.out.println("mobilenumber1111-======:: " + mobilenumber);
//	
//		String strRetunr = null;
//		Statement statementtAuth = null;
//		ResultSet resultSettAuth = null;
//		Connection conntAuth = null;
//					String sql="SELECT t.status_id,k.status_name from tb_subscriber_info t  INNER JOIN tb_status_info k \r\n"
//			+ "on t.status_id=k.status_id WHERE t.msisdn='"+ mobilenumber +"' LIMIT 1";
//
////		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////		Calendar c = Calendar.getInstance();
//
//					System.out.println("sql-======:: " + sql);
//				
//				
//					//querymodel simstatus= new querymodel();
//		
//		try {
//			
//
//			dbConnectionPool = new DatabaseConnectionPool(Config.driverServr, Config.dburlServr, Config.dbUserNameServr,
//					Config.dbPasswordServr);
//			connection1 = dbConnectionPool.getConnection();
//			pstmt = connection1.prepareStatement(sql);
//			rs = (ResultSet) pstmt.executeQuery();
//
//			while (rs != null && rs.next()) {
//				String status_id = rs.getString("status_id");
//				String status_name = rs.getString("status_name");
//				
//				System.out.println("status_id-======:: " + status_id);
//				
//				System.out.println("status_name-======:: " + status_name);
//				
//				
//				simstatus.setMobileStatus(status_id);
//				simstatus.setMobileStatusDes(status_name);
//				
//				
//				
//				
//				return simstatus;
//				
//			}
//
//		} catch (Exception e) {
//			simstatus.setMobileStatus(" ");
//			simstatus.setMobileStatusDes("Error Mobile Status");
//			return simstatus;
//		
//		} finally {
//
//			try {
//
//				dbConnectionPool.freeConnection(connection1);
//				// release resources
//				// dbConnectionPool.destroy();
//
//				if (conntAuth != null) {
//					conntAuth.close();
//				}
//
//				if (statementtAuth != null) {
//					statementtAuth.close();
//				}
//
//				if (resultSettAuth != null) {
//					resultSettAuth.close();
//				}
//			} catch (Exception e3) {
//
//			}
//
//		}
//		return simstatus;
//		
//		
//	}
//	
//	


}
