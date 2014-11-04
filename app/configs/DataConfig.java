package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import play.Logger;
import play.Play;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("models");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setDataSource(herokuDataSource());
        entityManagerFactory.afterPropertiesSet();
        return entityManagerFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        transactionManager.setDataSource(herokuDataSource());
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }

//    @Bean
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(Play.application().configuration().getString("db.default.driver"));
//        dataSource.setUrl(Play.application().configuration().getString("db.default.url"));
//        dataSource.setUsername(Play.application().configuration().getString("db.default.user"));
//        dataSource.setPassword(Play.application().configuration().getString("db.default.password"));
//        return dataSource;
//    }

    @Bean
    public DataSource herokuDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Play.application().configuration().getString("db.default.driver"));
        dataSource.setUrl("jdbc:postgresql://ec2-54-225-101-199.compute-1.amazonaws.com:5432/d2prsu6ijdje7g");
        dataSource.setUsername("wxeqpnqwzczfai");
        dataSource.setPassword("gdfuxaaP69mUDo1jiVQ-CV3bjG");
        return dataSource;
    }

}


//<bean class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close" id="dataSource">
//<property name="driverClassName" value="${database.driverClassName}"/>
//<property name="url" value="#{ 'jdbc:postgresql://' + @dbUrl.getHost() + ':' + @dbUrl.getPort() + @dbUrl.getPath() }"/>
//<property name="username" value="#{ @dbUrl.getUserInfo().split(':')[0] }"/>
//<property name="password" value="#{ @dbUrl.getUserInfo().split(':')[1] }"/>
//db.heroku.url="postgres://wxeqpnqwzczfai:gdfuxaaP69mUDo1jiVQ-CV3bjG@ec2-54-225-101-199.compute-1.amazonaws.com:5432/d2prsu6ijdje7g"
//postgres://user:password@hostname:port/dbname