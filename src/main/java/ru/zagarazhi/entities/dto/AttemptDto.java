package ru.zagarazhi.entities.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

//Сущность для записи попытки
@Data
public class AttemptDto {
    @NotEmpty
    private String answear;

    @NotEmpty
    private Long time;

    @NotEmpty
    private Boolean success;
}
