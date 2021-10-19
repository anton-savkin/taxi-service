package ru.digitalleague.taxi_company.api;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.core.model.TaxiDriverInfoModel;
import ru.digitalleague.taxi_company.model.OrderModel;

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

    /**
     * Метод для начала поездки.
     * @param orderModel - модель заказа
     * @return - обновленная модель заказа
     */
    OrderModel startTrip(OrderModel orderModel);

    /**
     * Метод для окончания поездки.
     * @param orderModel - модель заказа
     * @return - обновленная модель заказа
     */
    OrderModel completeTrip(OrderModel orderModel);

    /**
     * Метод для рассчета стоимости поездки.
     * @param orderID - идентификатор заказа
     * @param driverID - идентификатор водителя
     * @return - стоимость поездки
     */
    Long estimatePrice(Long orderID, Long driverID);
}
