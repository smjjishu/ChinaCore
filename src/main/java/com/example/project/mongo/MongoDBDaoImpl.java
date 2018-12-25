package com.example.project.mongo;

import com.example.project.model.AAAInfo;
import com.example.project.model.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class MongoDBDaoImpl implements MongoDBService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     *
     * @param user
     */
    @Override
    public void saveUser(AAAInfo user) {
        mongoTemplate.save(user);
    }

    @Override
    public void saveLogInfo(LogInfo log) {
        mongoTemplate.save(log);
    }

    /**
     * 添加对象 List
     */
    @Override
    public void saveUserList(List<AAAInfo> list) {
        mongoTemplate.insert(list, AAAInfo.class);
    }


    /**
     * 根据用户名查询对象
     */
    @Override
    public AAAInfo findUserByAction(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        AAAInfo user = mongoTemplate.findOne(query, AAAInfo.class);
        return user;
    }

    @Override
    public AAAInfo findUserById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        AAAInfo user = mongoTemplate.findOne(query, AAAInfo.class);
        return user;
    }

    @Override
    public List<AAAInfo> findUserByRemark(String remark) {
        Query query = new Query(Criteria.where("remark").is(remark));
        List<AAAInfo> list = mongoTemplate.find(query, AAAInfo.class);
        return list;
    }


    @Override
    public List<AAAInfo> findUsers() {
        Query query = new Query();
        // query.addCriteria(Criteria.where("remark").is("hello1"));
        Criteria criteria1 = Criteria.where("name").nin("");
        Criteria criteria2 = Criteria.where("name").exists(true);
        Criteria criteria = new Criteria().orOperator(criteria1, criteria2);
        query.addCriteria(criteria);
        List<AAAInfo> list = mongoTemplate.find(query, AAAInfo.class);
        return list;
    }

    /**
     * 更新对象
     *
     * @param user
     */
    @Override
    public void updateUser(AAAInfo user) {
        Query query = new Query(Criteria.where("nameId").is(user.getNameId()));
        Update update = new Update().set("content", user.getContent()).set("remark", user.getRemark()).set("name", user.getName()).set("createtime", new Date());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, AAAInfo.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    @Override
    public void deleteUserById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, AAAInfo.class);
    }

    @Override
    public void RemoveAll() {
        mongoTemplate.dropCollection(AAAInfo.class);
    }
}