package com.coc.abhi8.clashofplayers;

/**
 * Created by abhi8 on 2/8/2016.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParsePlayerList {

    public static String[] _names;
    public static String[] _roles;
    public static String[] _expLevels;
    public static String[] _trophies;
    public static String[] _clanRanks;
    public static String[] _previousClanRanks;
    public static String[] _donations;
    public static String[] _donationsReceived;
    public static String[] _leagueBadgeImg;

    public static final String JSON_ARRAY = "memberList";
    public static final String KEY_NAME = "name";
    public static final String KEY_ROLE = "role";
    public static final String KEY_EXPLEVEL = "expLevel";
    public static final String KEY_TROPHIES = "trophies";
    public static final String KEY_CLANRANK = "clanRank";
    public static final String KEY_PRVCLANRANK = "previousClanRank";
    public static final String KEY_DONATIONS = "donations";
    public static final String KEY_DONATIONSRECEIVED= "donationsReceived";
    public static final String KEY_LEAGUEBADGEIMG= "leagueBadgeImg";

    public static Player[] playerArrayList;


    private JSONArray users = null;

    private String playerJSON;

    public ParsePlayerList(String playerJSON){
        this.playerJSON = playerJSON;

    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(playerJSON);
            jsonObject = jsonObject.getJSONObject("clanDetails");
            jsonObject = jsonObject.getJSONObject("results");
            users = jsonObject.getJSONArray(JSON_ARRAY);

            playerArrayList=new Player[users.length()];

//            _names = new String[users.length()];
//            _roles=new String[users.length()];
//            _expLevels = new String[users.length()];
//            _trophies = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                playerArrayList[i]=new Player();
                playerArrayList[i].setName(jo.getString(KEY_NAME));
                playerArrayList[i].setRole(jo.getString(KEY_ROLE));
                playerArrayList[i].setExpLevel(jo.getString(KEY_EXPLEVEL));
                playerArrayList[i].setTrophies(jo.getString(KEY_TROPHIES));
                playerArrayList[i].setClanRank(jo.getString(KEY_CLANRANK));
                playerArrayList[i].setPreviousClanRank(jo.getString(KEY_PRVCLANRANK));
                playerArrayList[i].setDonations(jo.getString(KEY_DONATIONS));
                playerArrayList[i].setDonationsReceived(jo.getString(KEY_DONATIONSRECEIVED));
//                jsonObject=jsonObject.getJSONObject(KEY_LEAGUEBADGEIMG);
                jsonObject=jo.getJSONObject(KEY_LEAGUEBADGEIMG);
                playerArrayList[i].setLeagueBadgeImg(jsonObject.getString("xl"));

//                _trophies[i] = jo.getString(KEY_TROPHIES);
//                _names[i] = jo.getString(KEY_NAME);
//                _expLevels[i] = jo.getString(KEY_EXPLEVEL);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
