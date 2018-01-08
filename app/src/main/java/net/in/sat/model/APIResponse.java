package net.in.sat.model;

import java.util.List;


public class APIResponse {


    private List<Category> categories;
    private List<Ranking> rankings;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }



}
