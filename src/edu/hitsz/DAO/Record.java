package edu.hitsz.DAO;

import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

public class Record {
    private int score;
    private String name;
    private String time;


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
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
