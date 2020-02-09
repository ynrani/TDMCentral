package com.tdm.dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import com.tdm.dao.RefreshDAO;
import com.tdm.exception.DAOException;


@Component("refreshDAO")
public class RefreshDAOImpl implements RefreshDAO
{

	final static Logger logger = Logger.getLogger(RefreshDAOImpl.class);

	public static final String PAAS_DS = "java:jboss/datasources/tdmCsaaPropAutoDS";
	public static final String USER_DS = "java:jboss/datasources/tdmCsaaUserDS";
	
	// JDBC driver name and database URL
	public static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
	public static final String PASEP2_DB_CONNECTION = "jdbc:oracle:thin:@N01DOL493:1521:pasep2";
	public static final String ATSDEV_DB_CONNECTION = "jdbc:oracle:thin:@192.168.29.216:1521:atsdev";
	//  Database credentials
	public static final String PASEP2_DB_USER = "pasreadonly";
	public static final String PASEP2_DB_PASSWORD = "read4pasep2";
	public static final String ATSDEV_DB_USER = "atsdata";
	public static final String ATSDEV_DB_PASSWORD = "atsdata4dev";

	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean getPolicysummaryData() throws DAOException {
		try {

			String bfr = "SELECT POLICYDETAIL_ID,POLICYNUMBER, LOB, POLICYSTATUSCD, POLICYFORMCD, TIMEDPOLICYSTATUSCD, TXTYPE, CONTRACTTERMTYPECD, PRODUCTCD, RISKSTATECD, TO_CHAR(EFFECTIVE, 'MM-DD-YYYY'), TO_CHAR(EXPIRATION, 'MM-DD-YYYY'), CURRENTREVISIONIND, PO.PAYMENTPLANCD PAYMENTPLANCD "
						+ "FROM POLICYSUMMARY P LEFT OUTER JOIN PAYMENTOPTION PO on p.PAYMENTOPTION_ID = PO.ID WHERE  ROWNUM <5";

			Map<String, String> setResult = new HashMap<String, String>();

			long startTime = System.currentTimeMillis();

/*			JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			
			
			Connection conPasLane = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection csaaCoeCon = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);
			

			/**
			 * Going for fetching summary fields values
			 */

			/**
			 * clean up all
			 */

			PreparedStatement pst2 = csaaCoeCon
					.prepareStatement("INSERT INTO DM_POLICY_SUMMARY_STG(POLICYDETAIL_ID,POLICYNUMBER, LOB, POLICYSTATUSCD, POLICYFORMCD, TIMEDPOLICYSTATUSCD, TXTYPE, CONTRACTTERMTYPECD, PRODUCTCD, RISKSTATECD,EFFECTIVE, EXPIRATION, CURRENTREVISIONIND, PAYMENTPLANCD) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, TO_DATE(?,'MM-DD-YYYY'), TO_DATE(?,'MM-DD-YYYY'), ?, ?)");
			int iCount = 0;

			PreparedStatement pst = conPasLane.prepareStatement(bfr);
			pst.setFetchSize(10000);
			ResultSet rs1 = pst.executeQuery();
			while (rs1.next()) {
				iCount++;
				
				System.out.println("POLICYDETAIL_ID ::" + rs1.getString(1));
				System.out.println("POLICYNUMBER ::" + rs1.getString(2));
				
				pst2.setString(1, rs1.getString(1));
				pst2.setString(2, rs1.getString(2));
				pst2.setString(3, rs1.getString(3));
				pst2.setString(4, rs1.getString(4));
				pst2.setString(5, rs1.getString(5));
				pst2.setString(6, rs1.getString(6));
				pst2.setString(7, rs1.getString(7));
				pst2.setString(8, rs1.getString(8));
				pst2.setString(9, rs1.getString(9));
				pst2.setString(10, rs1.getString(10));
				pst2.setString(11, rs1.getString(11));
				pst2.setString(12, rs1.getString(12));
				pst2.setString(13, rs1.getString(13));
				pst2.setString(14, rs1.getString(14));

				pst2.addBatch();
				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}

			}
			if (iCount > 0) {
				pst2.executeBatch();
			}

			setResult = null;

			// closing resource pre stmts
			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			if (rs1 != null) {
				rs1.close();
			}
			
			if (conPasLane != null) {
				conPasLane.close();
			}
			if (csaaCoeCon != null) {
				csaaCoeCon.close();
			}

			// End

			//
		//	dataSourcePas = null;
		//	conCsaaUser = null;
		//	jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("~~@@##!!$$%%^^&&**  >  Summary " + elapsedTime);
			/**
			 * Going for fetching coverage details
			 */

			return true;

		} catch (IllegalStateException illegalStateEx) {
			illegalStateEx.printStackTrace();
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			illegalArgEx.printStackTrace();
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			nullPointerEx.printStackTrace();
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			otherEx.printStackTrace();
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}
	}

	@Override
	public boolean getPolicyAllCoverages() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();
			// Begin

			/**
			 * Going for fetching All coverage details
			 */

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("%%%%@@@@@@########$$$$$$$$$ > Total derived " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	@Override
	public boolean getPolicyCoverageData() throws DAOException {
		try {

			long startTime = System.currentTimeMillis();

		/*	JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * going for fetch Coverage Details
			 */

			// Going insert records into local mart

			PreparedStatement pstCoverage = conCsaaUser
					.prepareStatement("INSERT INTO DM_COVERAGE_STG(ID,POLICYDETAIL_ID,COVERAGECD)values(?,?,?)");

			/**
			 * Going for fetching coverage details
			 */
			PreparedStatement pstCov = conPas
					.prepareStatement("Select ID,POLICYDETAIL_ID,COVERAGECD from COVERAGE where POLICYDETAIL_ID is not null  and ROWNUM<5");
			pstCov.setFetchSize(5000);
			ResultSet rsCoverage = pstCov.executeQuery();

			int iCount = 0;
			while (rsCoverage.next()) {
				iCount++;
				System.out.println("ID ::" + rsCoverage.getString(1));
				System.out.println("POLICYDETAIL_ID ::" + rsCoverage.getString(2));
				System.out.println("COVERAGECD ::" + rsCoverage.getString(3));
				pstCoverage.setString(1, rsCoverage.getLong(1) + "");
				pstCoverage.setString(2, rsCoverage.getString(2));
				pstCoverage.setString(3, rsCoverage.getString(3));

				pstCoverage.addBatch();
				if (iCount % 10000 == 0) {
					pstCoverage.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pstCoverage.executeBatch();
			}

			// closing resource pre stmts
			if (pstCov != null) {
				pstCov.close();
			}

			if (pstCoverage != null) {
				pstCoverage.close();
			}

			// closing resource resultsets
			if (rsCoverage != null) {
				rsCoverage.close();
			}

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			//dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("****************  >  Coverage details  " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}
	}

	@Override
	public boolean getPolicyRiskCoverage() throws DAOException {
		Connection conCsaaUser = null;
		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			conCsaaUser = dataSourceCsaa.getConnection();*/
			
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);


			// Begin
			//

			/**
			 * going for fetch Risk coverage
			 */

			// Going insert records into local mart
			PreparedStatement pstRiskCoverage = conCsaaUser
					.prepareStatement("INSERT INTO DM_COVERAGE_RISK_STG(ID,POLICYDETAIL_ID,COVERAGECD)values(?,?,?)");

			/**
			 * Going for fetching coverage details
			 */
			PreparedStatement pstCov = conPas
					.prepareStatement("SELECT DISTINCT C.ID,P.POLICYDETAIL_ID,C.COVERAGECD FROM RISKITEM RI, COVERAGE C , POLICYSUMMARY P WHERE P.POLICYDETAIL_ID = RI.POLICYDETAIL_ID AND RI.ID = C.RISKITEM_ID AND ROWNUM <5");
			pstCov.setFetchSize(1000);
			ResultSet rsRisk = pstCov.executeQuery();
			int iCount = 0;
			while (rsRisk.next()) {
				iCount++;
				System.out.println("RISK ID ::" + rsRisk.getString(1));
				System.out.println("POLICYDETAIL_ID ::" + rsRisk.getString(2));
				System.out.println("COVERAGECD ::" + rsRisk.getString(3));
				pstRiskCoverage.setString(1, rsRisk.getLong(1) + "");
				pstRiskCoverage.setString(2, rsRisk.getString(2));
				pstRiskCoverage.setString(3, rsRisk.getString(3));

				pstRiskCoverage.addBatch();
				if (iCount % 10000 == 0) {
					pstRiskCoverage.executeBatch();
					iCount = 0;
				}
			}

			if (iCount != 0 && iCount < 10000) {
				pstRiskCoverage.executeBatch();
			}

			// closing resource pre stmts
			if (pstCov != null) {
				pstCov.close();
			}

			if (pstRiskCoverage != null) {
				pstRiskCoverage.close();
			}

			// closing resource result sets

			if (rsRisk != null) {
				rsRisk.close();
			}

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			//dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("~~~~~~~~~~~~~~~~ > Risk Coverage " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {

			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}
	}

	@Override
	public boolean getPolicyDerivedData() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();
			// Begin

			/**
			 * Going for fetching coverage details
			 */

			Thread threadNoOfDrivers = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyDerivedNoOfDrivers();
					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			Thread threadNoOfInsured = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyDerivedNoOfInsured();

					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			Thread threadNoOfVehi = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyDerivedNoOfVehi();

					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			Thread threadNoOfVio = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyDerivedNoOfVio();

					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			Thread threadCoverages = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyCoverageData();
					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			Thread threadRiskCoverage = new Thread()
			{
				@Override
				public void run() {
					try {
						getPolicyRiskCoverage();

					} catch (DAOException e) {
						e.printStackTrace();
					}
				}
			};

			// Start the threads.
			threadCoverages.start();
			threadRiskCoverage.start();
			threadNoOfDrivers.start();
			threadNoOfInsured.start();
			threadNoOfVehi.start();
			threadNoOfVio.start();

			// Wait for them both to finish
			threadCoverages.join();
			threadRiskCoverage.join();
			threadNoOfDrivers.join();
			threadNoOfInsured.join();
			threadNoOfVehi.join();
			threadNoOfVio.join();

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("%%%%@@@@@@########$$$$$$$$$ > Total derived " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	public boolean getPolicyDerivedNoOfDrivers() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * Going for fetching No Of Driver details
			 */

			// Going insert records into local mart
			PreparedStatement pst2 = conCsaaUser
					.prepareStatement("INSERT INTO DM_NO_OF_DRIVER_STG(POLICYDETAIL_ID,COUNT)values(?,?)");

			/**
			 * No Of Drivers
			 */
			PreparedStatement pst = conPas
					.prepareStatement("SELECT R.POLICYDETAIL_ID,COUNT(*) AS countValue FROM DRIVER R WHERE R.SEQNO IS NOT NULL and R.POLICYDETAIL_ID is not null and ROWNUM <5 group by R.POLICYDETAIL_ID");

			pst.setFetchSize(2000);
			ResultSet rsNoOfDriver = pst.executeQuery();
			int iCount = 0;
			while (rsNoOfDriver.next()) {
				System.out.println("POLICYDETAIL_ID "+rsNoOfDriver.getString(1));
				System.out.println("No Of Drivers Count "+rsNoOfDriver.getString(2));
				pst2.setString(1, rsNoOfDriver.getLong(1) + "");
				pst2.setString(2, rsNoOfDriver.getString(2));
				pst2.addBatch();

				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pst2.executeBatch();
			}
			// closing resource pre stmts

			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			// closing resource resultsets

			if (rsNoOfDriver != null) {
				rsNoOfDriver.close();
			}

			// connections

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			conCsaaUser = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("%%%%%%%%%%%%%%%%% > No Of Driver details  " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	public boolean getPolicyDerivedNoOfVehi() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * Going for fetching No Of Vehi details
			 */

			// Going insert records into local mart

			PreparedStatement pst2 = conCsaaUser
					.prepareStatement("INSERT INTO DM_NO_OF_VEHI_STG(POLICYDETAIL_ID,COUNT)values(?,?)");

			/**
			 * No Of Vehicles
			 */
			PreparedStatement pst = conPas
					.prepareStatement("SELECT R.POLICYDETAIL_ID,COUNT(*) AS countValue FROM RISKITEM R where r.seqno IS NOT NULL and R.POLICYDETAIL_ID is not null AND ROWNUM <5 group by R.POLICYDETAIL_ID");

			pst.setFetchSize(2000);
			ResultSet rsNoOfVehi = pst.executeQuery();
			int iCount = 0;
			while (rsNoOfVehi.next()) {
				
				System.out.println("POLICYDETAIL_ID "+rsNoOfVehi.getString(1));
				System.out.println("No Of Vehical Count "+rsNoOfVehi.getString(2));
				
				pst2.setString(1, rsNoOfVehi.getLong(1) + "");
				pst2.setString(2, rsNoOfVehi.getString(2));
				pst2.addBatch();

				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pst2.executeBatch();
			}
			// closing resource pre stmts

			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			// closing resource resultsets

			if (rsNoOfVehi != null) {
				rsNoOfVehi.close();
			}

			// connections

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			//dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("@@@@@@@@@@@@@@ > No Of Vehi  " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	public boolean getPolicyDerivedNoOfInsured() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * Going for fetching No Of Insured details
			 */

			// Going insert records into local mart

			PreparedStatement pst2 = conCsaaUser
					.prepareStatement("INSERT INTO DM_NO_OF_NAME_INSU_STG(POLICYDETAIL_ID,COUNT)values(?,?)");

			/**
			 * No Of Insured
			 */

			PreparedStatement pst = conPas
					.prepareStatement("select POLICYDETAIL_ID,count(*) AS countValue from INSUREDPRINCIPAL where POLICYDETAIL_ID is not null AND ROWNUM <5  group by POLICYDETAIL_ID");
			pst.setFetchSize(2000);
			ResultSet rsNoOfName = pst.executeQuery();
			int iCount = 0;
			while (rsNoOfName.next()) {
				
				System.out.println("POLICYDETAIL_ID "+rsNoOfName.getString(1));
				System.out.println("No Of Insured Count "+rsNoOfName.getString(2));
				pst2.setString(1, rsNoOfName.getLong(1) + "");
				pst2.setString(2, rsNoOfName.getString(2));
				pst2.addBatch();

				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pst2.executeBatch();
			}
			// closing resource pre stmts

			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			// closing resource resultsets

			if (rsNoOfName != null) {
				rsNoOfName.close();
			}

			// connections

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			//dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("############## >  No Of Insured  " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	public boolean getPolicyDerivedNoOfVio() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * Going for fetching No Of Viol details
			 */

			// Going insert records into local mart

			PreparedStatement pst2 = conCsaaUser
					.prepareStatement("INSERT INTO DM_NO_OF_VIO_STG(POLICYDETAIL_ID,COUNT)values(?,?)");

			/**
			 * No Of Viol
			 */
			PreparedStatement pst = conPas
					.prepareStatement("select d.POLICYDETAIL_ID,count(*) AS countValue from ACCIDENTVIOLATION AV,Driver d where d.ID=AV.DRIVER_ID AND AV.SEQNO IS NOT NULL and d.POLICYDETAIL_ID is not null and ROWNUM <5 group by d.POLICYDETAIL_ID");
			pst.setFetchSize(2000);
			ResultSet rsNoOfVio = pst.executeQuery();
			int iCount = 0;
			while (rsNoOfVio.next()) {
				System.out.println("POLICYDETAIL_ID "+rsNoOfVio.getString(1));
				System.out.println("No Of Violation Count "+rsNoOfVio.getString(2));
				
				pst2.setString(1, rsNoOfVio.getLong(1) + "");
				pst2.setString(2, rsNoOfVio.getString(2));
				pst2.addBatch();

				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pst2.executeBatch();
			}
			// closing resource pre stmts

			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			// closing resource resultsets

			if (rsNoOfVio != null) {
				rsNoOfVio.close();
			}

			// connections

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			//dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out.println("$$$$$$$$$$$$$$$$  >  No Of Viol " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	@Override
	public boolean getRunScript(HttpServletRequest request, HttpServletResponse response)
			throws DAOException {
		String filePath = "\\downloads\\CSAA_SCRIPT.sql";
		try {
			JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourceCsaa = (DataSource) jndiTemplate.lookup(USER_DS);
			Connection conCsaaUser = dataSourceCsaa.getConnection();

			ServletContext context = request.getServletContext();
			String appPath = context.getRealPath("");

			// construct the complete absolute path of the file
			String fullPath = appPath + filePath;
			Reader reader = new BufferedReader(new FileReader(fullPath));

			runScript(conCsaaUser, reader);

			if (null != conCsaaUser) {
				conCsaaUser.close();
			}
			if (null != dataSourceCsaa) {
				dataSourceCsaa = null;
			}
			jndiTemplate = null;

			return true;
		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION" + illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null), illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION" + illegalArgEx);
			throw new DAOException(messageSource.getMessage("INVALID_QUERY_EXCEPTION", null, null),
					illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION" + nullPointerEx);
			throw new DAOException(messageSource.getMessage("NULL_POINTER_EXCEPTION", null, null),
					nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage("DATABASE_EXCEPTION", null, null),
					otherEx);

		}

	}

	private void runScript(Connection conn, Reader reader) throws IOException, SQLException {
		StringBuffer command = null;
		try {
			LineNumberReader lineReader = new LineNumberReader(reader);
			String line = null;
			while ((line = lineReader.readLine()) != null) {
				if (command == null) {
					command = new StringBuffer();
				}
				String trimmedLine = line.trim();
				if (trimmedLine.startsWith("--"))
					println(trimmedLine);
				else if ((trimmedLine.length() >= 1) && (!trimmedLine.startsWith("//"))) {
					if ((trimmedLine.length() >= 1) && (!trimmedLine.startsWith("--"))) {
						if (trimmedLine.endsWith(";")) {
							command.append(line.substring(0, line.lastIndexOf(";")));
							command.append(" ");
							Statement statement = conn.createStatement();

							println(command);

							boolean hasResults = false;
							if (this.stopOnError)
								hasResults = statement.execute(command.toString());
							else {
								try {
									statement.execute(command.toString());
								} catch (SQLException e) {
									e.fillInStackTrace();
									printlnError("Error executing: " + command);
									printlnError(e);
								}
							}

							ResultSet rs = statement.getResultSet();
							if ((hasResults) && (rs != null)) {
								ResultSetMetaData md = rs.getMetaData();
								int cols = md.getColumnCount();
								for (int i = 0; i < cols; i++) {
									String name = md.getColumnName(i);
									print(name + "\t");
								}
								println("");
								while (rs.next()) {
									for (int i = 0; i < cols; i++) {
										String value = rs.getString(i);
										print(value + "\t");
									}
									println("");
								}
							}

							command = null;
							try {
								statement.close();
							} catch (Exception e) {
							}
							Thread.yield();
						} else {
							command.append(line);
							command.append(" ");
						}
					}
				}
			}

		} catch (SQLException e) {
			e.fillInStackTrace();
			printlnError("Error executing: " + command);
			printlnError(e);
			throw e;
		} catch (IOException e) {
			e.fillInStackTrace();
			printlnError("Error executing: " + command);
			printlnError(e);
			throw e;
		} finally {

			flush();
			flush();
		}

	}

	private void print(Object o) {
		if (this.logWriter != null)
			System.out.print(o);
	}

	private void println(Object o) {
		if (this.logWriter != null)
			this.logWriter.println(o);
	}

	private void printlnError(Object o) {
		if (this.errorLogWriter != null)
			this.errorLogWriter.println(o);
	}

	private void flush() {
		if (this.logWriter != null) {
			this.logWriter.flush();
		}
		if (this.errorLogWriter != null)
			this.errorLogWriter.flush();
	}

	private boolean stopOnError;

	private PrintWriter logWriter = new PrintWriter(System.out);
	private PrintWriter errorLogWriter = new PrintWriter(System.err);
	
	public boolean getPolicyAmountStageData() throws DAOException {

		try {

			long startTime = System.currentTimeMillis();

			/*JndiTemplate jndiTemplate = new JndiTemplate();
			DataSource dataSourcePas = (DataSource) jndiTemplate
					.lookup(PAAS_DS);
			DataSource dataSourceCsaa = (DataSource) jndiTemplate
					.lookup(USER_DS);

			Connection conPas = dataSourcePas.getConnection();
			Connection conCsaaUser = dataSourceCsaa.getConnection();*/
			
			Connection conPas = getDBConnection(DB_DRIVER, PASEP2_DB_CONNECTION, PASEP2_DB_USER, PASEP2_DB_PASSWORD);
			Connection conCsaaUser = getDBConnection(DB_DRIVER, ATSDEV_DB_CONNECTION, ATSDEV_DB_USER, ATSDEV_DB_PASSWORD);

			// Begin

			/**
			 * Going for fetching DM_AMOUNT_STG details
			 */

			// Going insert records into local mart

			PreparedStatement pst2 = conCsaaUser
					.prepareStatement("INSERT INTO DM_AMOUNT_STG(POLICYNUMBER, AMT_DUE, AMT_DUE_FLAG, MIN_AMT_DUE, MIN_AMT_DUE_FLAG)values(?,?,?,?,?)");

			/**
			 * DM_AMOUNT_STG
			 */
			PreparedStatement pst = conPas
					.prepareStatement("SELECT DISTINCT BPT.POLICYNUMBER, BA1.AMOUNT AS AMT_DUE, CASE WHEN BA1.AMOUNT=0 or BA1.AMOUNT is null THEN 'N' ELSE 'Y' END AS AMT_DUE_FLAG," 
						+ " BA2.AMOUNT AS MIN_AMT_DUE, CASE WHEN BA2.AMOUNT = 0 or BA2.AMOUNT is null THEN 'N' ELSE 'Y' END AS MIN_AMT_DUE_FLAG "
						+ " FROM BILLABLEPOLICYTERM BPT "
						+ " INNER JOIN BALANCEINFO BI on BI.ID=BPT.BALANCEINFO_ID "
						+ " LEFT OUTER JOIN BALANCEAMOUNT BA1 on BI.BALANCEDUE_ID=BA1.ID "
						+ " LEFT OUTER JOIN BALANCEAMOUNT BA2 on BI.MINIMUMDUE_ID=BA2.ID WHERE ROWNUM <5");
			pst.setFetchSize(2000);
			ResultSet amountStg = pst.executeQuery();
			int iCount = 0;
			while (amountStg.next()) {
				pst2.setString(1, amountStg.getString(1) + "");
				pst2.setString(2, amountStg.getDouble(2) + "");
				pst2.setString(3, amountStg.getString(3) + "");
				pst2.setString(4, amountStg.getDouble(4) + "");
				pst2.setString(5, amountStg.getString(5) + "");
				pst2.addBatch();

				if (iCount % 10000 == 0) {
					pst2.executeBatch();
					iCount = 0;
				}
			}
			if (iCount != 0 && iCount < 10000) {
				pst2.executeBatch();
			}
			// closing resource pre stmts

			if (pst != null) {
				pst.close();
			}
			if (pst2 != null) {
				pst2.close();
			}

			// closing resource resultsets

			if (amountStg != null) {
				amountStg.close();
			}

			// connections

			if (conPas != null) {
				conPas.close();
			}
			if (conCsaaUser != null) {
				conCsaaUser.close();
			}

			// dataSourcePas = null;
			conCsaaUser = null;
			//jndiTemplate = null;

			long stopTime = System.currentTimeMillis();
			long elapsedTime = stopTime - startTime;
			System.out
			.println("$$$$$$$$$$$$$$$$  >  No Of Viol " + elapsedTime);

			return true;

		} catch (IllegalStateException illegalStateEx) {
			logger.error("MessageConstant.NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION"
					+ illegalStateEx);
			throw new DAOException(messageSource.getMessage(
					"NRE_ENTITY_MGR_FACTORY_CLOSED_EXCEPTION", null, null),
					illegalStateEx);

		} catch (IllegalArgumentException illegalArgEx) {
			logger.error("MessageConstant.INVALID_QUERY_EXCEPTION"
					+ illegalArgEx);
			throw new DAOException(messageSource.getMessage(
					"INVALID_QUERY_EXCEPTION", null, null), illegalArgEx);

		} catch (NullPointerException nullPointerEx) {
			logger.error("MessageConstant.NULL_POINTER_EXCEPTION"
					+ nullPointerEx);
			throw new DAOException(messageSource.getMessage(
					"NULL_POINTER_EXCEPTION", null, null), nullPointerEx);

		} catch (Exception otherEx) {
			logger.error("MessageConstant.DATABASE_EXCEPTION" + otherEx);
			throw new DAOException(messageSource.getMessage(
					"DATABASE_EXCEPTION", null, null), otherEx);

		}

	}
	@Override
	public boolean dropAndRecreateStageTables(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getPolicyDerivedDataStage() throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
	
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
	
	public static void main(String[] args) {
		System.out.println("Hi");
		try {
			//new RefreshDAOImpl().getPolicysummaryData();
			//new RefreshDAOImpl().getPolicyCoverageData();
			//new RefreshDAOImpl().getPolicyDerivedNoOfDrivers();
			//new RefreshDAOImpl().getPolicyDerivedNoOfVehi();
			//new RefreshDAOImpl().getPolicyDerivedNoOfInsured();
			//new RefreshDAOImpl().getPolicyDerivedNoOfVio();
			new RefreshDAOImpl().getPolicyAmountStageData();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
