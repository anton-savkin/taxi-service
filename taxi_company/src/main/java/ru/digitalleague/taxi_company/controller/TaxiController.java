package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.DriversMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

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

    @Autowired
    private OrderService orderService;

    /**
     * Метод для начала поездки и обновления БД.
     * @param orderModel - заказ
     * @return - HTTP Response
     * */
    @PostMapping("/trip-start")
    @ApiOperation(value="Контроллер начала поездки")
    public ResponseEntity<OrderModel> startTrip(@RequestBody OrderModel orderModel) {

        if (orderModel == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        orderModel = orderService.startTrip(orderModel);
        log.info("Order {} is started", orderModel);

        return ResponseEntity.ok(orderModel);
    }

    /**
     * Метод для завершения поездки и обновления БД.
     * @param orderModel - заказ
     * @return - HTTP Response
     * */
    @PostMapping("/trip-complete")
    @ApiOperation(value="Контроллер для окончания поездки")
    public ResponseEntity<OrderModel> completeTrip(@RequestBody OrderModel orderModel) {

        if (orderModel == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        orderModel = orderService.completeTrip(orderModel);
        log.info("Order {} is completed", orderModel);

        amqpTemplate.convertAndSend("trip-result", message);

        return ResponseEntity.ok(orderModel);
    }
}
