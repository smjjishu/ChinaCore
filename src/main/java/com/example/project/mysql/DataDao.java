package com.example.project.mysql;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.project.model.DTO_User;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface DataDao extends BaseMapper<DTO_User>  {
    Integer AddInfo(DTO_User model);
    Integer AddInfoList(List<DTO_User> list);
    Integer ModifyInfo(DTO_User model);
    Integer DeleteInfo(@Param("id")Integer id);
    List<DTO_User> QueryInfo(@Param("name")String namestr);

    List<DTO_User> QueryInfoByName(@Param("name")String namestr);


    List<String> Querylist();
    List<Integer> QueryIdlist();
    List<DTO_User> getList(List<Integer> list);

    Integer saveBook(@Param("title")String title,@Param("author")String author);

    Integer saveBookTest(@Param("code")String code,@Param("name")String name);

    Integer saveBookInfo(@Param("code")String code,@Param("name")String name,@Param("date")String date);
}
