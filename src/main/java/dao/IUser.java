package dao;

import models.User;
import org.apache.ibatis.annotations.Select;

public interface IUser {
    @Select("select * from user where id= #{id}")
    public User getUserByID(int id);
}
