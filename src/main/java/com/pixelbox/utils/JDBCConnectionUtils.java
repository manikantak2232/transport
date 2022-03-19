package com.pixelbox.utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.sql.DataSource;
//import org.apache.tomcat.jdbc.pool.DataSource;

import com.pixelbox.dto.BaseResponse;
import com.pixelbox.exceptions.TransportException;
import com.pixelbox.exceptions.ExceptionHelper;
import com.pixelbox.exceptions.ExceptionLevelEnum;

/**
 * @author Cheman Shaik, 10-Aug-2016 14:18:59 pm
 *
 */
public class JDBCConnectionUtils {

	// define datasource variable here 
	static DataSource TRANSPORT_DS ;

	// define logger variable here 
	final static Logger log = Logger.getLogger(JDBCConnectionUtils.class);
	
	public static void  dataSourceInitialization() 
	{
	//  initialise the datasource variable
		Context initContext;

		// lookup datasource
		try 
		{
			if(TRANSPORT_DS == null)
			{
				initContext = new InitialContext();
				Context envCtx = (Context) initContext.lookup("java:comp/env");
				TRANSPORT_DS = (DataSource) envCtx.lookup("jdbc/TransportDev");
			}

		} 
		catch (NamingException e) 
		{
			//DHGlobalUtils.error(e.getMessage(),log) ;
			if (log.isEnabledFor(Level.ERROR)) log.error(e.getMessage());
		}

	}


	/**
	 * 
	 * @return Returns DB Connection object or null
	 */
	public static Connection  getJDBCConnection()
	{
		//define connection variable here
		Connection dbCon = null;

		try
		{
			//initialise datasource variable if null
			if(TRANSPORT_DS == null)
			{
				dataSourceInitialization();
			}

			//get connection from datasoruce variable
			dbCon = TRANSPORT_DS.getConnection();
			if (dbCon == null) {

				log.error("Exception while getting the connection object.");
				throw new TransportException(
						ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
								TransportGlobalErrorMessageMap.CONNECTION_OBJECT_NULL));
			}
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
		}

