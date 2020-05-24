package com.aliyu.ism;

import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.util.Properties;

import static org.springframework.orm.jpa.vendor.Database.MYSQL;

@Configuration
@ComponentScan
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.aliyu.ism"})
@EnableSpringDataWebSupport
public class AppConfig implements WebMvcConfigurer {

    @Value("${jpa.username}")
    private String username;
    @Value("${jpa.password}")
    private String password;
    @Value("${jpa.url}")
    private String url;
    @Value("${jpa.driverClassName}")
    private String driverClassName;

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocation(new ClassPathResource("application.properties"));
        return pspc;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmd = new DriverManagerDataSource();
//        System.out.printf("%s\n %s\n %s\n %s", username, password, url, driverClassName);
        dmd.setUsername(username);
        dmd.setPassword(password);
        dmd.setUrl(url);
        dmd.setDriverClassName(driverClassName);
        return dmd;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);

        Properties props = new Properties();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("com.aliyu.ism");
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setJpaDialect(jpaVendorAdapter.getJpaDialect());

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager() {
            {
                setEntityManagerFactory(entityManagerFactory);
//                setDataSource(dataSource());
            }
        };
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AppSecurity());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // url for collecting the resource
        registry.addResourceHandler("/js/**", "/img/**", "/fonts/**", "/css/**", "/**")
                // where the resource file is
                .addResourceLocations("/js/", "/img/", "/fonts/", "/css/", "/")
//                .setCachePeriod(31556926);
                .setCachePeriod(30);
    }
}
