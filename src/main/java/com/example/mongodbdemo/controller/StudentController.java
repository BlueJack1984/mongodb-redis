package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.entity.Student;
import com.example.mongodbdemo.io.request.StudentInput;
import com.example.mongodbdemo.io.response.OutputListResult;
import com.example.mongodbdemo.io.response.OutputResult;
import com.example.mongodbdemo.service.IStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 新增一条数据到mongodb
     * @return
     */
    @PostMapping("/save")
    public OutputResult<Student> save(@RequestBody @Valid StudentInput studentInput) throws Exception {
        Student student = new Student();
        copyProperties(student, studentInput);
        Student saved = studentService.save(student);
        return new OutputResult<>(saved);
    }

    /**
     * 拷贝属性
     * @param target
     * @param source
     * @throws Exception
     */
    private void copyProperties(Student target, StudentInput source)throws Exception {
        if(target == null || source == null) {
            return;
        }
        target.setStudentNo(source.getStudentNo());
        if(! StringUtils.isEmpty(source.getName())) {
            target.setName(source.getName());
        }
        if(null != source.getAge()) {
            target.setAge(source.getAge());
        }
        Date birthday = SDF.parse(source.getBirthday());
        if(null != birthday) {
            target.setBirthday(birthday);
        }
        if(null != source.getSubjects()) {
            target.setSubjects(source.getSubjects());
        }
        if(null != source.getInterests()) {
            target.setInterests(source.getInterests());
        }
    }

    /**
     *查询一条数据
     */
    @GetMapping("/get/{studentNo}")
    public OutputResult<Student> get(@PathVariable Integer studentNo) throws Exception {
        Student student = studentService.get(studentNo);
        return new OutputResult<>(student);
    }
    /**
     *查询数据列表
     */
    @GetMapping("/get/list")
    public OutputListResult<Student> getList() throws Exception {
        List<Student> studentList = studentService.getList();
        return new OutputListResult<>(studentList);
    }

    @PostMapping("/update")
    public OutputResult<Student> update(@RequestBody StudentInput studentInput) throws Exception {
        Integer studentNo = studentInput.getStudentNo();
        if(null == studentNo) {
            return new OutputResult<>(201, "学号参数不存在");
        }
        Student student = studentService.get(studentNo);
        if(null == student) {
            return new OutputResult<>(201, "该学号查询不到学生信息");
        }
        copyProperties(student, studentInput);
        Student updated = studentService.update(student);
        return new OutputResult<>(updated);
    }

    @GetMapping("/delete/{studentNo}")
    public OutputResult<Void> delete(@PathVariable Integer studentNo) throws Exception {
        studentService.delete(studentNo);
        return new OutputResult<>();
    }
}
