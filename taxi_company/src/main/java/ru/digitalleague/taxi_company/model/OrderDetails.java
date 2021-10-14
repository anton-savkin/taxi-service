package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="Модель заказа такси")
public class OrderDetails {

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента")
    private Long clientNumber;

    /**
     * Желаемый класс поездки (бизнес, эконом, и т.п.)
     */
    @ApiModelProperty("Уровень поездки")
    private int level;

    /**
     * Должна быть enum.
     */
    @ApiModelProperty("Модель автомобиля")
    private String carModel;

    /**
     * Название города.
     */
    @ApiModelProperty("Город")
    private String city;
}
