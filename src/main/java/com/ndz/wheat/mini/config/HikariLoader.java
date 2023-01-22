package com.ndz.wheat.mini.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * 项目启动时进行Hikari初始化
 */
@Component
public class HikariLoader implements ApplicationRunner {
    private final HikariDataSource hikariDataSource;

    public HikariLoader(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @Autowired
    public void run(ApplicationArguments args) throws SQLException {
        hikariDataSource.getConnection();
    }
}
