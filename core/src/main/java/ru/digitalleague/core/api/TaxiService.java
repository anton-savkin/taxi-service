package ru.digitalleague.core.api;

import ru.digitalleague.core.model.OrderDetails;

/**
 * Сервис отправки информации о заказе.
 */
public interface TaxiService {

    /**
     * Метод для информоривания такси о поступлении заказа.
     * @param orderDetails - детали заказа
     * @return - ответ от сервиса
     */
    String notifyTaxi(OrderDetails orderDetails);
}
