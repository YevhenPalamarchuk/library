package dbutil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	private Connection connection;
	private Properties prop;
	private ClassLoader loader;
	private StringBuilder pathCorrection;
	private String driverName;
	private String path2DB;
	private String login;
	private String password;
	
	public Connection Connect2DB() throws Exception	{
		prop = new Properties();
		loader = Thread.currentThread().getContextClassLoader();
		pathCorrection = new StringBuilder();
		
		try(InputStream resourceStream = loader.getResourceAsStream("config.properties")){
			prop.load(resourceStream);
			driverName = prop.getProperty("driverName");
			path2DB = prop.getProperty("path2DB");
			login = prop.getProperty("login");
			password = prop.getProperty("password");
			
			getPathProperties("useSSL");
			getPathProperties("useUnicode");
			getPathProperties("useJDBCCompliantTimezoneShift");
			getPathProperties("useLegacyDatetimeCode");
			getPathProperties("serverTimezone");
			
			
			if((pathCorrection!=null)&&(!pathCorrection.equals(""))){
				path2DB = path2DB + "?" + pathCorrection;
			}
			
			Class.forName(driverName);
		}
		
		connection = DriverManager.getConnection(path2DB, login, password);
				
		prop = null;
		loader = null;
		pathCorrection = null;
		driverName = null;
		path2DB = null;
		login = null;
		password = null;
		
		return connection;
	}
	private void getPathProperties(String NameOfProperty){
		String propData=prop.getProperty(NameOfProperty);
		if (pathCorrection!=null){
			if (propData!=""){
				pathCorrection.append(pathCorrection +"&"+ NameOfProperty + "=" + propData);
				
			}
		}else{
			pathCorrection.append(NameOfProperty + "=" + propData);
		}
		propData=null;
		NameOfProperty=null;
	}
}
