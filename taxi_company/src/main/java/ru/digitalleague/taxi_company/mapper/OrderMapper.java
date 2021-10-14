package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.OrderModel;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Метод для сохранения заказа в БД.
     * @param orderModel - заказ
     */
    @Insert("INSERT INTO orders (client_number, driver_id, start_trip, end_trip) VALUES (#{clientNumber}, #{driverID}, #{startTrip}, #{endTrip})")
    void saveOrder(OrderModel orderModel);

    /**
     * Метод для установления времени начала выполнения заказа.
     * @param orderID -  идентификатор заказа
     */
    @Update("UPDATE orders SET start_trip = now() WHERE order_id = #{orderID}")
    void setStartTrip(Long orderID);

    /**
     * Метод для установления времени окончания выполнения заказа.
     * @param orderID -  идентификатор заказа
     */
    @Update("UPDATE orders SET end_trip = now() WHERE order_id = #{orderID}")
    void setEndTime(Long orderID);

    /**
     * Метод для поиска идентификатора водителя в данном заказе.
     * @param orderID -  идентификатор заказа
     * @return - идентификатор водителя
     */
    @Select("SELECT driver_id FROM orders WHERE order_id = #{orderID}")
    Long getDriverIDByOrderID(Long orderID);
}
