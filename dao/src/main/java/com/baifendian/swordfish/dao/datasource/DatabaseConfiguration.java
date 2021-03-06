/*
 * Copyright (C) 2017 Baifendian Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.baifendian.swordfish.dao.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.Arrays;

@Configuration
@PropertySource({"classpath:dao/data_source.properties"})
@MapperScan(basePackages = "com.baifendian.swordfish.dao.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DatabaseConfiguration {
  private static Logger logger = LoggerFactory.getLogger(DatabaseConfiguration.class.getName());

  @Autowired
  private Environment env;

  /**
   * 注册数据源
   */
  @Primary
  @Bean(name = "DataSource", initMethod = "init", destroyMethod = "close")
  public DruidDataSource dataSource() throws SQLException {
    if (StringUtils.isEmpty(env.getProperty("spring.datasource.url"))) {
      logger.error("Your database connection pool configuration is incorrect! Please check your Spring profile, " +
          "current profiles are: {}", Arrays.toString(env.getActiveProfiles()));
      throw new ApplicationContextException(
          "Database connection pool is not configured correctly");
    }

    DruidDataSource druidDataSource = new DruidDataSource();

    druidDataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
    druidDataSource.setUrl(env.getProperty("spring.datasource.url"));
    druidDataSource.setUsername(env.getProperty("spring.datasource.username"));
    druidDataSource.setPassword(env.getProperty("spring.datasource.password"));
    druidDataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.initialSize")));
    druidDataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.minIdle")));
    druidDataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.maxActive")));
    druidDataSource.setMaxWait(Integer.parseInt(env.getProperty("spring.datasource.maxWait")));
    druidDataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
    druidDataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
    druidDataSource.setValidationQuery(env.getProperty("spring.datasource.validationQuery"));
    druidDataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("spring.datasource.testWhileIdle")));
    druidDataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnBorrow")));
    druidDataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("spring.datasource.testOnReturn")));
    druidDataSource.setPoolPreparedStatements(Boolean.parseBoolean(env.getProperty("spring.datasource.poolPreparedStatements")));
    druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
    druidDataSource.setFilters(env.getProperty("spring.datasource.filters"));

    return druidDataSource;
  }

  @Primary
  @Bean(name = "SqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());

    return sqlSessionFactoryBean.getObject();
  }

  @Primary
  @Bean(name = "TransactionManager")
  public PlatformTransactionManager transactionManager() throws SQLException {
    return new DataSourceTransactionManager(dataSource());
  }
}
