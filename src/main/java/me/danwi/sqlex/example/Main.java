package me.danwi.sqlex.example;

import me.danwi.sqlex.core.DaoFactory;
import me.danwi.sqlex.example.dao.*;

import static me.danwi.sqlex.core.query.expression.Expression.*;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DaoFactory factory = new DaoFactory(
                "jdbc:mysql://localhost:3306/sqlex?connectionTimeZone=SERVER",
                "root", "1234qwer",
                Repository.class
        );

        //数据库结构迁移
        factory.migrate();
        //数据库结构检查
        factory.check();

        UserTable userTable = factory.getInstance(UserTable.class);
        //删除所有用户
        userTable.delete().where(arg(true)).execute();
        //插入用户
        User user1 = User.builder().setId("fake id").setName("fake name").build();
        userTable.insertWithoutNull(user1);
        //查找
        User user2 = userTable.findById("fake id");
        assert user1.getId().equals(user2.getId());
        assert user1.getName().equals(user2.getName());
        //更新
        userTable.update().setAge(1).where(UserTable.Id.eq(arg("fake id"))).execute();
        User user3 = userTable.findById("fake id");
        assert user3.getAge().equals(1);
        //删除
        userTable.delete().where(UserTable.Id.eq(arg("fake id"))).execute();
        User user4 = userTable.findById("fake id");
        assert user4 == null;
        //保存并返回
        User user5 = userTable.save(User.builder().setId("fake id").setName("fake name").build());
        assert user5.getCreateAt() != null;
        assert user5.getAge() == null;
        //保存或更新
        user5.setAge(1);
        user5.setCreateAt(OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        User user6 = userTable.saveOrUpdate(user5);
        user6.setCreateAt(user6.getCreateAt().atZoneSameInstant(ZoneId.of("UTC")).toOffsetDateTime());
        assert user5.equals(user6);

        //sqlm查询
        UserDao userDao = factory.getInstance(UserDao.class);
        RoleDao roleDao = factory.getInstance(RoleDao.class);

        System.out.println(userDao.findAll(10, "123", null));
        System.out.println(userDao.findAll(10, "123", new ArrayList<>()));
        System.out.println(userDao.findAllByRole("123"));
        System.out.println(userDao.findCountsByRole());

        System.out.println(roleDao.findAll("管"));
    }
}
