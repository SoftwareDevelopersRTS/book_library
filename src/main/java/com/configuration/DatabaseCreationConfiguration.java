package com.configuration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//This is written simply for the creation of DB that is maintained in application.proeprties in usersystem as it run project
@Configuration
public class DatabaseCreationConfiguration {
//	
//	@Value("${spring.datasource.url}")
//	private String url;
//	
//	@Value("${spring.datasource.username}")
//	private String username;
//	
//	@Value("${spring.datasource.password}")
//	private String password;
//	
//	@Bean
//	public CommandLineRunner databaseCreator() {
//		return args->{
//			try {
//				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/",username,password);
//				Statement s=con.createStatement();
//				String dbName=url.substring(url.lastIndexOf("/")+1);
//				String query="CREATE DATABASE IF NOT EXISTS "+dbName;
//				s.executeUpdate(query);
//				System.out.println("creation done");
//			}
//			catch(Exception e) {
//				e.printStackTrace();
//				System.out.println("Db not creted due to error");
//			}
//		};
//	}
//	

}
