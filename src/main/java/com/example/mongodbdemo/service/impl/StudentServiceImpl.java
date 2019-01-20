package com.example.mongodbdemo.service.impl;

import com.example.mongodbdemo.entity.Student;
import com.example.mongodbdemo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Student save(Student student) throws Exception {
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());
        mongoTemplate.insert(student);
        return student;
    }
    /**
     * 查询一条数据
     * @param studentNo
     * @return
     * @throws Exception
     */
    @Override
    public Student get(Integer studentNo) throws Exception {
        Criteria criteria = Criteria.where("studentNo").is(studentNo);
        Query query = Query.query(criteria);
        Student student = mongoTemplate.findOne(query, Student.class);
        return student;
    }

    /**
     * 查询数据列表
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> getList() throws Exception {
        //Criteria criteria = Criteria.where()
        Query query = new Query();
        List<Student> studentList = mongoTemplate.find(query, Student.class);
        return studentList;
    }

    /**
     * 修改数据
     * @param student
     * @return
     * @throws Exception
     */
    @Override
    public Student update(Student student) throws Exception {
        student.setUpdateTime(new Date());
        Criteria criteria = Criteria.where("studentNo").is(student.getStudentNo());
        Query query = Query.query(criteria);
        Update update = new Update();
        copyToUpdate(student, update);
        mongoTemplate.updateFirst(query, update, Student.class);
        //更新所有的查询到的数据
        //mongoTemplate.updateMulti(query, update, Student.class);
        return student;
    }

    /**
     * 根据studentNo删除数据
     * @param studentNo
     * @throws Exception
     */
    @Override
    public void delete(Integer studentNo) throws Exception {
        Criteria criteria = Criteria.where("studentNo").is(studentNo);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, Student.class);
    }

    private void copyToUpdate(Student student, Update update) throws Exception {
        if(student == null || update == null) {
            return;
        }
        update.set("name", student.getName());
        update.set("age", student.getAge());
        update.set("birthday", student.getBirthday());
        update.set("subjects", student.getSubjects());
        update.set("interests", student.getInterests());
        update.set("createTime", student.getCreateTime());
        update.set("updateTime", student.getUpdateTime());
    }
}
