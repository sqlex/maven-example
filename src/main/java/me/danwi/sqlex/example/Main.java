package me.danwi.sqlex.example;

import me.danwi.sqlex.core.DataSource;
import me.danwi.sqlex.core.DataSourceManager;
import me.danwi.sqlex.example.dao.RoleDao;
import me.danwi.sqlex.example.dao.UserDao;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final DataSourceManager manager = new DataSourceManager();
        final DataSource<Repository> ds = manager.getInstance(Repository.class);

        UserDao userDao = ds.getInstance(UserDao.class);
        RoleDao roleDao = ds.getInstance(RoleDao.class);

        userDao.getAll(10, "123", null);
        userDao.getAll(10, "123", new ArrayList());
        userDao.getAllByRole("123");
        userDao.getCountsByRole();

        roleDao.getAll("管");
    }
}
