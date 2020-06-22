package com.mygradle.commons.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.mygradle.commons.repository"})
public class MyBatisConfig {

    private MySQLConnectionConfig mySQLConnectionConfig;

    @Autowired
    public void setMySQLConnectionConfig(MySQLConnectionConfig mySQLConnectionConfig) {
        this.mySQLConnectionConfig = mySQLConnectionConfig;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mySQLConnectionConfig.dataSource());
        sqlSessionFactoryBean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")
        );
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/sample/mapper/**/*.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }
    /*
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder().setName("jPubTestDB")
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema-hsqldb.sql")
                .addScript("data-hsqldb.sql")
                .build();
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        System.out.println("return sqlSessionTemplate");
        return new SqlSessionTemplate(sqlSessionFactory(dataSource));
    }

    public MapperFactoryBean<MapperInterface> mapperFactoryBean(){
        MapperFactoryBean<MapperInterface> mapperFactoryBean = new MapperFactoryBean<MapperInterface>();
        mapperFactoryBean.setMapperInterface(MapperInterface.class);
        return mapperFactoryBean;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("sqlSessionFactory");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation((new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml")));
        sqlSessionFactoryBean.setMapperLocations((new PathMatchingResourcePatternResolver().getResource("classpath:/sample/mapper/userMapper.xml")));
        return sqlSessionFactoryBean.getObject();
    }

 */
}

