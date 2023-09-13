package manhanlou.com.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Druid_JDBCUtils {
    private static DataSource dataSource = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("resource\\Druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(ResultSet rs, Statement s,Connection c){
        try {
            if(rs != null){
                rs.close();
            }
            if(s != null){
                s.close();
            }
            if(c != null){
                c.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
