package com.example.mongodbdemo.io.response;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @desc 通用返回实体列表，实现泛型
 * @author lushusheng
 * @date 2018-11-21
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OutputListResult<T> extends OutputResult<List<T>> {

    /**
     * 总记录数量
     */
    private long total;

    /**
     * 返回集合型数据
     */
    public OutputListResult(List<T> data){
        super(data);
        this.total = data.size();
    }
    /**
     * 返回逻辑分页结果
     */
//    public OutputListResult(Page<T> data){
//        super(data.getRecords());
//        this.total = data.getTotal();
//    }
    /**
     * 返回物理分页结果
     * 例如采用PageHelper插件实现分页
     */
    public OutputListResult(PageInfo<T> data) {
        super(data.getList());
        this.total = data.getTotal();
    }
}
