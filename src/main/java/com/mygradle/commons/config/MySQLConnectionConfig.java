package com.mygradle.commons.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class MySQLConnectionConfig implements TransactionManagementConfigurer {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String dbPassword;
    @Value("${spring.datasource.driver-class-name}")
    private String dbClassName;

    @Lazy
    @Bean
    public DataSource dataSource(){
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(dbUsername);
        hikariConfig.setPassword(dbPassword);
        hikariConfig.setJdbcUrl(dbUrl);
        hikariConfig.setDriverClassName(dbClassName);
        hikariConfig.setLeakDetectionThreshold(2000);
        hikariConfig.setPoolName("jPubDBPool");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }
}
