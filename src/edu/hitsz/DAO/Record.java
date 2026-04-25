package edu.hitsz.DAO;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import java.time.LocalDateTime;

public class Record { //记录分数，日期，用户名，即示例中的Book类，我可以在游戏结束时创建一个record对象，传入score
    private int score;
    private String name;
    private String time;

    public Record(int score, String name){
        this.name = name;
        this.score = score;
        this.time = setNowTime();
    }

    public Record(int score, String name, String time){
        this.name = name;
        this.score = score;
        this.time = time;
    }

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

    public String setNowTime() {
        LocalDateTime now = LocalDateTime.now();

        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        String result = String.format("%d-%d-%d:%d",month,day,hour,minute);

        return result;
    }
    public String getTime(){
        return time;
    }


}
