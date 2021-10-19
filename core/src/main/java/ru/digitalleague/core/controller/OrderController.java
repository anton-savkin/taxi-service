package ru.digitalleague.core.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.api.TaxiService;
import ru.digitalleague.core.model.OrderDetails;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private TaxiService taxiService;

    /**
     * Контроллер для заказа такси.
     * @param orderDetails - детали заказа
     * @return - HTTP Response
     */
    @PostMapping("/order-taxi")
    @ApiOperation(value="Контроллер для заказа такси")
    public ResponseEntity<String> receive(@RequestBody OrderDetails orderDetails) {

        log.info("Received message from postman " + orderDetails);
        String result = taxiService.notifyTaxi(orderDetails);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/rate-driver")
    @ApiOperation(value="Контроллер для выставления рейтинга")
    public ResponseEntity<String> rateDriver(@RequestParam Long driverId, @RequestParam int rate) {

        log.info("Клиент оценил водителя с ID {} на {} звезд", driverId, rate);
        taxiService.rateDriver(driverId, rate);

        return ResponseEntity.ok("Рейтинг обновлен");
    }
}
