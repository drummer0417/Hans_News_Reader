package nl.androidappfactory.hackernewsreader;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HA256157 on 20/12/2015.
 */
public class DbHelper {

    private static final String NEWS_DB = "newsDB";
    private static final String TABLE_NEWS_ITEMS = "newsItems";
    private static final String COL_DESCRIPTION = "descr";
    private static final String COL_URL = "url";
    private static final String COL_ID = "id";
    private static DbHelper dbHelper;

    private static SQLiteDatabase newsDB;

    private DbHelper(Context context) {
        newsDB = context.openOrCreateDatabase(NEWS_DB, Context.MODE_PRIVATE, null);

        String queryString = "DROP TABLE " + TABLE_NEWS_ITEMS;
        Log.d("DbHelper drop", queryString);
        newsDB.execSQL(queryString);

        String createQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NEWS_ITEMS  + " (" + COL_DESCRIPTION
                + " VARCHAR, " + COL_URL + " VARCHAR, " + COL_ID + " INTEGER PRIMARY KEY" + ")";
        Log.d("DbHelper create table", createQuery);
        dbHelper.newsDB.execSQL(createQuery);
    }

    public static DbHelper getInstance(Context context) {

        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
        return dbHelper;
    }

    public List getAllNewsItems() {
        List result = new ArrayList<String>();
        String queryString = "SELECT * FROM " + TABLE_NEWS_ITEMS;
//        Log.d("DbHelper query", queryString);
        Cursor c = queryNewsItems(queryString);
        while (c.moveToNext()) {
            int descIndex = c.getColumnIndex(COL_DESCRIPTION);
            int urlIndex = c.getColumnIndex(COL_URL);
            int idIndex = c.getColumnIndex(COL_ID);
            NewsItem item = new NewsItem(c.getString(descIndex), c.getString(urlIndex), c.getInt(idIndex));
//            Log.d("DbHelper item", item.getDescription() + ", " + item.getUrl()+ ", " + item.getId());
            result.add(item);
        }
        return result;
    }

    public void insertItem(String desctiption, String url) {
        try {
            String insertQuery = "INSERT INTO " + TABLE_NEWS_ITEMS + " (" + COL_DESCRIPTION + ", " + COL_URL + ") VALUES ('" + desctiption + "', '" + url + "')";
//            Log.d("DbHelper insert", insertQuery);
            newsDB.execSQL(insertQuery);
        } catch (Exception e) {
            Log.e("DbHelper ", e.getMessage());
        }
    }

    private Cursor queryNewsItems(String query) {
        return newsDB.rawQuery(query, null);
    }

    public void deleteAllNewsItems() {
        newsDB.execSQL("DELETE FROM " + TABLE_NEWS_ITEMS);
    }

}
