package ru.zagarazhi.entities.res;

import java.time.LocalDateTime;

import lombok.Data;
import ru.zagarazhi.entities.Game;

//Сущность, необходимая для отображения данных об игре в профиле
@Data
public class ResGame {
    private String hiddenNumber;
    private LocalDateTime startTime;
    private boolean wasAttemptsConstraint;
    private Integer maxAttempts;
    private Integer realAttempts;
    private boolean wasTimeConstraint;
    private Long maxTime;
    private Long time;
    private boolean success;

    public ResGame(Game game){
        this.hiddenNumber = game.getHiddenNumber();
        this.startTime = game.getStartTime();
        this.wasAttemptsConstraint = game.isWasAttemptsConstraint();
        this.maxAttempts = game.getMaxAttempts();
        this.realAttempts = game.getRealAttempts();
        this.wasTimeConstraint = game.isWasTimeConstraint();
        this.maxTime = game.getMaxTime();
        this.time = game.getTime();
        this.success = game.isSuccess();
    }
}
