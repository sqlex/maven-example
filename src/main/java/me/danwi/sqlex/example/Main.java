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

        //数据库结构迁移
        factory.migrate();
        //数据库结构检查
        factory.check();

        UserDao userDao = factory.getInstance(UserDao.class);
        RoleDao roleDao = factory.getInstance(RoleDao.class);

        System.out.println(userDao.findAll(10, "123", null));
        System.out.println(userDao.findAll(10, "123", new ArrayList()));
        System.out.println(userDao.findAllByRole("123"));
        System.out.println(userDao.findCountsByRole());

        System.out.println(roleDao.findAll("管"));
    }
}
