package com.example.mongodbdemo.io.request;
import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class StudentInput {

    @NotNull(message = "学号不能为空")
    @Min(value = 1, message = "学号为正整数")
    private Integer studentNo;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 1, message = "年龄最小为1岁")
    private Integer age;

    @NotBlank(message = "出生日期不能为空")
    private String birthday;

    @NotNull(message = "课程列表不能为空")
    @Size(min = 1, message = "每个学生至少上一门课程")
    private List<String> subjects;

    @NotNull(message = "兴趣爱好列表不能为空")
    @Size(min = 1, message = "每个学生至少有一个兴趣爱好")
    private List<String> interests;

}
