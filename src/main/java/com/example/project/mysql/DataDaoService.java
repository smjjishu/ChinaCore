package com.example.project.mysql;

import com.baomidou.mybatisplus.service.IService;
import com.example.project.model.DTO_User;
import com.example.project.comm.PageDataVO;


import java.util.List;

public interface DataDaoService extends IService<DTO_User>  {

     Integer AddUserList(List<DTO_User> list);

     Integer AddUser(DTO_User model);

     Integer ModifyUser(DTO_User model);

     Integer DeleteUser(Integer id);

     List<DTO_User> queryUser(String name);

     List<DTO_User> QueryInfoByName(String name);

     PageDataVO<List<DTO_User>>  getUserList(String name, int currentPage, int pageSize);

     List<String> querylist();

     List<Integer> queryIDlist();

     List<DTO_User> getList(List<Integer> ids);



     Integer saveBook(String title,String author);

     Integer saveBookTest(String code,String name);

     Integer saveBookInfo(String code,String name,String date);

}

