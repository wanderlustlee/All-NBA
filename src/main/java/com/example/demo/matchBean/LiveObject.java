package com.example.demo.matchBean;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 22:34 2018-04-24
 */
public class LiveObject{
    public String title;
    public String player1;
    public String player2;
    public String player1info;
    public String player2info;
    public String player1logobig;
    public String player2logobig;
    public String player1url;
    public String player2url;
    public String player1location;
    public String player2location;
    public String status;
    public String score;
    public String liveurl;

    public LiveObject(String title, String player1, String player2, String player1info, String player2info, String player1logobig, String player2logobig, String player1url, String player2url, String player1location, String player2location, String status, String score, String liveurl) {
        this.title = title;
        this.player1 = player1;
        this.player2 = player2;
        this.player1info = player1info;
        this.player2info = player2info;
        this.player1logobig = player1logobig;
        this.player2logobig = player2logobig;
        this.player1url = player1url;
        this.player2url = player2url;
        this.player1location = player1location;
        this.player2location = player2location;
        this.status = status;
        this.score = score;
        this.liveurl = liveurl;

    }
}
