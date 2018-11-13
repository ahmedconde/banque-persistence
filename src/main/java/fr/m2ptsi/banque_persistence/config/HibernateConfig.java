package fr.m2ptsi.banque_persistence.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Classe de configuration hibernate pour le projet
 * 
 * @author Ahmed CONDE
 *
 */
@Configuration
@ComponentScan(basePackages = {"fr.m2ptsi.banque_persistence"})
@EnableTransactionManagement
@PropertySource("application.properties")
public class HibernateConfig {

	@Autowired
	private Environment env;
	
	/**
	 * Configuration de la DataSource pour la connexion bdd
	 * @return
	 */
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("dataSource.driver"));
		dataSource.setUrl(env.getRequiredProperty("dataSource.url"));
		dataSource.setUsername(env.getRequiredProperty("dataSource.user"));
		dataSource.setPassword(env.getRequiredProperty("dataSource.password"));
		return dataSource;
		
	}
	
	/**
	 * Configuration des parametre hibernate
	 * @return
	 */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getRequiredProperty("hibernate.dialect"));
		properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
		properties.put(AvailableSettings.STATEMENT_BATCH_SIZE, env.getRequiredProperty("hibernate.batch.size"));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getRequiredProperty("hibernate.current.session.context.class"));
		return properties;
		
	}
//	org.hibernate.dialect.MySQL5Dialect;
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		factoryBean.setPackagesToScan(new String[] {"fr.m2ptsi.banque_persistence.models"});
		factoryBean.setHibernateProperties(getHibernateProperties());
		return factoryBean;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager; 
	}
	
}
