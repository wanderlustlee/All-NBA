package com.example.demo.match;

import com.example.demo.matchBean.JsonString;
import com.example.demo.matchBean.ListObject;
import com.example.demo.teammatchBean.TeamJsonString;
import com.example.demo.teammatchBean.TeamListObject2;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



import java.util.ArrayList;
import java.util.List;


public class ResolveJson {
    public List<ListObject> resolve1(String json){
        Gson gson = new Gson();
        JsonString jsonString = gson.fromJson(json,JsonString.class);
        System.out.println(json);
//        System.out.println(jsonString.result.list.get(0).tr.get(0).player1);
        List<ListObject> matchlist = jsonString.result.list;
        return matchlist;
    }

    public List<TeamListObject2> resolve2(String json){
        System.out.println(json);
        TeamJsonString teamJsonString = (new Gson()).fromJson(json,TeamJsonString.class);

        JsonParser parse =new JsonParser();  //创建json解析器
        JsonObject json2=(JsonObject) parse.parse(json);
        System.out.println("reason:"+json2.get("reason").getAsString());
        System.out.println(json2.get("error_code").getAsString());
        JsonObject result=json2.get("result").getAsJsonObject();
        JsonArray array = result.get("list").getAsJsonArray();
        List<TeamListObject2> teammatchlist = new ArrayList<>();
        for(int i=0;i<array.size();i++){
            JsonObject subObject=array.get(i).getAsJsonObject();
            TeamListObject2 listObject2 = new TeamListObject2();
            listObject2.setTime(subObject.get("time").getAsString());
            listObject2.setScore(subObject.get("score").getAsString());
            listObject2.setPlayer1(subObject.get("player1").getAsString());
            listObject2.setPlayer2(subObject.get("player2").getAsString());
            listObject2.setLink1url(subObject.get("link1url").getAsString());
            listObject2.setLink2url(subObject.get("link2url").getAsString());
            teammatchlist.add(listObject2);
        }

        return teammatchlist;
    }
}
