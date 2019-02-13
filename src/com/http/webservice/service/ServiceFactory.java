package com.http.webservice.service;

import com.http.webservice.service.impl.ClientServiceImpl;
import com.http.webservice.service.impl.LibrarianServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private ClientService clientService = new ClientServiceImpl();
    private LibrarianService librarianService= new LibrarianServiceImpl();

    private ServiceFactory() {
    }

    public LibrarianService getLibrarianService() {
        return librarianService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
