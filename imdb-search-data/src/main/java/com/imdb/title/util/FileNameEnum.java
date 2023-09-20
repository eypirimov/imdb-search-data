package com.imdb.title.util;

public enum FileNameEnum {
    NAMES_BASICS("datasets/name.basics.tsv"),
    TITLE_RATINGS("datasets/title.ratings.tsv"),
    TITLE_CREW("datasets/title.crew.tsv"),
    TITLE_BASICS("datasets/title.basics.tsv"),
    TITLE_PRINCIPALS("datasets/title.principals.tsv");
    String filePath;

    FileNameEnum(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
