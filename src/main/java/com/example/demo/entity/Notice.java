package com.example.demo.entity;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 16:12 2018-06-13
 */
public class Notice {
    private int id;
    private String notice;
    private String releaseTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }
}
