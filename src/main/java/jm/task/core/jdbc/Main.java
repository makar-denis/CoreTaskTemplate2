package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Denis", "Markachev", (byte) 36);
        userService.saveUser("Dima", "Markachev", (byte) 13);
        userService.saveUser("Juliy", "Markacheva", (byte) 35);
        userService.saveUser("Margo", "dog", (byte) 6);
        userService.removeUserById(1);
        List<User> user = userService.getAllUsers();
        for (User value : user) {
            System.out.println(value);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