		return dbCon;
	}
	
	/**
	 * Closing all objected created in DAO Classes
	 * @param rs
	 * @param stmt
	 * @param dbCon
	 * @throws DHException
	 */
	public static void closeAllDBObjects(ResultSet rs, Statement stmt, Connection dbCon) throws TransportException {
		closeResultset(rs);
		closeStatment(stmt);
		closeConnection(dbCon);
	}
	
	public static void closeResultSetAndStatement(ResultSet rs, Statement stmt) throws TransportException {
		closeResultset(rs);
		closeStatment(stmt);
	}
	
	public static void validateCallableStatement(CallableStatement callableStatement) throws SQLException, TransportException {

		if(!callableStatement.getBoolean("outResult"))
		{
			log.error("errCode:"+callableStatement.getInt("errCode")
					+", message:"+callableStatement.getString("message"));
			throw new TransportException(
					ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
							callableStatement.getInt("errCode"), callableStatement.getString("message")));
		}
	}
	
	public static void validateCallableStatement(CallableStatement callableStatement, BaseResponse baseResponse) throws SQLException{
	
		if(!callableStatement.getBoolean("outResult"))
		{
			baseResponse.setResult(false);
			baseResponse.setErrorMessage(callableStatement.getString("message"));
			baseResponse.setErrorCode(callableStatement.getInt("errCode"));
		}
	}
	
	/**
	 * Close resultset
	 * @param rs
	 * @throws DHException
	 */
	public static void closeResultset(ResultSet rs) throws TransportException {
		
		if (rs != null) {
			try 
			{
				rs.close();
			} 
			catch (SQLException e) 
			{	
				log.error("Exception while closing resultset: "+e.getMessage());
				throw new TransportException(
						ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
								TransportGlobalErrorMessageMap.EXCEPTION, e.getMessage()));
			}
		}
	}
	
	/**
	 * Close statment
	 * @param stmt
	 * @throws DHException
	 */
	public static void closeStatment(Statement stmt) throws TransportException {
		
		if (stmt != null) {
			try 
			{
				stmt.close();
			} 
			catch (SQLException e) 
			{	
				log.error("Exception while closing statment: "+e.getMessage());
				throw new TransportException(
						ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
								TransportGlobalErrorMessageMap.EXCEPTION, e.getMessage()));
			}
		}
	}
	
	/**
	 * Close Connection object
	 * @param dbCon
	 * @throws DHException
	 */
	public static void closeConnection(Connection dbCon) throws TransportException {

		if (dbCon != null) {
			try 
			{
				dbCon.close();
			} 
			catch (SQLException e) 
			{	
				log.error("Exception while closing connection: "+e.getMessage());
				throw new TransportException(
						ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
								TransportGlobalErrorMessageMap.EXCEPTION, e.getMessage()));
			}
		}
	}

	
	/*
	 * This is a common method to call stored procedures, set SP params
	 * dynamically and returns resultset.
	 */
	public static HashMap<String, Object> JDBCOperation(LinkedHashMap<String, Object> reqParams, Connection connection) throws SQLException 
	{
			
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		HashMap<String, Object> response = new HashMap<String, Object>();
		String errorMessage = null;
		String operation =null;
			
		try {
			// get connection
			//connection = getJDBCConnection();
			
			/*if (connection == null) {
				response.put("result", false);
				response.put("errorCode", DHGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				errorMessage = DHGlobalErrorMessageMap.getMessage(DHGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				response.put("errorMessage", errorMessage);
				//DHGlobalUtils.error(errorMessage, log);
				if (log.isEnabledFor(Level.ERROR)) log.error(errorMessage);
				return response;
			}*/
			
			connection.setAutoCommit(false);
			
			if(!reqParams.isEmpty())
			{
				operation = reqParams.get("operation").toString();
				callableStatement = connection.prepareCall((String)reqParams.get("storedProcName"));
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);

				//removing storedproc name and operation
				reqParams.remove("storedProcName");
				reqParams.remove("operation");
				// Get input params and set to stored Procedure
				Iterator<Map.Entry<String, Object>> iterator = reqParams.entrySet().iterator();

				while (iterator.hasNext()) {
					Map.Entry<String, Object> me = iterator.next();
					if (me.getValue() instanceof Double) {
						callableStatement.setDouble(me.getKey(), (Double) me.getValue());
					} else if (me.getValue() instanceof Float) {
						callableStatement.setFloat(me.getKey(), (Float) me.getValue());
					} else if (me.getValue() instanceof String) {
						callableStatement.setString(me.getKey(), (String) me.getValue());
					} else if (me.getValue() instanceof Integer) {
						callableStatement.setInt(me.getKey(), (Integer) me.getValue());
					} else if (me.getValue() instanceof Date) {
						callableStatement.setDate(me.getKey(), (Date) me.getValue());
					}
				}
				System.out.println(callableStatement);
				// checking operation type select or update
				if(operation.equals("select")){
					resultSet = callableStatement.executeQuery();
					
					connection.commit();
					
					//executeUpdate = callableStatement.getBoolean("outresult");
					response.put("resultSet", resultSet);
				}else{
					int countFromExecuteUpdate =callableStatement.executeUpdate();
					connection.commit();
					//executeUpdate = callableStatement.getBoolean("outresult");
					response.put("countFromExecuteUpdate",countFromExecuteUpdate);
				}
				
				/*if (!callableStatement.getBoolean("outResult")) {
					
					response.put("result", false);
					response.put("errorCode", DHGlobalErrorMessageMap.DB_EXECUTION_ERROR);
					Object error = callableStatement.getString("errCode") + callableStatement.getString("message");
					response.put("errorMessage", error);
					//DHGlobalUtils.error(callableStatement.getString("message"), log);
					if (log.isEnabledFor(Level.ERROR)) {
						log.error(callableStatement.getString("message")); 
					}
					return response;
				} else {
					//response.put("outResult",executeUpdate);
					response.put("result", true);
				}*/
				
				if(!callableStatement.getBoolean("outResult"))
				{
					response.put("result", false);
					log.error("errCode:"+callableStatement.getInt("errCode")
							+", message:"+callableStatement.getString("message"));
					throw new TransportException(
							ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
									callableStatement.getInt("errCode"), callableStatement.getString("message")));
					
				}else
				{
					response.put("result", true);
				}
				
				
			}else{
				response.put("result", false);
				response.put("errorCode", TransportGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				errorMessage = TransportGlobalErrorMessageMap.getMessage(TransportGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				response.put("errorMessage", errorMessage);
				//DHGlobalUtils.error(errorMessage, log);
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(errorMessage); 
				}
				return response;
			}

		} catch (Exception ex) {
			connection.rollback();
			log.error(ex.getMessage());
		}

		return response;
	}// jdbc operation end
	/*
	 * This is a common method to call stored procedures, set Stored Procedure params
	 * dynamically and returns resultset and out params
	 */
	public static HashMap<String, Object> JDBCOperation(LinkedHashMap<String, Object> reqParams,LinkedHashMap<String, Object> outParams, Connection connection) throws SQLException 
	{
			
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		HashMap<String, Object> response = new HashMap<String, Object>();
		String errorMessage = null;
		String operation =null;
		
		try {
			// get connection
			connection = getJDBCConnection();
			
			/*if (connection == null) {
				response.put("result", false);
				response.put("errorCode", DHGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				errorMessage = DHGlobalErrorMessageMap.getMessage(DHGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				response.put("errorMessage", errorMessage);
				//DHGlobalUtils.error(errorMessage, log);
				if (log.isEnabledFor(Level.ERROR)) log.error(errorMessage);
				return response;
			}*/
			
			connection.setAutoCommit(false);
			
			if(!outParams.isEmpty())
			{
				Iterator<Map.Entry<String, Object>> outIterator = outParams.entrySet().iterator();

				while (outIterator.hasNext()) {
					Map.Entry<String, Object> me = outIterator.next();
					callableStatement = connection.prepareCall((String)reqParams.get("storedProcName"));
					callableStatement.registerOutParameter(me.getKey(), java.sql.Types.INTEGER);
					
				}
				callableStatement.registerOutParameter("outResult", java.sql.Types.BOOLEAN);
				callableStatement.registerOutParameter("errCode", java.sql.Types.CHAR);
				callableStatement.registerOutParameter("message", java.sql.Types.VARCHAR);
			}
			
			
			if(!reqParams.isEmpty())
			{
				operation = reqParams.get("operation").toString();
				
				

				//removing storedproc name and operation
				reqParams.remove("storedProcName");
				reqParams.remove("operation");
				// Get input params and set to stored Procedure
				Iterator<Map.Entry<String, Object>> reqIterator = reqParams.entrySet().iterator();

				while (reqIterator.hasNext()) {
					Map.Entry<String, Object> me = reqIterator.next();

					if (me.getValue() instanceof Double) {
						callableStatement.setDouble(me.getKey(), (Double) me.getValue());
					} else if (me.getValue() instanceof String) {
						callableStatement.setString(me.getKey(), (String) me.getValue());
					} else if (me.getValue() instanceof Float) {
						callableStatement.setFloat(me.getKey(), (Float) me.getValue());
					} else if (me.getValue() instanceof Integer) {
						callableStatement.setInt(me.getKey(), (Integer) me.getValue());
					} else if (me.getValue() instanceof Date) {
						callableStatement.setDate(me.getKey(), (Date) me.getValue());
					}
				}
					System.out.println(callableStatement);
				// checking operation type select or update
				if(operation.equals("select")){
					resultSet = callableStatement.executeQuery();
					
					connection.commit();
				
					response.put("resultSet", resultSet);
				}else{
					int countFromExecuteUpdate =callableStatement.executeUpdate();
					connection.commit();
					//executeUpdate = callableStatement.getBoolean("outresult");
					response.put("countFromExecuteUpdate",countFromExecuteUpdate);
					
				}
				
				if (!callableStatement.getBoolean("outResult")) {
					response.put("result", false);
					log.error("errCode:"+callableStatement.getInt("errCode")
					+", message:"+callableStatement.getString("message"));
					throw new TransportException(
							ExceptionHelper.prepareExceptionInfo(ExceptionLevelEnum.DATA_LEVEL, 
							callableStatement.getInt("errCode"), callableStatement.getString("message")));
			
					
				} else {
					
					if(!outParams.isEmpty())
					{
						Iterator<Map.Entry<String, Object>> respOutIterator = outParams.entrySet().iterator();

						while (respOutIterator.hasNext()) {
							Map.Entry<String, Object> respMap = respOutIterator.next();
							
							if(respMap.getValue() instanceof Integer)
								response.put(respMap.getKey(),callableStatement.getInt(respMap.getKey()));
							else if(respMap.getValue() instanceof Double)
								response.put(respMap.getKey(),callableStatement.getDouble(respMap.getKey()));
							else if(respMap.getValue() instanceof String)
								response.put(respMap.getKey(),callableStatement.getString(respMap.getKey()));							
						}
						
					}
					response.put("outResult",callableStatement.getString("outResult"));
					response.put("errCode",callableStatement.getString("errCode"));
					response.put("message",callableStatement.getString("message"));
					
				}
				
			}else{
				response.put("result", false);
				response.put("errorCode", TransportGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				errorMessage = TransportGlobalErrorMessageMap.getMessage(TransportGlobalErrorMessageMap.CONNECTION_OBJECT_NULL);
				response.put("errorMessage", errorMessage);
				//DHGlobalUtils.error(errorMessage, log);
				if (log.isEnabledFor(Level.ERROR)) {
					log.error(errorMessage); 
				}
				return response;
			}

		} catch (Exception ex) {
			connection.rollback();
			log.error(ex.getMessage());
		}

		return response;
	}// jdbc operation end
	
}