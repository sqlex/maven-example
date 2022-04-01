package me.danwi.sqlex.example;

import me.danwi.sqlex.core.DatabaseManager;
import me.danwi.sqlex.example.dao.RoleDao;
import me.danwi.sqlex.example.dao.UserDao;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        UserDao userDao = databaseManager.getInstance(UserDao.class);
        RoleDao roleDao = databaseManager.getInstance(RoleDao.class);

        userDao.getAll(10, "123", null);
        userDao.getAll(10, "123", new ArrayList());
        userDao.getAllByRole("123");
        userDao.getCountsByRole();

        roleDao.getAll("管");
    }
}
