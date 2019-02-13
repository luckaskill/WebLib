package com.http.webservice;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        String[] pizzas = {"Pepperoni", "4 Seasons", "Mushroom", "Hawai"};
            System.out.println(pizzas[random.nextInt(pizzas.length)]);
//        SQLLibraryDAO userDAO = new SQLLibraryDAO();
//        try {
//            System.out.println(userDAO.findUserBooks(1));
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        SQLVendorDAO sqlVendorDAO = new SQLVendorDAO();
//        try {
//            sqlVendorDAO.rentABook("Java core", "Cay S. Horstmann", 2018,
//                    30.8f, 87, 1);
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }


//        ConnectionPool pool = null;
//        try {
//            pool = ConnectionPool.getInstance();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        Connection connection = null;
//        try {
//            connection = pool.takeConnection();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
//        PreparedStatement st;
//


    }
}
