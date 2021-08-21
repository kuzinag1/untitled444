package org.example.config.jdbc.connection;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionDataBaseFactory {

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private final DataSource dataSource;


    private ConnectionDataBaseFactory() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        try {
            String name = "postgres";
            String url = "jdbc:postgresql://localhost:5432/university";
            String password = "aaaaaa";
            pgSimpleDataSource.setUser(name);
            pgSimpleDataSource.setUrl(url);
            pgSimpleDataSource.setPassword(password);
            String driverClazz = "org.postgresql.Driver";
            Class.forName(driverClazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dataSource = pgSimpleDataSource;
    }
    private static class SingletonHelper
    {
        private static final ConnectionDataBaseFactory INSTANCE
                = new ConnectionDataBaseFactory();
    }
}
