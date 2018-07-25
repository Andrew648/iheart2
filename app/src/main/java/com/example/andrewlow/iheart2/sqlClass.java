package com.example.andrewlow.iheart2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class sqlClass extends SQLiteOpenHelper{

    public static final int version = 1;
    public static final String name ="lesson.db";

    public sqlClass(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_Create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(delete);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        /*db.execSQL(entries);
        db.execSQL(entries2);
        db.execSQL(entries3);
        db.execSQL(entries4);
        db.execSQL(entries5);
        db.execSQL(entries6);
        db.execSQL(entries7);*/
    }

    public static class Table1 implements BaseColumns{
        public static final String TABLE_NAME = "Category";
        public static final String column = "Title";
        public static final String column2 = "TopicID";
    }

    public static final String SQL_Create =
            "CREATE TABLE " + Table1.TABLE_NAME + " (" +
                    Table1._ID + " INTEGER PRIMARY KEY," +
                    Table1.column + " TEXT," +
                    Table1.column2 + " INTEGER)";

    public static final String delete =
            "DROP TABLE IF EXISTS " + Table1.TABLE_NAME;

    public static final String entries ="INSERT INTO Category (Title, TopicID) Select 'Gods people',1 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Gods people' AND TopicID = 1)";
    public static final String entries2 ="INSERT INTO Category (Title, TopicID) Select 'Family life',2 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Family Life' AND TopicID = 2)";
    public static final String entries3 ="INSERT INTO Category (Title, TopicID) Select 'Church Life',3 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Church Life' AND TopicID = 3)";
    public static final String entries4 ="INSERT INTO Category (Title, TopicID) Select 'Disciples - corporate',3 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Disciples - corporate' AND TopicID = 4)";
    public static final String entries5 ="INSERT INTO Category (Title, TopicID) Select 'Disciples - individual',4 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Disciples - individual' AND TopicID = 5)";
    public static final String entries6 ="INSERT INTO Category (Title, TopicID) Select 'Disciples - Calling/Destiny',5 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Disciples - Calling/Destiny' AND TopicID = 6)";
    public static final String entries7 ="INSERT INTO Category (Title, TopicID) Select 'Marketplace Life',6 WHERE NOT EXISTS (SELECT * FROM Category WHERE Title = 'Marketplace Life' AND TopicID = 7)";

}


