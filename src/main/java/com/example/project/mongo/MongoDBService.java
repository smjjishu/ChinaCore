package com.example.project.mongo;


import com.example.project.model.AAAInfo;
import com.example.project.model.LogInfo;

import java.util.List;

public interface MongoDBService {


    void saveLogInfo(LogInfo log);

    /**
     * 添加对象
     */
    void saveUser(AAAInfo user);

    /**
     * 添加对象 List
     */
    void saveUserList(List<AAAInfo> list);

    /**
     * 根据用户名查询对象
     */
    AAAInfo findUserByAction(String name);

    AAAInfo findUserById(String id);

    List<AAAInfo> findUserByRemark(String remark);

    /**
     * 更新对象
     */
    void updateUser(AAAInfo user);

    /**
     * 删除对象
     */
    void deleteUserById(String id);

    List<AAAInfo> findUsers();

    void RemoveAll();
}
