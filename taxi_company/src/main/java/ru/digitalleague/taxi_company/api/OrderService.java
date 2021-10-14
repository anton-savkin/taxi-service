package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

/**
 * Сервис обработки заказов.
 */
public interface OrderService {

    /**
     * Метод для сохранения информации о заказе в БД.
     * @param orderDetails - детали заказа
     */
    void save(OrderDetails orderDetails);

    /**
     * Метод для поиска подходящего водителя.
     * @param orderDetails - детали заказа
     * @return - выбранный водитель
     */
    TaxiDriverInfoModel getDriver(OrderDetails orderDetails);
}
