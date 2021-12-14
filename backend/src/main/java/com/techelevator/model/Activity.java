package com.techelevator.model;

import java.time.LocalDate;

public class Activity {

    private long activityId;
    private long userId;
    private String isbn;
    private LocalDate dateRead;
    private int minutesRead;
    private boolean isFinished;
    private String mediaType;


    public Activity() {
    }

    public Activity(long activityId, long userId, String isbn,
                    LocalDate dateRead, int minutesRead, boolean isFinished, String mediaType) {
        this.activityId = activityId;
        this.userId = userId;
        this.isbn = isbn;
        this.dateRead = dateRead;
        this.minutesRead = minutesRead;
        this.isFinished = isFinished;
        this.mediaType = mediaType;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public boolean getisFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
