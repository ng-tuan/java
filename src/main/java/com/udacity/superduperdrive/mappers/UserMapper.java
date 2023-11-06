package com.udacity.superduperdrive.mappers;

import com.udacity.superduperdrive.models.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User getUser(String userName);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{userName}, #{salt}, #{passWord}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);
}
