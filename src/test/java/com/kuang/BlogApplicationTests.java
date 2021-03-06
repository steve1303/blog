package com.kuang;

import com.alibaba.druid.pool.DruidDataSource;
import com.kuang.dao.*;
import com.kuang.pojo.Comment;
import com.kuang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    BlogMapper blogMapper;


    @Autowired
    TagMapper tagMapper;

    @Autowired
    BlogTagRelationMapper blogTagRelationMapper;

    @Autowired
    CommentMapper CommentMapper;

    /* user crud*/
    @Test
    void contextLoads() {
/*        List<User> allUsers = userMapper.getAllUsers();
        allUsers.forEach(System.out::println);

        User userById = userMapper.getUserById(2);
        System.out.println(userById);*/

        List userBlogs = userMapper.getUserBlogs(2);
        System.out.println(userBlogs);


/*        Date date = new Date();
        System.out.println(date);
        User user = new User();
        user.setUserId(8);
        user.setUserName("hello");
        user.setUserPassword("333999");
        user.setUserPhone("18295353379");
        user.setUserEmail("1303040142@qq.com");
        user.setUserCreatTime(date);
        user.setUserLocked(0);
        userMapper.addUser(user);*/


/*        Date date = new Date();
        User user2 = new User();
        user2.setUserId(5);
        user2.setUserName("88aa");
        user2.setUserPassword("666444");
        user2.setUserPhone("18295353378");
        user2.setUserEmail("3104128161@qq.com");
        user2.setUserCreatTime(date);
        user2.setUserLocked(1);
        userMapper.updateUser(user2);*/

        /*        userMapper.deleteUserById(3);*/
    }


    /* druid ?????????*/
    @Test
    void contextLoads2() throws SQLException {

        //?????????????????????
        System.out.println("01:" + dataSource.getClass());

        //?????????????????????
        Connection connection = dataSource.getConnection();
        System.out.println("02:" + connection);

        // xxx Template : SpringBoot??????????????????bean. ????????????
        // jdbc
        // redis
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource ???????????????????????????" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource ??????????????????????????????" + druidDataSource.getInitialSize());

        //??????
        connection.close();
    }


    /*service*/
    @Test
    void contextLoads3() {
        System.out.println(userMapper.queryUserByName("admin"));
        System.out.println(userService.queryUserByName("admin"));
    }

    @Test
    void contextLoads4(HttpSession session) {
        // ??????session??????????????????
        Enumeration<String> attrs = session.getAttributeNames();
        // ??????attrs??????
        while (attrs.hasMoreElements()) {
            // ??????session??????
            String name = attrs.nextElement().toString();
            // ???????????????session?????????
            Object vakue = session.getAttribute(name);
            // ????????????
            System.out.println("------" + name + ":" + vakue + "--------\n");

        }
    }


    @Test
    public void contetxtloads5() {
        List<Comment> allCommentsByBlogId = CommentMapper.getAllCommentsByBlogId(3);
        for (Comment comment : allCommentsByBlogId) {
            System.out.println(comment);
        }

    }

    @Test
    public void contetxtloads6() {
        String s = getClass()
                .getClassLoader()
                .getResource("/static/images/user/1649628649495.png")
                .getPath();
        System.out.println(s);
    }
}
