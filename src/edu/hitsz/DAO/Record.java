package edu.hitsz.DAO;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import java.time.LocalDateTime;

public class Record {
    private int score;
    private String name;


    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        LocalDateTime now = LocalDateTime.now();

        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        String result = String.format("%d-%d %d:%d",month,day,hour,minute);

        return result;
    }


}
