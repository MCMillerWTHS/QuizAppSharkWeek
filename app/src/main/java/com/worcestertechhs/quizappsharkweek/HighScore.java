package com.worcestertechhs.quizappsharkweek;

import java.util.ArrayList;

public class HighScore
{
    int pScore;
    String pName;

    public HighScore(int pScore, String pName) {
        this.pScore = pScore;
        this.pName = pName;
    }
    public int getpScore() {
        return pScore;

    }

    public void setpScore(int pScore) {
        this.pScore = pScore;

    }

    public String getpName() {
        return pName;

    }

    public void setpName(String pName) {
        this.pName = pName;

    }

    @Override
    public String toString() {
        return "HighScore{" +
                "pScore=" + pScore +
                ", pName='" + pName + '\'' +
                '}';
    }

    public HighScore() {
        pName = "****";
        pScore = 0;
    }
}