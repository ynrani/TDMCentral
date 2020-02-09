package com.tdm.constant;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionPOC {
	// JDBC driver name and database URL
	public static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	public static final String PASEP2_DB_CONNECTION = "jdbc:oracle:thin:@N01DOL493:1521:pasep2";
	public static final String ATSDEV_DB_CONNECTION = "jdbc:oracle:thin:@192.168.29.216:1521:atsdev";


	//  Database credentials
	public static final String PASEP2_DB_USER = "pasreadonly";
	public static final String PASEP2_DB_PASSWORD = "read4pasep2";
	public static final String ATSDEV_DB_USER = "atsdata";
	public static final String ATSDEV_DB_PASSWORD = "ats4dev";

	public static void main(String[] args) {
		Connection pasEP2Connection = null;
		Connection atsDataConnection = null;
		Statement stmt = null;
		String policyNumber;
		String lob;
		String policyStatusCD;
		String policyFormCD;
		String timedPolicyStatusCD;
		String txType;
		String contractTermTypeCD;
		String productCD;
		String riskStateCD;
		String effective;
		String expiration;
		String policyDetailId;
		String currentRevisionInd;
		try{
			//STEP 2: Register JDBC driver
			Class.forName(DB_DRIVER);

			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			pasEP2Connection = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			atsDataConnection = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = pasEP2Connection.createStatement();
			String sql;
			sql = "SELECT *  FROM POLICYSUMMARY WHERE ROWNUM <=5";
			ResultSet rs = stmt.executeQuery(sql);

			//STEP 5: Extract data from result set
			while (rs.next()) {
				policyNumber = rs.getString("POLICYNUMBER");
				lob = rs.getString("LOB");
				policyStatusCD = rs.getString("POLICYSTATUSCD");
				policyFormCD = rs.getString("POLICYFORMCD");
				timedPolicyStatusCD = rs.getString("TIMEDPOLICYSTATUSCD");
				txType = rs.getString("TXTYPE");
				contractTermTypeCD = rs.getString("CONTRACTTERMTYPECD");
				productCD = rs.getString("PRODUCTCD");
				riskStateCD = rs.getString("RISKSTATECD");
				effective = rs.getString("EFFECTIVE");
				expiration = rs.getString("EXPIRATION");
				policyDetailId = rs.getString("POLICYDETAIL_ID");
				currentRevisionInd = rs.getString("CURRENTREVISIONIND");
				//			paymentPlanCD = rs.getString("PAYMENTPLANCD");
				//			paymentFrequency = rs.getString("PAYMENT_FREQUENCY");

				System.out.println(policyNumber+","+lob+","+policyStatusCD+","+policyFormCD+","+timedPolicyStatusCD+","+txType+","+contractTermTypeCD+","+
						productCD+","+riskStateCD+","+effective+","+expiration+","+policyDetailId+","+currentRevisionInd
						//+","+paymentPlanCD+","+paymentFrequency
						);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			pasEP2Connection.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(pasEP2Connection!=null)
					pasEP2Connection.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try
		System.out.println("Goodbye!");
	}//end main
	/***
	 * To get the 
	 * @return
	 */
	private static Connection getDBConnection(String dbDriver
			, String dbConnectionURL
			, String dbUser
			, String dbPassword) {
		Connection dbConnection = null;
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			dbConnection = DriverManager.getConnection(
					dbConnectionURL , dbUser ,dbPassword);
			return dbConnection;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
