package ru.digitalleague.core.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CarModel {

    private Long id;

    /**
     * Модель машины.
     */
    private String model;

    /**
     * Дата создания.
     */
    private OffsetDateTime createDttm;
}
