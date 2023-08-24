package com.spotifest.model;

public record DateInfo(String month, int year, String dayOne, int dayOneDate, String dayTwo, int dayTwoDate,
                       String dayThree, int dayThreeDate) {

    @Override
    public String toString() {
        return "DateInfo{" +
                "month='" + month + '\'' +
                ", year=" + year +
                ", dayOne='" + dayOne + '\'' +
                ", dayOneDate=" + dayOneDate +
                ", dayTwo='" + dayTwo + '\'' +
                ", dayTwoDate=" + dayTwoDate +
                ", dayThree='" + dayThree + '\'' +
                ", dayThreeDate=" + dayThreeDate +
                '}';
    }

}
