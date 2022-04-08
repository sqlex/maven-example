package me.danwi.sqlex.example;

import me.danwi.sqlex.core.DataSource;
import me.danwi.sqlex.core.DataSourceManager;
import me.danwi.sqlex.example.dao.RoleDao;
import me.danwi.sqlex.example.dao.UserDao;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final DataSourceManager manager = new DataSourceManager();
        final DataSource<Repository> dataSource = manager.getInstance(Repository.class);

        UserDao userDao = dataSource.getInstance(UserDao.class);
        RoleDao roleDao = dataSource.getInstance(RoleDao.class);

        userDao.findAll(10, "123", null);
        userDao.findAll(10, "123", new ArrayList());
        userDao.findAllByRole("123");
        userDao.findCountsByRole();

        roleDao.findAll("管");
    }
}
