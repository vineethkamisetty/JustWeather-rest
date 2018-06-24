package com.vineethkamisetty.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:application.properties")
public class JPAConfig {
    private final Environment env;

    @Autowired
    public JPAConfig(Environment env) {
        this.env = env;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show.sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format.sql"));
        return properties;
    }

    @Bean
    public PlatformTransactionManager txnMgr(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setDataSource(getDatasource());
        emf.setPackagesToScan("com.vineethkamisetty.api.entity");
        emf.setJpaProperties(jpaProperties());
        return emf;
    }

    @Bean
    public DataSource getDatasource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl(env.getProperty("db.url"));
        ds.setUsername(env.getProperty("db.user", "root"));
        ds.setPassword(env.getProperty("db.password", "root"));
        return ds;
    }
}
