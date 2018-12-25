package com.example.project.mysql;


import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.project.model.DTO_User;
import com.example.project.comm.PageDataVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Primary
@Service
public class DataDaoServiceImpl extends ServiceImpl<DataDao, DTO_User> implements DataDaoService {


    @Override
    public Integer AddUser(DTO_User model) { return this.baseMapper.AddInfo(model); }

    @Override
    public Integer AddUserList(List<DTO_User> list) { return this.baseMapper.AddInfoList(list); }


    @Override
    public Integer ModifyUser(DTO_User model) {
        return this.baseMapper.ModifyInfo(model);
    }

    @Override
    public Integer DeleteUser(Integer id) {
        return this.baseMapper.DeleteInfo(id);
    }

    @Override
    public List<DTO_User> queryUser(String name) {
        return this.baseMapper.QueryInfo(name);
    }

    @Override
    public List<DTO_User> QueryInfoByName(String name) {
        return this.baseMapper.QueryInfoByName(name);
    }


    @Override
    public PageDataVO<List<DTO_User>>  getUserList(String name, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<DTO_User> list = this.baseMapper.QueryInfo(name);
        int totalCount = (int)PageHelper.freeTotal();
        PageDataVO pageDataVO = PageDataVO.init(currentPage, pageSize, totalCount, list);
        return pageDataVO;
    }

    @Override
    public  List<String> querylist() {
        return this.baseMapper.Querylist();
    }

    @Override
    public  List<Integer> queryIDlist()
    {
        return this.baseMapper.QueryIdlist();
    }

    @Override
    public List<DTO_User> getList(List<Integer> ids)
    {
        return this.baseMapper.getList(ids);
    }

    @Transactional
    @Override
    public Integer saveBook(String title,String author)
    {
        return  this.baseMapper.saveBook(title,author);
    }

    @Transactional
    @Override
    public Integer saveBookTest(String code,String name)
    {
        return  this.baseMapper.saveBookTest(code,name);
    }



    @Transactional
    @Override
    public Integer saveBookInfo(String code,String name,String date)
    {
        return  this.baseMapper.saveBookInfo(code,name,date);
    }




}

