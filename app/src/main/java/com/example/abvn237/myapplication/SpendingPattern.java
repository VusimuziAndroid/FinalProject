package com.example.abvn237.myapplication;

/**
 * Created by Vusi Ngwenya on 4/11/2016.
 */
public class SpendingPattern {

    String amountSpent;
    String percentage;
    String SCategory;
    String accNr;
    String Rewards;

    public SpendingPattern(String amtSpent,String perc,String scategory, String accnr){

        this.amountSpent= amtSpent;
        this.percentage = perc;
        this.SCategory=scategory;
        this.accNr=accnr;
    }
    public void setRewards(String rewards){

        this.Rewards=rewards;
    }

    public String getAmountSpent(){

        return this.amountSpent;
    }

    public String getPercentage(){

        return this.percentage;
    }

    public String getSCategory(){

        return this.SCategory;
    }

    public String getAccNr(){

        return this.accNr;
    }

    public String getRewards(){

        return this.Rewards;
    }
}
