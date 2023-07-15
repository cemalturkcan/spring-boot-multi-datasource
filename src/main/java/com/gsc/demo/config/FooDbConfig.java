package com.gsc.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
    basePackages = {"com.gsc.demo.foo"})
public class FooDbConfig {

  @Primary
  @Bean(name = "dataSource")
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5433/postgres")
            .username("postgres")
            .password("postgres")
            .driverClassName("org.postgresql.Driver")
            .build();
  }
  @Bean
  @Primary
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true); // DDL operations generation is turned on
    vendorAdapter.setDatabase(Database.POSTGRESQL); // Database is set to POSTGRESQL
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect"); // Dialect is set to PostgreSQLDialect

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.gsc.demo.foo");  // `com.gsc.demo.domain.client` paketi eklenmiştir
    factory.setDataSource(dataSource);
    factory.setPersistenceUnitName("db1");
    return factory;
  }


  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
