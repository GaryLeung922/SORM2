package com.xiaojiaqi.core;

/**
 * 负责java数据类型和数据库类型的互相转换
 */
public interface TypeConvertor {

    /**
     * 将数据库数据类型转化为java的数据类型
     * @param columnType       数据库字段的数据类型
     * @return  java的数据类型
     */
    public String databaseType2JavaType(String columnType);

    /**
     * 将java数据类型转化为数据库数据类型
     * @param javaDataType  java数据类型
     * @return  数据库类型
     */
    public String javaType2DatabaseType(String javaDataType);
}