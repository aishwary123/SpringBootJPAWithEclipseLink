package com.sap.aish.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Config  {

		@Value("${dbURL}")
		String dbURL;
		
		@Value("${dbUser}")
		String dbUser;
		
		@Value("${dbPassword}")
		String dbPassword;
		
		@Value("${dbDriver}")
		String dbDriver;
		
		
		private static final Log log = LogFactory.getLog(Config.class);
		@Bean
		public DataSource dataSource() {
			DataSource ds=DataSourceBuilder.create().driverClassName(dbDriver).url(dbURL).username(dbUser).password(dbPassword).build();
			return ds;
		}

		
		
	
		@Bean
		public JpaVendorAdapter eclipseLink() {
			EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
			return adapter;
		}

		@Bean(name = "entityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaVendorAdapter adapter, DataSource dataSource) {
			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setPackagesToScan("com.sap.aish.model");
			factory.setPersistenceUnitName("SpringBootJPAWithEclipseLink");
			factory.setJpaVendorAdapter(eclipseLink());
			factory.setDataSource(dataSource());
			return factory;
		}

		@Bean
		@Autowired
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			return new JpaTransactionManager(emf);
		}
	

}

