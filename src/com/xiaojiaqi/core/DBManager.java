package com.xiaojiaqi.core;

import com.xiaojiaqi.bean.Configuration;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * 根据配置信息，维持连接对象的管理（增加连接池功能）
 */
public class DBManager {

    private static Configuration conf;

    static {//静态代码块,只加载一次
        Properties pros = new Properties();

        try {
            pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //加载配置文件
        conf = new Configuration();
        conf.setDriver(pros.getProperty("driver"));
        conf.setUrl(pros.getProperty("url"));
        conf.setUser(pros.getProperty("user"));
        conf.setPwd(pros.getProperty("pwd"));
        conf.setSrcPath(pros.getProperty("srcPath"));
        conf.setUsingDB(pros.getProperty("usingDB"));
        conf.setPoPackage(pros.getProperty("poPackage"));
        conf.setQueryClass(pros.getProperty("queryClass"));

    }
    public static Connection getConn(){

        try {
            Class.forName(conf.getDriver());
            return DriverManager.getConnection(conf.getUrl(),
                    conf.getUser(),conf.getPwd());  //目前直接建立连接，后期增加连接池处理，提高效率！！！
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void close(ResultSet rs, Statement ps, Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement ps,Connection conn){
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn){
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回Configuration对象
     * @return
     */
    public static Configuration getConf(){
        return conf;
    }

}
