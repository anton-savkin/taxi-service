package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.TaxiDriverInfoModel;

import java.util.List;

@Repository
@Mapper
public interface TaxiInfoMapper {

    /**
     * Метод для подсчета общего числа водителей.
     * @return - количество водителей в БД
     * */
    @Select("select count(1) from taxi_drive_info")
    int getCount();

    /**
     * Метод для получения списка всех водителей.
     * @return - список всех водиелей из БД
     * */
    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "isBusy", column = "is_busy"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "cityID", column = "city_id"),
            @Result(property = "carID", column = "car_id"),
            @Result(property = "ordersCnt", column = "orders_cnt")
    })
    @Select("SELECT driver_id, last_name, first_name, level, create_dttm, rate, is_busy, minute_cost, city_id, car_id, orders_cnt FROM taxi_drive_info")
    List<TaxiDriverInfoModel> getAllDrivers();

    /**
     * Метод для поиска очереди, которая будет слушать сообщения в данном городе.
     * @param cityName - название города
     * @return - название очереди
     * */
    @Select("SELECT queue FROM city_queue where name = #{cityName}")
    String getQueueByCity(String cityName);

    int insert(TaxiDriverInfoModel record);

    /**
     * Метод для поиска водителя по идентификатору.
     * @param driverId - идентификатор водителя
     * @return - модель водителя
     * */
    @Select("SELECT * FROM taxi_drive_info WHERE driver_id = #{driverId}")
    TaxiDriverInfoModel selectByPrimaryKey(Long driverId);

    /**
     * Метод обновления информации о водителе после выставления рейтинга.
     * @param driverId - идетификатор водителя
     * @param ordersCnt - обновленное количество поездок
     * @param rate - обновленный рейтинг
     * */
    @Update("UPDATE taxi_drive_info SET orders_cnt = #{ordersCnt}, rate = #{rate} WHERE driver_id = #{driverId}")
    void updateOrdersCntAndRateByDriverId(Long driverId, int ordersCnt, double rate);

    int updateByPrimaryKey(TaxiDriverInfoModel record);

    int deleteByPrimaryKey(TaxiDriverInfoModel record);

    List<TaxiDriverInfoModel> selectByLastName(String lastName);
}
