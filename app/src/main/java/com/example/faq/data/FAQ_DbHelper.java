package com.example.faq.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.faq.data.FAQ_Contract.FAQEntry;

import static android.content.ContentValues.TAG;

import java.security.KeyStore;

/**
 * Database helper for Toosha app. Manages database creation and version management.
 */
public class FAQ_DbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = FAQ_DbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    public static final String DATABASE_NAME = "FAQ.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 29;
    private Context mycontext;

    public FAQ_DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mycontext = context;
    }


    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_FAQ_TABLE = "CREATE TABLE " + FAQEntry.FAQ_TABLE_NAME + " ("
                + FAQEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FAQEntry.COLUMN_FAQ_QUESTION + " TEXT, "
                + FAQEntry.COLUMN_FAQ_TITEL + " TEXT, "
                + FAQEntry.COLUMN_FAQ_ANSWER + " TEXT, "
                + FAQEntry.COLUMN_FAQ_COUNT + " TEXT, "
                + FAQEntry.COLUMN_FAQ_STATE + " INTEGER, "
                + FAQEntry.COLUMN_FAQ_CATEGORY + " INTEGER ) ";


        // Create a String that contains the SQL statement to create the Daily Amal table
//        String SQL_CREATE_DAILY_AMAL_TABLE = "CREATE TABLE " + DailyAmalEntry.DAILY_AMAL_TABLE_NAME + "(" +
//                DailyAmalEntry.D_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + DailyAmalEntry.COLUMN_AMAL_ID + " INTEGER ,"
//                + DailyAmalEntry.COLUMN_AMAL_DATE + " LONG ,"
//                + DailyAmalEntry.COLUMN_AMAL_STATUS + " INTEGER NOT NULL DEFAULT 0," +
//                " foreign key (" + DailyAmalEntry.COLUMN_AMAL_ID + ") references " + AmalEntry.AMAL_TABLE_NAME + "(" + AmalEntry._ID + "));";

        // Create a String that contains the SQL statement to create the Tasbih table
//        String SQL_CREATE_TASBIH_TABLE = "CREATE TABLE " + TasbihEntry.TASBIH_TABLE_NAME + " ("
//                + TasbihEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + TasbihEntry.COLUMN_TASBIH_ARABIC + " TEXT, "
//                + TasbihEntry.COLUMN_TASBIH_PASHTO + " TEXT, "
//                + TasbihEntry.COLUMN_TASBIH_FARSI + " TEXT, "
//                + TasbihEntry.COLUMN_TASBIH_ENGLISH + " TEXT, "
//                + TasbihEntry.COLUMN_TASBIH_FAZILAT + " TEXT, "
//                + TasbihEntry.COLUMN_TASBIH_COUNT + " INTEGER,"
//                + TasbihEntry.COLUMN_TASBIH_CATEGORY + " INTEGER,"
//                + TasbihEntry.COLUMN_TASBIH_TOTAL_COUNT + " INTEGER, "
//                + TasbihEntry.COLUMN_TASBIH_DEFAULT_COUNT + " INTEGER, "
//                + TasbihEntry.COLUMN_TASBIH_STATE + " INTEGER ) ";


        // Execute the SQL statements
        db.execSQL(SQL_CREATE_FAQ_TABLE);
        new FAQ_Data(db);


        // inserting data
