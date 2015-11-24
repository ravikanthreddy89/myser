package me.ravikanth.restTest;

import java.util.Map;

/**
 * Created by ragudipati on 11/22/15.
 */
public class Pojo {

    String name;

    Map<String , String> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
