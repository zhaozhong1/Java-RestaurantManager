package manhanlou.com.Dao;


import manhanlou.com.Utils.Druid_JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<T> {
    QueryRunner qr = new QueryRunner();

    //DML语句
    public int update(String sql,Object... params){
        Connection connection = Druid_JDBCUtils.getConnection();
        try {
            return qr.update(connection,sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_JDBCUtils.closeConnection(null,null,connection);
        }
    }

    //查询多行结果，存放在一个List中
    public List<T> queryMulti(String sql,Class<T> cls,Object... params){
        Connection connection = Druid_JDBCUtils.getConnection();
        try {
            return qr.query(connection,sql,new BeanListHandler<T>(cls),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_JDBCUtils.closeConnection(null,null,connection);
        }
    }


    //查询单行结果，存放在一个T(泛型指定)中
    public T querySingleRow(String sql,Class<T> cls,Object... params){
        Connection connection = Druid_JDBCUtils.getConnection();
        try {
            return qr.query(connection,sql,new BeanHandler<T>(cls),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_JDBCUtils.closeConnection(null,null,connection);
        }
    }

    //查询单行单列结果，存放在一个Object中
    public Object querySingleRes(String sql,Object... params){
        Connection connection = Druid_JDBCUtils.getConnection();
        try {
            return qr.query(connection,sql, new ScalarHandler<>(),params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            Druid_JDBCUtils.closeConnection(null,null,connection);
        }
    }

}
