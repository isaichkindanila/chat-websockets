package ru.itis.chat.websockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.sql.DataSource;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
@AllArgsConstructor
public class AppConfig {

    private final Environment env;

    @Bean
    public DataSource hikariDataSource() {
        var config = new HikariConfig();

        config.setJdbcUrl(env.getRequiredProperty("db.url"));
        config.setUsername(env.getRequiredProperty("db.username"));
        config.setPassword(env.getRequiredProperty("db.password"));
        config.setDriverClassName(env.getRequiredProperty("db.driver"));

        return new HikariDataSource(config);
    }

    @Bean
    public Configuration freemarkerConfiguration() {
        var configuration = new Configuration(Configuration.VERSION_2_3_29);

        configuration.setClassForTemplateLoading(getClass(), "/templates/");
        configuration.setDefaultEncoding("UTF-8");

        return configuration;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        var configurer = new FreeMarkerConfigurer();
        configurer.setConfiguration(freemarkerConfiguration());

        return configurer;
    }
}
