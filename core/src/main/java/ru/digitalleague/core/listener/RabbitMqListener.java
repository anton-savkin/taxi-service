package ru.digitalleague.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
@ConditionalOnBean(value = RabbitTemplate.class)
public class RabbitMqListener {

    /**
     * Метод, который слушает очередь "trip-result" и логирует информацию о заверщении поездки.
     * @param message - информация о завершении поездки
     */
    @RabbitListener(queues = "trip-result")
    public void processRabbitMessage(String message) {
        log.info("Поездка завершена " + message);
    }
}
