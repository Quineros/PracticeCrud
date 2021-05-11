package com.company;
import java.util.List;
import java.util.ArrayList;

public class UserStorage {
    private static List<User> userList = new ArrayList<>();
    public static List<User> getUserList(){
        return userList;
    }
    public static void addUser(User user){
        if(user != null) userList.add(user);
    }
    public static void deleteUser(User user){
        userList.remove(user);
    }
}
