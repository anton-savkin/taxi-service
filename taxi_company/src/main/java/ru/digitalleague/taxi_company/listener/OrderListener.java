package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;

@Slf4j
@Component
public class OrderListener implements MessageListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    /**
     * Метод, который получает информацию о заказе из очереди и сохраняет заказ в БД.
     * @param message - информация о заказе
     */
    @SneakyThrows
    @Override
    public void onMessage(Message message) {

        log.info("Received message from rabbitmq " + message);

        OrderDetails orderDetails = objectMapper.readValue(message.getBody(), OrderDetails.class);

        orderService.save(orderDetails);
    }
}
