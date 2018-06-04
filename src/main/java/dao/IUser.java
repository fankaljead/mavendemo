package dao;

import models.User;

import java.util.List;


public interface IUser {
//    @Select("select * from user where id= #{id}")
//    public User getUserByID(int id);
    public List<User> getUserList();
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
    public User getUser(int id);
}
