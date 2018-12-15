package com.example.pawan.androidtemplate.models;

import java.util.List;

public class FactResponse {
    private String title;
    private List<Fact> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Fact> getRows() {
        return rows;
    }

    public void setRows(List<Fact> rows) {
        this.rows = rows;
    }
}
