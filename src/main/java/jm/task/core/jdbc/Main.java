package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Мария", "Петрова", (byte) 25);
        userService.saveUser("Иван", "Иванов", (byte) 32);
        userService.saveUser("Екатерина", "Сергеева", (byte) 40);
        userService.saveUser("Михаил", "Васильев", (byte) 55);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();


        Util.closeConnection();

        Util.closeSessionFactory();
    }
}