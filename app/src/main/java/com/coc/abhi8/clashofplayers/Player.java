package com.coc.abhi8.clashofplayers;

/**
 * Created by abhi8 on 2/10/2016.
 */
public class Player {

    public String name="";
    public String role="";
    public String expLevel="";
    public String trophies="";
    public String clanRank="";
    public String previousClanRank="";
    public String donations="";
    public String donationsReceived="";
    public String leagueBadgeImg="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpLevel() {
        return expLevel;
    }

    public void setExpLevel(String expLevel) {
        this.expLevel = expLevel;
    }

    public String getTrophies() {
        return trophies;
    }

    public void setTrophies(String trophies) {
        this.trophies = trophies;
    }

    public String getClanRank() {
        return clanRank;
    }

    public void setClanRank(String clanRank) {
        this.clanRank = clanRank;
    }

    public String getPreviousClanRank() {
        return previousClanRank;
    }

    public void setPreviousClanRank(String previousClanRank) {
        this.previousClanRank = previousClanRank;
    }

    public String getDonations() {
        return donations;
    }

    public void setDonations(String donations) {
        this.donations = donations;
    }

    public String getDonationsReceived() {
        return donationsReceived;
    }

    public void setDonationsReceived(String donationsReceived) {
        this.donationsReceived = donationsReceived;
    }

    public String getLeagueBadgeImg() {
        return leagueBadgeImg;
    }

    public void setLeagueBadgeImg(String leagueBadgeImg) {
        this.leagueBadgeImg = leagueBadgeImg;
    }
}
