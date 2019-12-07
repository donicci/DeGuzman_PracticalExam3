package com.example.deguzman_prac3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "student.db";
    final static int ver = 1;
    final static String table = "grade";

    public DBHelper(Context context) {
        super(context, DBName, null, ver);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cTable = "CREATE TABLE grade (ID INTEGER PRIMARY KEY AUTOINCREMENT, Cname TEXT, Vnum TEXT, Rdate TEXT, Aplvl TEXT)";
        db.execSQL(cTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dTable = "DROP TABLE IF EXISTS grade";
        db.execSQL(dTable);
        onCreate(db);
    }

    public boolean insert(String cname, String vnum, String rdate, String aplvl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Code Name", cname);
        cv.put("Lname", vnum);
        cv.put("LGrade", rdate);
        cv.put("API Level", aplvl);
        long inserted = db.insert(table, null, cv);
        if (inserted == -1){
            return false;
        } else return true;
    }

    public Cursor selectRecord(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM grade", null);
    }


    public boolean update(String id, String cname, String vnum, String rdate, String aplvl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Code Name", cname);
        cv.put("Lname", vnum);
        cv.put("LGrade", rdate);
        cv.put("API Level", aplvl);
        db.update(table,cv,"ID=?", new String[]{id});
        return true;
    }

    public boolean delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table,"ID=?", new String[]{id});
        return true;
    }
}
