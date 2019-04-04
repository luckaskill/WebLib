package com.http.webservice;

import com.http.webservice.dao.SQLManager;
import com.http.webservice.dao.pool.ConnectionPool;

public class Main extends SQLManager {
    public static void main(String[] args) {

        ConnectionPool pool = new ConnectionPool();

    }
}
