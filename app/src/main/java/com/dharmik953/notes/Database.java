package com.dharmik953.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    Context context;
    private static final String DatabaseName = "MyNotes";
    private static final int DatabaseVersion = 1;

    private static final String TableName = "myNotes";
    private static final String ColumnId = "id";
    private static final String ColumnTitle = "title";
    private static final String ColumnDescription = "description";

    public Database(@Nullable Context context) {
        super(context, DatabaseName, null ,DatabaseVersion );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TableName + " (" + ColumnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ColumnTitle + " TEXT, " + ColumnDescription + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TableName);
        onCreate(db);
    }

    void addNotes(String title, String Description){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(ColumnTitle,title);
//        contentValues.put(ColumnDescription,Description);
//
//        Long result = db.insert(TableName,null,contentValues);
//
//        if (result == -1){
//            Toast.makeText(context, " Failed ", Toast.LENGTH_SHORT).show();
//        }
//        else Toast.makeText(context, " Data added Successfully " , Toast.LENGTH_SHORT).show();
//


        //**********************************************************************************************************
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTitle,title);
        contentValues.put(ColumnDescription,Description);

        db.insert(TableName,null,contentValues);

        if (db.insert(TableName,null,contentValues) == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, " Data added Successfully " , Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllNotes(){
        String quary = "SELECT * FROM " + TableName;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;

        if (database != null){
            cursor = database.rawQuery(quary,null);
        }
        return cursor;
    }

    public void deleteAllnotes() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "DELETE FROM " + TableName;
        database.execSQL(query);
    }

    public void updateNotes(String title, String description, String id) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ColumnTitle,title);
        contentValues.put(ColumnDescription,description);

        database.update(TableName,contentValues,"id=?",new String[]{id});

        if (database.update(TableName,contentValues,"id=?",new String[]{id}) == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, " Data Updated Successfully ", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeSingleItem(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TableName,"id=?",new String[]{id});

        if (database.delete(TableName,"id=?",new String[]{id}) == -1){
            Toast.makeText(context, "Item Not Deleted", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
    }
}
