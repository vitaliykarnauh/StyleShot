package com.checkdoc.config;

import com.checkdoc.dao.*;
import com.checkdoc.dao.impl.*;
import com.checkdoc.domain.MistakeType;
import com.checkdoc.domain.User;
import com.checkdoc.service.*;
import com.checkdoc.service.impl.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBConfig {


    @Inject
    private Environment environment;

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto",
                environment.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Autowired
    @Bean(name = "hibernateTemplate")
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }


    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(
                dataSource);
        localSessionFactoryBuilder.addAnnotatedClasses(new Class[]{User.class});
        localSessionFactoryBuilder.addProperties(getHibernateProperties());
        localSessionFactoryBuilder.scanPackages("com.checkdoc.domain");
        return localSessionFactoryBuilder.buildSessionFactory();
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("driverClassName"));
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("username1"));
        dataSource.setPassword(environment.getProperty("password1"));
        return dataSource;
    }

    @Autowired
    @Bean(name = "transactionManager")
    @Scope(WebApplicationContext.SCOPE_REQUEST)
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }


    @Bean(name = "userDao")
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean(name = "roleDao")
    public RoleDao getRoleDao() {
        return new RoleDaoImpl();
    }

    @Bean(name = "directoryDao")
    public DirectoryDao getDirectoryDao() {
        return new DirectoryDaoImpl();
    }

    @Bean(name = "documentDao")
    public DocumentDao getDocumentDao() {
        return new DocumentDaoImpl();
    }

    @Bean(name = "mistakeDao")
    public MistakeDao getMistakeDao() {
        return new MistakeDaoImpl();
    }

    @Bean(name = "mistakeTypeDao")
    public MistakeTypeDao getMistakeTypeDao() {
        return new MistakeTypeDaoImpl();
    }

    @Bean(name = "userService")
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean(name = "roleService")
    public RoleService getRoleService() {
        return new RoleServiceImpl();
    }

    @Bean(name = "directoryService")
    public DirectoryService getDirectoryService() {
        return new DirectoryServiceImpl();
    }

    @Bean(name = "documentService")
    public DocumentService getDocumentService() {
        return new DocumentServiceImpl();
    }

    @Bean(name = "mistakeService")
    public MistakeService getMistakeService() {
        return new MistakeServiceImpl();
    }

    @Bean(name = "mistakeTypeService")
    public MistakeTypeService getMistakeTypeService() {
        return new MistakeTypeServiceImpl();
    }


}


