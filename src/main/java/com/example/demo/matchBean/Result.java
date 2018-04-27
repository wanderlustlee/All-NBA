package com.example.demo.matchBean;



import java.util.List;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 22:34 2018-04-24
 */
public class Result{
    public String title;
    public Statuslist statuslist;
    public List<ListObject> list;
    public List<TeammatchObject> teammatch;

    public Result(String title, Statuslist statuslist, List<ListObject> list, List<TeammatchObject> teammatch) {
        this.title = title;
        this.statuslist = statuslist;
        this.list = list;
        this.teammatch = teammatch;
    }
}
