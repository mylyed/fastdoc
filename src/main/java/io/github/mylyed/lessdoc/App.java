package io.github.mylyed.lessdoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lilei
 * created at 2019/4/30
 */
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = {"io.github.mylyed.lessdoc.persist.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
