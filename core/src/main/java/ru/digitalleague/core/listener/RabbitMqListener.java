package ru.digitalleague.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
public class RabbitMqListener {

    /**
     * Получаем информацию о заказе.
     */
    @RabbitListener(queues = "order")
    public void processRabbitMessage(String message) {
        log.info("Received message from rabbitmq " + message);
    }
}
