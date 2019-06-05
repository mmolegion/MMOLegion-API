package com.mmolegion.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        "com.mmolegion.core.dao",
        "com.mmolegion.core.service",
})
@ComponentScans(value = {
        @ComponentScan("com.mmolegion.core.dao"),
        @ComponentScan("com.mmolegion.core.service"),
        @ComponentScan("com.mmolegion.core.model"),
})
public class JPAConfig {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean em 
                = new LocalContainerEntityManagerFactoryBean();
        
        em.setDataSource(getDatasource());
        return em;
    }

    @Bean
    public DataSource getDatasource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup("java:jboss/jdbc/mmolegion");
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
}
