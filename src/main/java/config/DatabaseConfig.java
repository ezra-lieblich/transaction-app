package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by elieblich on 8/16/16.
 */

@Configuration
public class DatabaseConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://elmer-01.db.elephantsql.com:5432/wfcnvhwm");
        dataSource.setUsername("wfcnvhwm");
        dataSource.setPassword("dfh3vlCcRkmE5rKEvuvsii-2Rr0CSS7n");
        return dataSource;
    }
}