package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@ApiModel("Модель заказа такси")
public class OrderModel {

    /**
     * Идентификатор заказа.
     */
    @ApiModelProperty("Идентификатор заказа")
    private Long orderID;

    /**
     * Идентификатор клиента.
     */
    @ApiModelProperty("Идентификатор клиента")
    private Long clientNumber;

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя")
    private Long driverID;

    /**
     * Время начала поездки.
     */
    @ApiModelProperty("Время начала поездки")
    private OffsetDateTime startTrip;

    /**
     * Время окончания поездки.
     */
    @ApiModelProperty("Время окончания поездки")
    private OffsetDateTime endTrip;




}
