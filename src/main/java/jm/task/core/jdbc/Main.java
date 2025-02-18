package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Мария", "Петрова", (byte) 20);
        userService.saveUser("Иван", "Иванов", (byte) 30);
        userService.saveUser("Михаил", "Васильев", (byte) 40);
        userService.saveUser("Екатерина", "Сергеева", (byte) 50);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}