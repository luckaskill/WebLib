package com.http.webservice.dao;

import java.util.ResourceBundle;

public class SQLManager {

    protected static final String url;
    protected static final String driver;
    protected static final String login;
    protected static final String password;
    protected static final String poolSize;

    private static final String DB_DRIVER = "db.driver";
    private static final String DB_URL = "db.url";
    private static final String DB_LOGIN = "db.login";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_POOL_SIZE = "db.poolsize";

    private static final String DB_PROPERTIES_FILE_PATH = "resources.db";

    static {
        ResourceBundle jdbcProperties = ResourceBundle.getBundle(DB_PROPERTIES_FILE_PATH);

        driver = jdbcProperties.getString(DB_DRIVER);
        url = jdbcProperties.getString(DB_URL);
        login = jdbcProperties.getString(DB_LOGIN);
        password = jdbcProperties.getString(DB_PASSWORD);
        poolSize = jdbcProperties.getString(DB_POOL_SIZE);
    }
}
