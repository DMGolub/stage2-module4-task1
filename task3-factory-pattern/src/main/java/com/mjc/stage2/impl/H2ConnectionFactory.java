package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {

	private static final String PROPERTIES_FILE_NAME = "h2database.properties";
	private static final String URL_PROPERTY_NAME = "db_url";
	private static final String USER_PROPERTY_NAME = "user";
	private static final String PASSWORD_PROPERTY_NAME = "password";

	@Override
	public Connection createConnection() {
		String path = this.getClass().getClassLoader().getResource(PROPERTIES_FILE_NAME).getPath();
		try (FileInputStream stream = new FileInputStream(path)) {
			Properties properties = new Properties();
			properties.load(stream);
			final String url = properties.getProperty(URL_PROPERTY_NAME);
			final String user = properties.getProperty(USER_PROPERTY_NAME);
			final String password = properties.getProperty(PASSWORD_PROPERTY_NAME);
			return DriverManager.getConnection(url, user, password);
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}