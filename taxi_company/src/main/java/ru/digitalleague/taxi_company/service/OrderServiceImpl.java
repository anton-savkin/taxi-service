package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.CityMapper;
import ru.digitalleague.taxi_company.mapper.DriversMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderModel;

import java.time.Duration;

/**
 * Сервис обработки заказов.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private DriversMapper driversMapper;

    /**
     * Метод для сохранения информации о заказе в БД.
     * @param orderDetails - детали заказа
     */
    @Override
    public void save(OrderDetails orderDetails) {

        OrderModel orderModel = new OrderModel();
        TaxiDriverInfoModel driver = getDriver(orderDetails);
        driversMapper.changeBusy(driver.getDriverId());

        orderModel.setDriverID(driver.getDriverId());
        orderModel.setClientNumber(orderDetails.getClientNumber());

        orderMapper.saveOrder(orderModel); //@selectKey как инструмент для получения Ордера с ненулевым ID без дополительного запроса к базе -
        //под капотом запрос к сиквенсу перед инсертом

        log.info("Order {} is saved", orderModel);
    }

    /**
     * Метод для поиска подходящего водителя.
     * @param orderDetails - детали заказа
     * @return - выбранный водитель
     */
    @Override
    public TaxiDriverInfoModel getDriver(OrderDetails orderDetails) {

        Long cityId = cityMapper.getIdByCityName(orderDetails.getCity());

        return driversMapper.getDriver(orderDetails, cityId);
    }

    /**
     * Метод для начала поездки.
     * @param orderModel - модель заказа
     * @return - обновленная модель заказа
     */
    @Override
    public OrderModel startTrip(OrderModel orderModel) {

        Long orderID = orderModel.getOrderID();
        orderMapper.setStartTrip(orderID);

        return orderMapper.getOrderById(orderID);
    }

    /**
     * Метод для окончания поездки.
     * @param orderModel - модель заказа
     * @return - обновленная модель заказа
     */
    @Override
    public OrderModel completeTrip(OrderModel orderModel) {

        Long orderID = orderModel.getOrderID();
        Long driverID = orderMapper.getDriverIDByOrderID(orderID);

        orderMapper.setEndTime(orderID);
        Long price = estimatePrice(orderID, driverID);
        orderMapper.setPrice(orderID, price);
        driversMapper.changeBusy(driverID);

        return orderMapper.getOrderById(orderID);
    }

    /**
     * Метод для рассчета стоимости поездки.
     * @param orderID - идентификатор заказа
     * @param driverID - идентификатор водителя
     * @return - стоимость поездки
     */
    @Override
    public Long estimatePrice(Long orderID, Long driverID) {

        OrderModel orderModel = orderMapper.getOrderById(orderID);
        Integer minuteCost = driversMapper.getMinuteCost(driverID);
        Duration orderDuration = Duration.between(orderModel.getStartTrip(), orderModel.getEndTrip());

        return minuteCost * orderDuration.toMinutes();
    }
}
