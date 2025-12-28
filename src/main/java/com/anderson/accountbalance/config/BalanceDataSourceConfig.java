package com.anderson.accountbalance.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties; // IMPORTANTE
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.anderson.accountbalance.domain.repository.balance",
        entityManagerFactoryRef = "balanceEntityManagerFactory",
        transactionManagerRef = "balanceTransactionManager"
)
public class BalanceDataSourceConfig {

    // 1. Este Bean lê as propriedades do YAML
    @Bean
    @ConfigurationProperties("spring.datasource.balance")
    public DataSourceProperties balanceDataSourceProperties() {
        return new DataSourceProperties();
    }

    // 2. Este Bean usa as propriedades acima para criar o DataSource real com a URL do H2
    @Bean
    public DataSource balanceDataSource() {
        return balanceDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean balanceEntityManagerFactory(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(balanceDataSource())
                .packages("com.anderson.accountbalance.domain.model")
                .persistenceUnit("balancePU")
                .build();
    }

    @Bean
    public PlatformTransactionManager balanceTransactionManager(
            @Qualifier("balanceEntityManagerFactory") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
