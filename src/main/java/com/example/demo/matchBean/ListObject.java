package com.example.demo.matchBean;



import java.util.List;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 22:32 2018-04-24
 */
public class ListObject{
    public String title;
    public List<LiveObject> live;
    public List<LivelinkObject> livelink;
    public List<TrObject> tr;
    public List<LivelinkObject> bottomlink;

    public ListObject(String title, List<TrObject> tr) {
        this.title = title;
        this.tr = tr;
    }

    public ListObject(String title, List<LiveObject> live, List<LivelinkObject> livelink, List<TrObject> tr, List<LivelinkObject> bottomlink) {
        this.title = title;
        this.live = live;
        this.livelink = livelink;
        this.tr = tr;
        this.bottomlink = bottomlink;
    }
}
