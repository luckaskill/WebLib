package com.http.webservice;

import com.http.webservice.controller.command.CommandProvider;
import com.http.webservice.dao.HibernateSessionFactoryUtil;
import com.http.webservice.dao.SQLManager;
import com.http.webservice.dao.impl.SQLVendorDAO;
import com.http.webservice.entity.SpringApplicationContext;
import com.http.webservice.exception.ValidationException;
import com.http.webservice.service.impl.ClientServiceImpl;
import lombok.Cleanup;
import lombok.Getter;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Main extends SQLManager {
    @Getter
    public static ApplicationContext context = new AnnotationConfigApplicationContext("com.http.webservice");
    public static void main(String[] args) {
//        SQLAdministrationDAO sqlAdministrationDAO= new SQLAdministrationDAO();
//            SQLUserDAO sqlUserDAO= new SQLUserDAO();
//            sqlUserDAO.authorization("las", "sal");

//        try {
//            sqlAdministrationDAO.changeUserAccessLevel("Promote",2,"Promote");
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        CommandProvider provider = new CommandProvider(context);
//        sqlVendorDAO.main();
//        ArrayList<Book> books = new ArrayList<>();
//        User user = session.get(User.class, Long.parseLong(String.valueOf(1)));
//        System.out.println(user.getRentBooks());
//        Transaction tr=session.beginTransaction();
//        user.getRentBooks().add(book);
//        tr.commit();
    }
}
