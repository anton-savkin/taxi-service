package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("Модель водителя такси")
public class TaxiDriverInfoModel {

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    /**
     * Фамилия водителя.
     */
    @ApiModelProperty("Фамилия водителя")
    private String lastName;

    /**
     * Имя водителя.
     */
    @ApiModelProperty("Имя водителя")
    private String firstName;

    /**
     * Уровень водителя.
     */
    @ApiModelProperty("Уровень водителя")
    private int level;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата создания")
    private Date createDttm;

    /**
     * Рейтинг водителя.
     */
    @ApiModelProperty("Рейтинг водителя")
    private int rate;

    /**
     * Флаг свободен\занят.
     */
    @ApiModelProperty("Флаг свободен\\занят")
    private boolean isBusy;

    /**
     * Стоимость минуты поездки.
     */
    @ApiModelProperty("Стоимость минуты поездки")
    private int minuteCost;

    /**
     * Идентификатор города.
     */
    @ApiModelProperty("Идентификатор города")
    private Long cityID;

    /**
     * Идентификатор машины.
     */
    @ApiModelProperty("Идентификатор машины")
    private Long carID;
}
