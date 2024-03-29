 package com.example.mybiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSION = 1;


//-----------------CREDITOR TABLE-----------------
    private static final String CREATE_QUERY =
            "CREATE TABLE "+ Creditors.NewCreditorInfo.TABLE_NAME+"("+
                    Creditors.NewCreditorInfo.USER_NAME+" TEXT,"+
                    Creditors.NewCreditorInfo.USER_PHONE+" TEXT,"+
                    Creditors.NewCreditorInfo.USER_AMOUNT+" TEXT," +
                    Creditors.NewCreditorInfo.USER_DATE+" TEXT);";

//-----------------INCOME TABLE--------------------
    private static final String CREATE_QUERY1 =
        "CREATE TABLE "+ Income.NewIncomeInfo.TABLE_NAME+"("+
                Income.NewIncomeInfo.INCOME_DATE+" TEXT,"+
                Income.NewIncomeInfo.SOURCE+" TEXT,"+
                Income.NewIncomeInfo.INCOME_AMOUNT+" TEXT);";

    public DBhelper(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created / opened...");
    }




    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Creditor Table Created...");

        db.execSQL(CREATE_QUERY1);
        Log.e("DATABASE OPERATIONS","Income Table Created...");
    }

//------------------add creditors------------

//    public void addInformation(String name, String phone, String amount, String data, SQLiteDatabase db){
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Creditors.NewCreditorInfo.USER_NAME,name);
//        contentValues.put(Creditors.NewCreditorInfo.USER_PHONE,phone);
//        contentValues.put(Creditors.NewCreditorInfo.USER_AMOUNT,amount);
//        contentValues.put(Creditors.NewCreditorInfo.USER_DATE,data);
//        db.insert(Creditors.NewCreditorInfo.TABLE_NAME,null,contentValues);
//        Log.e("DATABASE OPERATIONS","One Row Inserted...");
//    }

//---------------------add income-----------------

    public void addIncomeInfo(String source,String date, String amount,SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Income.NewIncomeInfo.SOURCE,source);
        contentValues.put(Income.NewIncomeInfo.INCOME_DATE,date);
        contentValues.put(Income.NewIncomeInfo.INCOME_AMOUNT,amount);
        db.insert(Income.NewIncomeInfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE_OPERATIONS","One row is inserted...");
    }

    public Cursor getInformations(SQLiteDatabase db){
        Cursor cursor;
        String[] projections={Income.NewIncomeInfo.INCOME_DATE,
                Income.NewIncomeInfo.SOURCE,
                Income.NewIncomeInfo.INCOME_AMOUNT };
        cursor=db.query(Income.NewIncomeInfo.TABLE_NAME,projections,null,null,
                null,null,null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
