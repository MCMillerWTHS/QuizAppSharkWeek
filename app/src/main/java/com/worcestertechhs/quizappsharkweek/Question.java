package com.worcestertechhs.quizappsharkweek;

import android.graphics.drawable.Drawable;

public class Question {
    private String qPrompt;
    private boolean correctAnswer;
    private String picture;

    public Question(String qPrompt, boolean correctAnswer, String picture) {
        this.qPrompt = qPrompt;
        this.correctAnswer = correctAnswer;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getqPrompt() {
        return qPrompt;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setqPrompt(String qPrompt) {
        this.qPrompt = qPrompt;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qPrompt='" + qPrompt + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", picture=" + picture +
                '}';
    }
}

