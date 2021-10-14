package ru.digitalleague.taxi_company.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.digitalleague.taxi_company.listener.OrderListener;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class ApplicationConfiguration {

    @Value("${application.broker.receive-queue}")
    private String queueName;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitAdmin ampqAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    /**
     * Очередь для приема результатов поездки.
     */
    @Bean
    public Queue myQueue2() {
        return new Queue("trip-result");
    }

    /**
     * Очередь для приема результатов поездки.
     */
    @Bean
    public Queue myQueue3() {
        return new Queue(queueName);
    }

    @Bean
    public DataSource getDataSource() {

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/taxidb?currentSchema=taxi-service");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");

        return dataSourceBuilder.build();
    }

    @Bean
    public SpringLiquibase liquibase() {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setShouldRun(false);

        return liquibase;
    }

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {

        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        // устанавливаем очередь, которую будет слушать приложение
        simpleMessageListenerContainer.setQueues(myQueue3());
        simpleMessageListenerContainer.setMessageListener(orderListener);

        return simpleMessageListenerContainer;
    }

    @Autowired
    private OrderListener orderListener;
}
