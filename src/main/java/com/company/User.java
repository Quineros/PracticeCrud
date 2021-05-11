package com.company;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class User implements Crud<User>{
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int age;

    private String status;
    private List<User> friends = new ArrayList<>();
    private List<Gift> gifts = new ArrayList<>();

    private List<String> messageIn = new ArrayList<>();
    private List<String> messageOut = new ArrayList<>();
    private static int usersCount = 0;

    private int id=0;

    public String getName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getAge() {
        return this.age;
    }

    public final void setName(String name) {
        if(!name.isEmpty()) this.firstName = name;
        else throw new IllegalArgumentException("Invalid name");
    }

    public final void setLastName(String lastName) {
        if(!lastName.isEmpty()) this.lastName = lastName;
        else throw new IllegalArgumentException("Invalid surname");
    }

    public final void setEmail(String email) {
        this.email = email;
    }


    public final void setPassword(String password) {
        if(password.length() < 3) this.password = encoder.encode(password);
        else throw new IllegalArgumentException("Password must have 3 or more characters");
    }

    public final void setAge(int age) {
        if(age > 0) this.age = age;
        else throw new IllegalArgumentException("Invalid age");
    }

    @Override
    public void create(User user) {
        this.update(user);
        UserStorage.addUser(user);
        usersCount++;
    }

    @Override
    public void update(User user) {
        try {
            setName(user.firstName);
            setLastName(user.lastName);
            setAge(user.age);
            setPassword(user.password);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User find(Integer id) {
        List<User> users = UserStorage.getUserList();
        for (User user : users) {
            if (user.id == id) return user;
        }
        System.out.println("User not found");
        return null;
    }

    @Override
    public void delete(Integer id) {
        List<User> userList = UserStorage.getUserList();
        for (User user : userList) {
            if (user.id == id) UserStorage.deleteUser(user);
        }

    }

    public User(String name, String lastName, String email, String password, int age, String status, int id) {
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPassword(password);
        this.setAge(age);
        this.status = status;
        this.id = id;
        create(this);
    }
}
