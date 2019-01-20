package com.example.mongodbdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="student")
public class Student implements Serializable {

    private Integer studentNo;
    private String name;
    private Integer age;
    private Date birthday;
    private List<String> subjects;
    private List<String> interests;
    private Date createTime;
    private Date updateTime;
}
