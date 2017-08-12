package com.shzlabs.payukickstarter.data.local;

import android.database.Cursor;
import android.util.Log;

import com.shzlabs.payukickstarter.data.model.Kickstarter;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class DatabaseHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder builder = new SqlBrite.Builder();
        mDb = builder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<List<Kickstarter>> getProjects() {
        ArrayList<String> tables = new ArrayList<>();
        tables.add(Db.TheProjectsTable.TABLE_NAME);

        String query = "SELECT * FROM " + Db.TheProjectsTable.TABLE_NAME;

        return mDb.createQuery(tables, query)
                .mapToList(new Func1<Cursor, Kickstarter>() {
                    @Override
                    public Kickstarter call(Cursor cursor) {
                        Kickstarter model = new Kickstarter();
                        model.setSNo(cursor.getInt(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_SNO)));
                        model.setAmtPledged(cursor.getInt(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_AMT_PLEDGED)));
                        model.setBlurb(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_BLURB)));
                        model.setBy(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_BY)));
                        model.setCountry(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_COUNTRY)));
                        model.setEndTime(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_END_TIME)));
                        model.setLocation(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_LOCATION)));
                        model.setPercentageFunded(cursor.getInt(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_PERCENTAGE_FUNDED)));
                        model.setNumBackers(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_NUM_BACKERS)));
                        model.setState(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_STATE)));
                        model.setTitle(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_TITLE)));
                        model.setType(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_TYPE)));
                        model.setUrl(cursor.getString(cursor.getColumnIndex(Db.TheProjectsTable.COLUMN_URL)));

                        return model;
                    }
                });
    }

    public Observable<Kickstarter> setProjects(final List<Kickstarter> items) {
        return Observable.create(new Observable.OnSubscribe<Kickstarter>() {
            @Override
            public void call(Subscriber<? super Kickstarter> subscriber) {

                if (subscriber.isUnsubscribed()) return;

                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.TheProjectsTable.TABLE_NAME, null, null);
                    for (Kickstarter item: items) {
                        long result = mDb.insert(Db.TheProjectsTable.TABLE_NAME,
                                Db.TheProjectsTable.toContentValues(item));
                        if (result >=0) {
                            subscriber.onNext(item);
                        }else{
                            Log.e(TAG, "addItem: Error inserting to table " + Db.TheProjectsTable.TABLE_NAME);
                        }
                    }
                    subscriber.onCompleted();
                    transaction.markSuccessful();
                } finally {
                    transaction.end();
                }

            }
        });
    }

    public void additem(Kickstarter item) {
        BriteDatabase.Transaction transaction = mDb.newTransaction();

        try {
            long result = mDb.insert(Db.TheProjectsTable.TABLE_NAME,
                    Db.TheProjectsTable.toContentValues(item));
            if (result <0) Log.e(TAG, "addItem: Error inserting to table " + Db.TheProjectsTable.TABLE_NAME);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }
}