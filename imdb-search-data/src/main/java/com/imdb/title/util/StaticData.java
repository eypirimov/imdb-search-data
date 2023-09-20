package com.imdb.title.util;

import com.imdb.title.model.Rating;

import java.util.List;
import java.util.Map;

public class StaticData {
    private static List<String> notLivePersonNameList;
    private static Map<String, Rating> titleRatingMap;
    private static List<Integer> sameDirectorAndWriterTitleList;
    private static List<Integer> actorLineList;

    private StaticData() {
    }

    public static List<String> getNotLivePersonNameList() {
        return notLivePersonNameList;
    }

    public static void setNotLivePersonNameList(List<String> notLivePersonNameList) {
        StaticData.notLivePersonNameList = notLivePersonNameList;
    }

    public static Map<String, Rating> getTitleRatingsMap() {
        return titleRatingMap;
    }

    public static void setTitleRatingsMap(Map<String, Rating> titleRatingsMap) {
        StaticData.titleRatingMap = titleRatingsMap;
    }

    public static List<Integer> getSameDirectorAndWriterTitleList() {
        return sameDirectorAndWriterTitleList;
    }

    public static void setSameDirectorAndWriterTitleList(List<Integer> sameDirectorAndWriterTitleList) {
        StaticData.sameDirectorAndWriterTitleList = sameDirectorAndWriterTitleList;
    }

    public static List<Integer> getActorLineList() {
        return actorLineList;
    }

    public static void setActorLineList(List<Integer> actorLineList) {
        StaticData.actorLineList = actorLineList;
    }


}
