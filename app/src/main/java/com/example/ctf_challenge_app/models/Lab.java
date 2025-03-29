package com.example.ctf_challenge_app.models;

public class Lab {
    private String labKey;
    private String title;
    private String description;
    private int status;
    private String challengeType;


    public Lab(String labKey, String title, String description, int status, String challengeType) {
        this.labKey = labKey;
        this.title = title;
        this.description = description;
        this.status = status;
        this.challengeType = challengeType;
    }

    public String getLabKey() { return labKey; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getStatus() { return status; }
    public String getChallengeType() { return challengeType; }

    public void setStatus(int status) { this.status = status; }

    @Override
    public String toString() {
        return "Lab{" +
                "labKey='" + labKey + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", challengeType='" + challengeType + '\'' +
                '}';
    }
}
