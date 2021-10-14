package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.mapper.DriversMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

import java.time.OffsetDateTime;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
@Slf4j
public class TaxiController {

    private final String message = "Поездка окончена";

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DriversMapper driversMapper;

    /**
     * Метод для начала поездки и обновления БД.
     * @param orderModel - заказ
     * @return - HTTP Response
     * */
    @PostMapping("/trip-start")
    @ApiOperation(value="Контроллер начала поездки")
    public ResponseEntity<String> startTrip(@RequestBody OrderModel orderModel) {

        Long orderID = orderModel.getOrderID();
        orderMapper.setStartTrip(orderID);

        log.info("Order with ID {} is started", orderID);

        return ResponseEntity.ok("Водитель начал оказывать услугу");
    }

    /**
     * Метод для завершения поездки и обновления БД.
     * @param orderModel - заказ
     * @return - HTTP Response
     * */
    @PostMapping("/trip-complete")
    @ApiOperation(value="Контроллер для окончания поездки")
    public ResponseEntity<String> completeTrip(@RequestBody OrderModel orderModel) {

        Long orderID = orderModel.getOrderID();
        Long driverID = orderMapper.getDriverIDByOrderID(orderID);

        orderMapper.setEndTime(orderID);
        driversMapper.changeBusy(driverID);

        log.info("Order with ID {} is completed", orderID);

        amqpTemplate.convertAndSend("trip-result", message);

        return ResponseEntity.ok("Услуга оказана");
    }
}
