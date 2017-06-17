package dbutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class DBConfig {

	private Properties prop;
	private ClassLoader loader;
	private String pathCorrection;
	private String driverName;
	private String path2DB;
	private String login;
	private String password;

	DBConfig() {
		readPropertyFile();
	}

	/**
	 * Reading property file
	 */
	private void readPropertyFile() {

		prop = new Properties();
		loader = Thread.currentThread().getContextClassLoader();

		InputStream resourceStream = loader.getResourceAsStream("config.properties");
		try {
			prop.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		driverName = prop.getProperty("driverName");
		path2DB = prop.getProperty("path2DB");
		String useSSL = "useSSL=" + prop.getProperty("useSSL");
		String useUnicode = "useUnicode=" + prop.getProperty("useUnicode");
		String useJDBCCompliantTimezoneShift = "useJDBCCompliantTimezoneShift="
				+ prop.getProperty("useJDBCCompliantTimezoneShift");
		String useLegacyDatetimeCode = "useLegacyDatetimeCode=" + prop.getProperty("useLegacyDatetimeCode");
		String serverTimezone = "serverTimezone=" + prop.getProperty("serverTimezone");

		pathCorrection = useSSL + "&" + useUnicode + "&" + useJDBCCompliantTimezoneShift + "&" + useLegacyDatetimeCode
				+ "&" + serverTimezone;
		login = prop.getProperty("login");
		password = prop.getProperty("password");

		if ((pathCorrection != null) && (!pathCorrection.equals(""))) {
			path2DB = path2DB + "?" + pathCorrection;
		}
	}

	String getDriverName() {
		return driverName;
	}

	String getPath2DB() {
		return path2DB;
	}

	String getLogin() {
		return login;
	}

	String getPassword() {
		return password;
	}
	
	

}
