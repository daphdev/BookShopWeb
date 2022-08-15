package com.bookshopweb.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JdbiUtils {
    public static Jdbi createInstance() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(ConstantUtils.DB_PORT);
        dataSource.setServerName(ConstantUtils.SERVER_NAME);
        dataSource.setDatabaseName(ConstantUtils.DB_NAME);
        dataSource.setUser(ConstantUtils.DB_USERNAME);
        dataSource.setPassword(ConstantUtils.DB_PASSWORD);

        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }
}
