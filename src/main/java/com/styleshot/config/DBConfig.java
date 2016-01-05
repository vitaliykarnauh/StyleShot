package com.styleshot.config;

import com.styleshot.dao.RoleDao;
import com.styleshot.dao.UserDao;
import com.styleshot.dao.UserLinksDao;
import com.styleshot.dao.UserResultsDao;
import com.styleshot.dao.impl.RoleDaoImpl;
import com.styleshot.dao.impl.UserDaoImpl;
import com.styleshot.dao.impl.UserLinksDaoImpl;
import com.styleshot.dao.impl.UserResultsDaoImpl;
import com.styleshot.domain.User;
import com.styleshot.service.RoleService;
import com.styleshot.service.UserLinksService;
import com.styleshot.service.UserResultsService;
import com.styleshot.service.UserService;
import com.styleshot.service.impl.RoleServiceImpl;
import com.styleshot.service.impl.UserLinksServiceImpl;
import com.styleshot.service.impl.UserResultsServiceImpl;
import com.styleshot.service.impl.UserServiceImpl;
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
        localSessionFactoryBuilder.scanPackages("com.styleshot.domain");
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
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }


    @Bean(name = "userLinksDao")
    public UserLinksDao getUserLinksDao() {
        return new UserLinksDaoImpl();
    }

    @Bean(name = "userResultsDao")
    public UserResultsDao getUserResultsDao() {
        return new UserResultsDaoImpl();
    }

    @Bean(name = "userDao")
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Bean(name = "roleDao")
    public RoleDao getRoleDao() {
        return new RoleDaoImpl();
    }


    @Bean(name = "userResultsService")
    public UserResultsService getUserResultsService() {
        return new UserResultsServiceImpl();
    }


    @Bean(name = "userService")
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Bean(name = "roleService")
    public RoleService getRoleService() {
        return new RoleServiceImpl();
    }

    @Bean(name = "userLinksService")
    public UserLinksService getUserLinksService() {
        return new UserLinksServiceImpl();
    }

}


