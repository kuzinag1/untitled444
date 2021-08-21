package org.example;

import org.example.dao.PassportDao;
import org.example.dao.impl.PassportDaoImpl;
import org.example.model.Passport;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        PassportDao passportDao = new PassportDaoImpl();
        List<Passport> passportList = passportDao.findAll();
        passportList.forEach(System.out::println);
    }
}
