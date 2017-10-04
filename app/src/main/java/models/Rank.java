package models;

import java.io.Console;
import java.io.InterruptedIOException;
import java.io.PrintStream;
import java.math.BigDecimal;

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
        String scoreS = String.format("%04.1f", this.getScore()).toString();

        String positionString = setOutputLength(this.getPosition(), 4);
        int l = positionS.length();
        String emailString = setOutputLength(this.getUserEmail(), 30);
        int ll = emailString.length();
        String scoreString = setOutputLength(this.getScore(), 8);
        int lll = scoreString.length();
        String space = "     ";

        String res = positionString + space + emailString + space + scoreString;

        String dol = Integer.toString(l) + ", " + Integer.toString(ll) + ", " + Integer.toString(lll) + ", " + Integer.toString(res.length());
        System.out.println("LENGTHHHHHHHH: " + dol);

        return res;

//        return  positionS + "\t\t" + emailS + ":\t\t" + scoreS;
//        return this.getPosition() + ": " + this.getUserEmail() + " - " + this.getScore();
    }

    public static String setOutputLength(int n, int length) {
        String s = Integer.toString(n);
        while (s.length() <= length)
            s = " " + s;
        return s;
    }

    public static String setOutputLength(double n, int length) {
        String s = Double.toString(n);
        while (s.length() <= length)
            s = " " + s;
        return s;
    }

    public static String setOutputLength(String n, int length) {
        while (n.length() <= length)
            n = n + " ";
        return n;
    }

    public Rank(int userId, int position, double score, String userEmail) {
        this.userId = userId;
        this.position = position;
        this.score = score;
        this.userEmail = userEmail;
    }
}