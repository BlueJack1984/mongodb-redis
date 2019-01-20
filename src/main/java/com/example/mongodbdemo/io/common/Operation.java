package com.example.mongodbdemo.io.common;

import org.apache.commons.lang3.StringUtils;

public enum  Operation {
    /**
     * 枚举值
     */
    INSERT("insert", 1), DELETE("delete", 2),
    UPDATE("update", 3), SELECT("select", 4);

    /**
     * 私有变量
     */
    private String name;
    private Integer index;
    /**
     * 私有构造方法
     */
    private Operation(String name, int index) {
        this.name = name;
        this.index = index;
    }
    /**
     * 根据名称获取序列值
     */
    public static Integer getIndexByName(String name) {
        if(StringUtils.isEmpty(name)) {
            return null;
        }
        for(Operation operation: Operation.values()) {
            if(name.equals(operation.name)) {
                return operation.index;
            }
        }
        return null;
    }
    /**
     * 根据序列值获取名称
     */
    public static String getNameByIndex(Integer index) {
        if(null == index) {
            return "";
        }
        for(Operation operation: Operation.values()) {
            if(index.intValue() == operation.index) {
                return operation.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
