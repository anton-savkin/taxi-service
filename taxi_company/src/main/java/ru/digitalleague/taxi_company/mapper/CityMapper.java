package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CityMapper {

    /**
     * Метод для получения идентификатора города по его названию.
     * @param cityName - название города
     * @return - идентификатор города
     * */
    @Select("SELECT city_id FROM city_queue WHERE name = #{cityName}")
    Long getIdByCityName(String cityName);
}
