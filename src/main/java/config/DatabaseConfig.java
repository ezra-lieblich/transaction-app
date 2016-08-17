package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/**
 * Created by elieblich on 8/16/16.
 */


@Configuration
public class DatabaseConfig {

   @Bean
   public DataSource dataSource(){
       BasicDataSource dataSource = new BasicDataSource();
       dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
       dataSource.setUrl("jdbc:derby://localhost:1527/firstdb;create=true");
       return dataSource;
   }
}
