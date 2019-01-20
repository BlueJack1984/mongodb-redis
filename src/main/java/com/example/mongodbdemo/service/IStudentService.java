package com.example.mongodbdemo.service;

import com.example.mongodbdemo.entity.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;

public interface IStudentService {
    /**
     * 新建保存一条数据到mongodb
     * @param student
     * @return
     * @throws Exception
     */
    @CacheEvict(value = "student", key = "'student_list'", condition = "")
    Student save(Student student) throws Exception;

    /**
     * 查询一条数据
     * @param studentNo
     * @return
     * @throws Exception
     */
    @Cacheable(value = "student", key = "'student_number_' + #studentNo", condition = "")
    Student get(Integer studentNo) throws Exception;

    /**
     * 查询数据列表
     * @return
     * @throws Exception
     */
    @Cacheable(value = "student", key = "'student_list'", condition = "")
    List<Student> getList() throws Exception;

    /**
     * 更新数据
     * @param student
     * @return
     * @throws Exception
     */
    @Caching(evict = { @CacheEvict(value = "student", key = "'student_list'", condition = ""),
            @CacheEvict(value = "student", key = "'student_number_' + #student.studentNo", condition = "")})
    Student update(Student student) throws Exception;

    /**
     * 根据studentNo删除数据
     * @param studentNo
     * @throws Exception
     */
    @Caching(evict = { @CacheEvict(value = "student", key = "'student_list'", condition = ""),
            @CacheEvict(value = "student", key = "'student_number_' + #studentNo", condition = "")})
    void delete(Integer studentNo) throws Exception;

}
