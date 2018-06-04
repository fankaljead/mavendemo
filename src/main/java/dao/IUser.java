package dao;

import models.User;
import org.apache.ibatis.annotations.Select;

/**
 * 使用注解方式之
 */
public interface IUser {
    @Select("select * from user where id= #{id}")
    public User getUserByID(int id);
}
