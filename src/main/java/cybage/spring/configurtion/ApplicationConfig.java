package cybage.spring.configurtion;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import cybage.spring.model.Beer;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "cybage", excludeFilters = {@ComponentScan.Filter(Configuration.class)})
@PropertySource("classpath:jdbc.properties")
public class ApplicationConfig implements TransactionManagementConfigurer{
    
	@Autowired
    Environment env;
	
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();
		try {
			dataSource.setDriverClass(env.getProperty("driverClass"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(env.getProperty("jdbcUrl"));
		dataSource.setUser(env.getProperty("user"));
		dataSource.setPassword(env.getProperty("password"));
		return dataSource;
	}
	
	@Bean(destroyMethod="close")
	SessionFactory sessionFactory() throws Exception{
		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setAnnotatedClasses(new Class[]{ Beer.class});
		bean.setPackagesToScan(new String[]{"cybage"});
//		bean.setExposeTransactionAwareSessionFactory(false);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", org.hibernate.dialect.MySQLInnoDBDialect.class);
		hibernateProperties.put("hibernate.show_sq", true);
		hibernateProperties.put("hibernate.connection.autocommit", false);
//		hibernateProperties.put("hibernate.current_session_context_class", "thread");
//		hibernateProperties.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
		bean.setHibernateProperties(hibernateProperties);
		bean.afterPropertiesSet();
		return bean.getObject();
		
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws Exception {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	    transactionManager.setSessionFactory(sessionFactory());
	    transactionManager.setDataSource(dataSource());
	    return transactionManager;
	}
	
	 public PlatformTransactionManager annotationDrivenTransactionManager() {
	         try {
				return transactionManager();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	     }

	

}
