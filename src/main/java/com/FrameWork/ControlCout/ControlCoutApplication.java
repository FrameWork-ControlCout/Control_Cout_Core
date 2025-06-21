package com.FrameWork.ControlCout;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;



@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaAuditing
public class ControlCoutApplication {

@Value("${IpServer}")
    private String appIpServer;
    
  

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(ControlCoutApplication.class);
    public static String jwtSecret = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleUpoYkdjaU9pSklVekkxTmlKOS5leUp6ZFdJaU9pSnpiM1ZtYVdWdUlpd2lhV0YwSWpveE56UXlNRGN6TkRNekxDSmxlSEFpT2pFM05ESXdOemN3TXpOOS5JNUlMMlZoZkNFbGhDeDVnZlNXa1ZZV3EtYVNldlo2Zy1zdVNiSGlLd3UwIiwiaWF0IjoxNzQyMDczNDMzLCJleHAiOjE3NDIwNzcwMzN9.Ngo_oiUp68rJ_-345Ex6hamxZrP5a_CFWXFYki4jDBk";

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
//ConfigurableApplicationContext context = SpringApplication.run(MedLiteCoreApplication.class, args);
        SpringApplication app = new SpringApplication(ControlCoutApplication.class);
        ConfigurableApplicationContext ctx = app.run(args);
        Environment env = ctx.getEnvironment();
//        Boolean kafkaProfileStauts = Arrays.stream(env.getActiveProfiles()).anyMatch("kafka"::equals);
//        String consumersStauts = "Consumer(s): \n";

        String protocol = "http";

        if (env.getProperty(
                "server.ssl.key-store") != null) {
            protocol = "https";
        }

        log.info(
                "\n----------------------------------------------------------\n\t"
                + "Application's name '{}' is running! Access URLs:\n\t"
                + "Local: \t\t{}://localhost:{}{}\n\t"
                + "External: \t{}://{}:{}{}\n\t"
                + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                env.getProperty("server.contextPath"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("server.contextPath"),
                env.getActiveProfiles());
        
         
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration crosConfiguration = new CorsConfiguration();
        crosConfiguration.setAllowCredentials(true);
        crosConfiguration.setAllowedOrigins(Arrays.asList(  "http://localhost:8086", appIpServer));
        crosConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers", "cache-control", "")); // Added cache-control
        crosConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        crosConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", crosConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
