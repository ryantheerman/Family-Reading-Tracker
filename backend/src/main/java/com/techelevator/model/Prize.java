package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class Prize {

    private Long prizeId;
    private String prizeName;
    private String prizeDescription;
    private int milestone;
    private long familyId;  //need to figure out how this comes from the front end
    private int maxPrizes;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    public Prize() { }

    public Prize(long prizeId, String prizeName, String prizeDescription, int milestone, long familyId, int maxPrizes,
                 LocalDate startDate, LocalDate endDate, boolean isActive) {
        this.prizeId = prizeId;
        this.prizeName = prizeName;
        this.prizeDescription = prizeDescription;
        this.milestone = milestone;
        this.familyId = familyId;
        this.maxPrizes = maxPrizes;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeDescription() {
        return prizeDescription;
    }

    public void setPrizeDescription(String prizeDescription) {
        this.prizeDescription = prizeDescription;
    }

    public int getMilestone() {
        return milestone;
    }

    public void setMilestone(int milestone) {
        this.milestone = milestone;
    }

    public long getFamilyId() {
        return familyId;
    }

    public void setFamilyId(long familyId) {
        this.familyId = familyId;
    }

    public int getMaxPrizes() {
        return maxPrizes;
    }

    public void setMaxPrizes(int maxPrizes) {
        this.maxPrizes = maxPrizes;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
