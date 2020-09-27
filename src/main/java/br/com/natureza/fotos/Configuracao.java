package br.com.natureza.fotos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class Configuracao {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Configuracao.class, args);
	}
	
	@Bean
	public DataSource datasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/Imagens");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	
	

}
