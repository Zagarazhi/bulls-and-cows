package ru.zagarazhi.entities.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

//Сущность для записи игры
@Data
public class GameDto {
    @NotEmpty
    private String hiddenNumber;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @NotEmpty
    private Boolean wasAttemptsConstraint;

    @NotEmpty
    private Boolean wasTimeConstraint;

    @NotEmpty
    private Boolean success;

    private Integer maxAttempts;

    private Long maxTime;

    private AttemptDto[] attempts;
}
