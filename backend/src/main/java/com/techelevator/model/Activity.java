package com.techelevator.model;

import java.time.LocalDate;

public class Activity {

    private long activityId;
    private long userId;
    private int isbn;
    private LocalDate dateRead;
    private int minutesRead;
    private boolean isFinished;


    public Activity() {
    }

    public Activity(long activityId, long userId, int isbn,
                    LocalDate dateRead, int minutesRead, boolean isFinished) {
        this.activityId = activityId;
        this.userId = userId;
        this.isbn = isbn;
        this.dateRead = dateRead;
        this.minutesRead = minutesRead;
        this.isFinished = isFinished;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDateRead() {
        return dateRead;
    }

    public void setDateRead(LocalDate dateRead) {
        this.dateRead = dateRead;
    }

    public int getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(int minutesRead) {
        this.minutesRead = minutesRead;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
