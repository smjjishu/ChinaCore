package com.example.project.mysql;


import java.util.*;
import com.example.project.model.DTO_User;
import com.example.project.model.DTO_UserDetail;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from userinfo where namestr  = #{name}")
    List<DTO_User> findUserByName(@Param("name") String name);

    @Select("select * from userinfo where namestr  like  '%${name}%'")
    List<DTO_User> findUserListByName(@Param("name") String name);

    @Insert("insert into userinfo(namestr,age,email)values(#{name},#{age},#{email})")
    int InsertUser(@Param("name") String name,@Param("age") Integer age,@Param("email") String email);

    @Select("select  book.id,book.title,book.author,book.date,`userinfo`.`namestr`,`userinfo`.age,`userinfo`.email from book ,`userinfo` where book.id=`userinfo`.id and `userinfo`.id  = #{id}")
    List<DTO_UserDetail> findUserDetail(@Param("id") Integer id);

}