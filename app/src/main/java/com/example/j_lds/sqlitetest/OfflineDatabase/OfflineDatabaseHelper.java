package com.example.j_lds.sqlitetest.OfflineDatabase;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class OfflineDatabaseHelper extends SQLiteOpenHelper {
    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window

    private static final String DATABASE_NAME ="OfflineChecker.db";
    private static final String TABLE_NAME = "OfflineDataChecker";
    private static final String COL_1 ="ID";
    private static final String COL_2 ="clientChecker";
    private static final String COL_3 ="categoriesChecker";
    private static final String COL_4 ="productsChecker";


    public OfflineDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Step 1");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" INTEGER, "+COL_3+" INTEGER, "+COL_4+" INTEGER )");
        //db.execSQL("CREATE TABLE OfflineDataChecker (ID INTEGER PRIMARY KEY AUTOINCREMENT, clientChecker INTEGER, categoriesChecker INTEGER, productsChecker INTEGER )");
        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Step 2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Step 3");
        onCreate(db);
        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Step 4");
    }

    public boolean insertDataCheckStatus(int clientCheck, int categoriesCheck, int productsCheck){
        Log.e(OfflineDatabaseHelper.class.getName()+" : ", "Step 5");

        SQLiteDatabase db = this.getWritableDatabase();

        boolean result = false;
        if (clientCheck == 0 && categoriesCheck == 0 && productsCheck == 0){
            db.execSQL("insert into OfflineDataChecker values(null, "+clientCheck+", "+categoriesCheck+", "+productsCheck+")");
            result = true;
            Log.e("DB: ", "insert is done");
        }else {
            result = false;
            Log.e("DB: ", "insert failed");
        }

        if (!result){
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }

    //get all data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    // Updating the check
    public boolean updateOfflineClientCheck(int clientStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result = false;

        // clientChecker is false
        if (clientStatus == 0){
            db.execSQL("update OfflineDataChecker set clientChecker = 0 where ID == 1");
            result = false;
        }
            // clientChecker is true
            if (clientStatus == 1){
                db.execSQL("update OfflineDataChecker set clientChecker = 1 where ID == 1");
                result = true;
        }

        // updating row
        if (!result){
            db.close();
            return false;
        }else {
            db.close();
            return true;
        }
    }

    public Integer deleteDataCheckStatus(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1+" = ?", new String[] {String.valueOf(id)});
    }
}




































