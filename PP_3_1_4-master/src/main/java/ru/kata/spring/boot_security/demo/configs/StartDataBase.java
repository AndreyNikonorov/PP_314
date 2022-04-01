package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class StartDataBase {
    private UserService userService;
    private RoleService roleService;

    public StartDataBase(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void initDB() {
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.addRole(roleUser);
        roleService.addRole(roleAdmin);
        User user = new User("user@gmail.com","user","user", "userov", (byte) 35);
        User admin = new User("admin@gmail.com","admin","admin", "adminovich", (byte) 21);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleUser);
        user.setRoleSet(Set.of(roleUser));
        admin.setRoleSet(Set.of(roleUser, roleAdmin));
        userService.addUser(user);
        userService.addUser(admin);
    }
}
