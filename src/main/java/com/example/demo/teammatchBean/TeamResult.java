package com.example.demo.teammatchBean;



import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR：wanderlustLee
 * @DATE:Create in 22:34 2018-04-24
 *
 */
public class TeamResult {
    public String title;
    public ArrayList<TeamListObject> teamlist;
    public MoreObject moreObject1;
    public MoreObject moreObject2;

    public TeamResult() {
    }

    public TeamResult(String title, ArrayList<TeamListObject> teamlist, MoreObject moreObject1, MoreObject moreObject2) {
        this.title = title;
        this.teamlist = teamlist;
        this.moreObject1 = moreObject1;
        this.moreObject2 = moreObject2;
    }
}