//        FAQ_Data FAQData = new FAQ_Data(db);
    }

    public long insertFAQ(Context context) {
        // Gets the database in write mode
        SQLiteDatabase db = new FAQ_DbHelper(context).getWritableDatabase();


        // Create a ContentValues object where column names are the keys,
        // and Toto's Toosha attributes are the values.
        ContentValues first = new ContentValues();
        first.put(FAQEntry.COLUMN_FAQ_QUESTION, "What is your Name?");
        first.put(FAQEntry.COLUMN_FAQ_ANSWER, "MY NAME IS MOHAMMAD SAMEER");
        first.put(FAQEntry.COLUMN_FAQ_TITEL, "The Name");//1
        //
        ContentValues second = new ContentValues();
        second.put(FAQEntry.COLUMN_FAQ_QUESTION, "What is your Name2?");
        second.put(FAQEntry.COLUMN_FAQ_ANSWER, "MY NAME IS MOHAMMAD SAMEER");
        second.put(FAQEntry.COLUMN_FAQ_TITEL, "The Name");//1

        ContentValues thered = new ContentValues();
        thered.put(FAQEntry.COLUMN_FAQ_QUESTION, "What is your Name3?");
        thered.put(FAQEntry.COLUMN_FAQ_ANSWER, "MY NAME IS MOHAMMAD SAMEER");
        thered.put(FAQEntry.COLUMN_FAQ_TITEL, "The Name");//1

        db.insert(FAQEntry.FAQ_TABLE_NAME, null, first);
        db.insert(FAQEntry.FAQ_TABLE_NAME, null, second);
        db.insert(FAQEntry.FAQ_TABLE_NAME, null, thered);
        Log.d(TAG, "data inserted: ");
        //

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insertAmal() is the Toos table name.
        // The second argument provides the name of a column in which the framework
        // can insertAmal NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insertAmal a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = 0;
        try {
            newRowId = db.insert(FAQEntry.FAQ_TABLE_NAME, null, first);
            newRowId = db.insert(FAQEntry.FAQ_TABLE_NAME, null, second);
            newRowId = db.insert(FAQEntry.FAQ_TABLE_NAME, null, thered);
            Log.d(TAG, "data inserted: ");


        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "insertAmal: " + e.getMessage());
        }

        Log.d(TAG, "insertAmal: Amal  " + newRowId);
        return newRowId;

    }

//    public Cursor selectCurrentDate(Context context, long dataOfamal) {
//        // Create and/or open a database to read from it
//        SQLiteDatabase db = new TooshaDbHelper(context).getReadableDatabase();
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                AmalEntry._ID,
//                AmalEntry.COLUMN_AMAL_DATE,
//                AmalEntry.COLUMN_AMAL_MORNING,
//                AmalEntry.COLUMN_AMAL_NOON,
//                AmalEntry.COLUMN_AMAL_AFTERNON,
//                AmalEntry.COLUMN_AMAL_EVE,
//                AmalEntry.COLUMN_AMAL_NIGHT,
//                AmalEntry.COLUMN_AMAL_TAHJOD,
//                AmalEntry.COLUMN_AMAL_MURMUR,
//                AmalEntry.COLUMN_AMAL_STUDY,
//                AmalEntry.COLUMN_AMAL_SPORT
//        };
//        String selection = AmalEntry.COLUMN_AMAL_DATE + "=?";
//
//        String[] selectionArgs = {String.valueOf(dataOfamal)};
//        // The table to query
//        // The columns to return
//        // The columns for the WHERE clause
//        // The values for the WHERE clause
//        // Don't group the rows
//        // Don't filter by row groups
//
//        /*Cursor cursor = getContentResolver().query(AmalEntry.CONTENT_URI, projection, null, null, null);*/
//
//        Cursor cursor = db.query(
//                AMAL_TABLE_NAME,   // The table to query
//                projection,// The columns to return
//                selection,                  // The columns for the WHERE clause
//                selectionArgs,                  // The values for the WHERE clause
//                null,                  // Don't group the rows
//                null,                  // Don't filter by row groups
//                null);
//
//        return cursor;
//    }

    public void updateAzkar(Context context) {


        SQLiteDatabase db = new FAQ_DbHelper(context).getReadableDatabase();
        // Create a ContentValues object where column names are the keys,
        // and Toto's Toosha attributes are the values.
        db.execSQL("update " + FAQEntry.FAQ_TABLE_NAME + "  set " + FAQEntry.COLUMN_FAQ_COUNT + " = " + FAQEntry.COLUMN_TASBIH_TOTAL_COUNT);
        db.close();

        Log.d(TAG, "updateAzkar: data");

    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FAQEntry.FAQ_TABLE_NAME);

        onCreate(db);
    }

}
