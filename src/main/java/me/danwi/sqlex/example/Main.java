package me.danwi.sqlex.example;

import me.danwi.sqlex.core.DaoFactory;
import me.danwi.sqlex.example.dao.RoleDao;
import me.danwi.sqlex.example.dao.UserDao;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        DaoFactory factory = new DaoFactory(
                "jdbc:mysql://localhost:3306/sqlex",
                "root", "1234qwer",
                Repository.class
        );

        UserDao userDao = factory.getInstance(UserDao.class);
        RoleDao roleDao = factory.getInstance(RoleDao.class);

        System.out.println(userDao.getAll(10, "123", null));
        System.out.println(userDao.getAll(10, "123", new ArrayList()));
        System.out.println(userDao.getAllByRole("123"));
        System.out.println(userDao.getCountsByRole());

        System.out.println(roleDao.getAll("管"));
    }
}
