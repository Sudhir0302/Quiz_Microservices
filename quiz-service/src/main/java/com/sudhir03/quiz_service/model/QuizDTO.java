package com.sudhir03.quiz_service.model;

public class QuizDTO
{
    String categoryName;
    Integer numQns;
    String title;

    public QuizDTO(String categoryName, Integer numQns, String title) {
        this.categoryName = categoryName;
        this.numQns = numQns;
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getNumQns() {
        return numQns;
    }

    public void setNumQns(Integer numQns) {
        this.numQns = numQns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
