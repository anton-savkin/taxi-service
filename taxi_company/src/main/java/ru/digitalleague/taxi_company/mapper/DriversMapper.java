package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.OrderDetails;
import ru.digitalleague.taxi_company.model.TaxiDriverInfoModel;

@Repository
@Mapper
public interface DriversMapper {

    /**
     * Метод для изменения флага свободен\занят у водителя с данным идентификатором.
     * @param driverID - идентификатор водителя
     * */
    @Update("UPDATE taxi_drive_info SET is_busy = NOT(is_busy) WHERE driver_id = #{driverID}")
    void changeBusy(Long driverID);

    /**
     * Метод для поиска подходящего водителя.
     * @param orderDetails - детали заказа
     * @param cityId - идентификатор города
     * @return - выбранный водитель
     * @// TODO: 14.10.2021 реализовать фильтрацию по моедли машины и уровню водителя
     * */
    @Results({
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "isBusy", column = "is_busy"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "cityID", column = "city_id"),
            @Result(property = "carID", column = "car_id")

    })
    @Select("SELECT driver_id, last_name, first_name, level, create_dttm, rate, is_busy, minute_cost, city_id, car_id FROM taxi_drive_info WHERE is_busy = false AND city_id = #{cityId} ORDER BY rate DESC LIMIT 1")
    TaxiDriverInfoModel getDriver(OrderDetails orderDetails, Long cityId);

}
