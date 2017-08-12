package com.shzlabs.payukickstarter.data.local;

import android.content.ContentValues;
import android.provider.BaseColumns;

import com.shzlabs.payukickstarter.data.model.Kickstarter;


public class Db {

    public Db() {
    }

    public abstract static class TheProjectsTable implements BaseColumns {
        public static final String TABLE_NAME = "projects";

        public static final String COLUMN_SNO = "sno";
        public static final String COLUMN_AMT_PLEDGED = "amt_pledged";
        public static final String COLUMN_BLURB = "blurb";
        public static final String COLUMN_BY = "by";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_END_TIME = "end_time";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_PERCENTAGE_FUNDED = "percentage_funded";
        public static final String COLUMN_NUM_BACKERS = "num_backers";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_URL = "url";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_SNO + " INTEGER , " +
                        COLUMN_AMT_PLEDGED + " INTEGER, " +
                        COLUMN_BLURB + " TEXT, " +
                        COLUMN_BY + " TEXT, " +
                        COLUMN_COUNTRY + " TEXT, " +
                        COLUMN_END_TIME + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_PERCENTAGE_FUNDED + " INTEGER, " +
                        COLUMN_NUM_BACKERS + " TEXT, " +
                        COLUMN_STATE + " TEXT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_URL + " TEXT " +
                        "); ";

        public static ContentValues toContentValues(Kickstarter model) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_SNO, model.getSNo());
            contentValues.put(COLUMN_AMT_PLEDGED, model.getAmtPledged());
            contentValues.put(COLUMN_BLURB, model.getBlurb());
            contentValues.put(COLUMN_BY, model.getBy());
            contentValues.put(COLUMN_COUNTRY, model.getCountry());
            contentValues.put(COLUMN_END_TIME, model.getEndTime());
            contentValues.put(COLUMN_LOCATION, model.getLocation());
            contentValues.put(COLUMN_PERCENTAGE_FUNDED, model.getPercentageFunded());
            contentValues.put(COLUMN_NUM_BACKERS, model.getNumBackers());
            contentValues.put(COLUMN_STATE, model.getState());
            contentValues.put(COLUMN_TITLE, model.getTitle());
            contentValues.put(COLUMN_TYPE, model.getType());
            contentValues.put(COLUMN_URL, model.getUrl());
            return contentValues;
        }
    }
}