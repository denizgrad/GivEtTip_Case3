package models;

public class Rank {

    private int id;
    private int userId;
    private int position;
    private double score;
    private String userEmail;
    private String scoreString;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getScoreString() {
        String positionS = String.format("%4d", this.getPosition()).toString();
        String emailS = String.format("%s", this.getUserEmail()).toString();
        String scoreS = String.format("%4.1f", this.getScore()).toString();
        return  positionS + "\t\t" + emailS + ":\t\t" + scoreS;
    }
    public Rank(int userId, int position, double score, String userEmail) {
        this.userId = userId;
        this.position = position;
        this.score = score;
        this.userEmail = userEmail;
    }
}