package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.CityMapper;
import ru.digitalleague.taxi_company.mapper.DriversMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(OrderDetails orderDetails) {

        OrderModel orderModel = new OrderModel();
        TaxiDriverInfoModel driver = getDriver(orderDetails);
        driversMapper.changeBusy(driver.getDriverId());

        orderModel.setDriverID(driver.getDriverId());
        orderModel.setClientNumber(orderDetails.getClientNumber());

        orderMapper.saveOrder(orderModel);

        log.info("Order {} is saved", orderModel);
    }

    /**
     * Метод для поиска подходящего водителя.
     * @param orderDetails - детали заказа
     * @return - выбранный водитель
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public TaxiDriverInfoModel getDriver(OrderDetails orderDetails) {

        Long cityId = cityMapper.getIdByCityName(orderDetails.getCity());

        return driversMapper.getDriver(orderDetails, cityId);
    }
}
