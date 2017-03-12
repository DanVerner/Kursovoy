package com.educsystem.database.connector;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Denis on 28.02.2017.
 */
public class Pooler {
    public static Connection getPoolConn() throws NamingException, SQLException {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/Kursovoy");
            return ds.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
