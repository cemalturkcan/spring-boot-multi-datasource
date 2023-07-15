package com.gsc.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory",
    transactionManagerRef = "barTransactionManager", basePackages = {"com.gsc.demo.bar"})
public class BarDbConfig {


  @Bean(name = "barDataSource")
  @ConfigurationProperties(prefix = "bar.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
            .url("jdbc:postgresql://localhost:5432/postgres")
            .username("postgres")
            .password("postgres")
            .driverClassName("org.postgresql.Driver")
            .build();
  }


  @Bean(name = "barEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("barDataSource") DataSource dataSource) {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true); // DDL operations generation is turned on
    vendorAdapter.setDatabase(Database.POSTGRESQL); // Database is set to POSTGRESQL
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect"); // Dialect is set to PostgreSQLDialect

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.gsc.demo.bar");  // `com.gsc.demo.domain.client` paketi eklenmiştir
    factory.setDataSource(dataSource);
    factory.setPersistenceUnitName("db1");
    return factory;
  }


  @Bean(name = "barTransactionManager")
  public PlatformTransactionManager barTransactionManager(
      @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
    return new JpaTransactionManager(barEntityManagerFactory);
  }

}
