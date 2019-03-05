package com.http.webservice;

import com.http.webservice.controller.command.CommandProvider;
import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.SQLManager;
import com.http.webservice.dao.impl.SQLAdministrationDAO;
import com.http.webservice.dao.impl.SQLLibraryDAO;
import com.http.webservice.dao.impl.SQLUserDAO;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.dao.pool.ConnectionPool;
import com.http.webservice.entity.Book;
import com.http.webservice.entity.User;
import com.http.webservice.exception.DAOException;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main extends SQLManager {
    public static void main(String[] args) {
//        SQLAdministrationDAO sqlAdministrationDAO= new SQLAdministrationDAO();
//            SQLUserDAO sqlUserDAO= new SQLUserDAO();
//            sqlUserDAO.authorization("las", "sal");

//        try {
//            sqlAdministrationDAO.changeUserAccessLevel("Promote",2,"Promote");
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        @Cleanup Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        SQLVendorDAO sqlVendorDAO=new SQLVendorDAO();
//        sqlVendorDAO.main();
//        ArrayList<Book> books = new ArrayList<>();
//        User user = session.get(User.class, Long.parseLong(String.valueOf(1)));
//        System.out.println(user.getRentBooks());
//        Transaction tr=session.beginTransaction();
//        user.getRentBooks().add(book);
//        tr.commit();
    }
}
