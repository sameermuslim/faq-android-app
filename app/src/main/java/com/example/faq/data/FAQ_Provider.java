package com.example.faq.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


/**
 * Created by Sameer Muslim on 3/7/2018.
 */

public class FAQ_Provider extends ContentProvider {
    public static final String LOG_TAG = FAQ_Provider.class.getSimpleName();
    /**
     * URI matcher code for the content URI for the Daily_amal table
     */
    private static final int FAQ = 100;
    /**
     * URI matcher code for the content URI for  a single Daily_amal in the pets table
     */
    private static final int FAQ_ID = 101;

    /**
     * URI matcher code for the content URI for the amal table
     */
    private static final int AMAL = 200;
    /**
     * URI matcher code for the content URI for  a single amal in the pets table
     */
    private static final int AMAL_ID = 201;

    /**
     * URI matcher code for the content URI for the TASBIH table
     */
    private static final int TASBIH = 300;
    /**
     * URI matcher code for the content URI for  a single TASBIH in the pets table
     */
    private static final int TASBIH_ID = 301;

    private static final int AMAL_VIEW_WEEK=700;
    private static final int AMAL_VIEW_MONTH=30000;

    /**
     * UriMatcher object to match a content URI to a corresponding code.
     * The input passed into the constructor represents the code to return for the root URI.
     * It's common to use NO_MATCH as the input for this case.
     */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.

        // The content URI of the form "content://com.sameer.daily_amal/daily_amal" will map to the
        // integer code {@link #daily_amal}. This URI is used to provide access to MULTIPLE rows
        // of the daily_amal table.
        sUriMatcher.addURI(FAQ_Contract.CONTENT_AUTHORITY, FAQ_Contract.PATH_FAQ, FAQ);

        // The content URI of the form "content://com.sameer.daily_amal/daily_amal/#" will map to the
        // integer code {@link #PET_ID}. This URI is used to provide access to ONE single row
        // of the daily_amal table.
        //
        // In this case, the "#" wildcard is used where "#" can be substituted for an integer.
        // For example, "content://com.sameer.daily_amal/daily_amal/2" matches, but
        // "content://com.sameer.daily_amal/daily_amal" (without a number at the end) doesn't match.
        sUriMatcher.addURI(FAQ_Contract.CONTENT_AUTHORITY, FAQ_Contract.PATH_FAQ + "/#", FAQ_ID);


    }

    private FAQ_DbHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new FAQ_DbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case FAQ:

                cursor = database.query(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case FAQ_ID:
                cursor = database.query(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);


        }

        // Set notification URI on the Cursor,
        // so we know what content URI the Cursor was created for.
        // If the data at this URI changes, then we know we need to updateAzkar the Cursor.
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the cursor
        return cursor;

    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        if (match == FAQ) {
            return insertFAQ(uri, contentValues);
        }
        throw new IllegalArgumentException("Insertion is not supported for " + uri);
    }

    private Uri insertFAQ(Uri uri, ContentValues values) {
        // Check that the name is not null
        String question = values.getAsString(FAQ_Contract.FAQEntry.COLUMN_FAQ_QUESTION);
        if (question == null) {
            throw new IllegalArgumentException(" requires a Category");
        }
        // Check that the morning is valid
        String answer = values.getAsString(FAQ_Contract.FAQEntry.COLUMN_FAQ_ANSWER);
        if (answer == null) {
            throw new IllegalArgumentException("requires count");
        }
        // Check that the morning is valid
        String title = values.getAsString(FAQ_Contract.FAQEntry.COLUMN_FAQ_TITEL);
        if (title == null) {
            throw new IllegalArgumentException("requires arabic text");
        }

        Integer Count = values.getAsInteger(FAQ_Contract.FAQEntry.COLUMN_FAQ_COUNT);
        if (Count == null) {
            throw new IllegalArgumentException("requires arabic text");
        }
        Integer category = values.getAsInteger(FAQ_Contract.FAQEntry.COLUMN_FAQ_CATEGORY);
        if (category == null) {
            throw new IllegalArgumentException("requires arabic text");
        }

        SQLiteDatabase database = mDBHelper.getWritableDatabase();

        // Insert the new pet with the given values
        long id = database.insert(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insertAmal row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }


//    private Uri insertAmal(Uri uri, ContentValues values) {
//        // Check that the name is not null
//        String dateA = values.getAsString(DailyAmalEntry.COLUMN_AMAL_DATE);
//        if (dateA == null) {
//            throw new IllegalArgumentException(" requires a Date");
//        }
//        // Check that the morning is valid
//        Integer eve = values.getAsInteger(DailyAmalEntry.COLUMN_AMAL_ID);
//        if (eve == null) {
//            throw new IllegalArgumentException("requires amla_id Chick");
//        }
//        // Check that the morning is valid
//        Integer night = values.getAsInteger(DailyAmalEntry.COLUMN_AMAL_STATUS);
//        if (night == null) {
//            throw new IllegalArgumentException("requires status Chick");
//        }
//
//        SQLiteDatabase database = mDBHelper.getWritableDatabase();
//
//        // Insert the new pet with the given values
//        long id = database.insert(DailyAmalEntry.DAILY_AMAL_TABLE_NAME, null, values);
//        // If the ID is -1, then the insertion failed. Log an error and return null.
//        if (id == -1) {
//            Log.e(LOG_TAG, "Failed to insertAmal row for " + uri);
//            return null;
//        }
//            getContext().getContentResolver().notifyChange(uri, null);
//
//        return ContentUris.withAppendedId(uri, id);
//    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FAQ:
                return updateFAq(uri, contentValues, selection, selectionArgs);
            case FAQ_ID:
                // For the NAMA_ID code, extract out the ID from the URI,
                // so we know which row to updateAzkar. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = FAQ_Contract.FAQEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateFAq(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }


    }

    private int updateFAq(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

// Check that the morning is valid
        Integer noon = values.getAsInteger(FAQ_Contract.FAQEntry._ID);
        if (noon == null) {
            throw new IllegalArgumentException("requires Noon Chick");
        }// Check that the morning is valid

        // No need to check the breed, any value is valid (including null).

        // If there are no values to updateAzkar, then don't try to updateAzkar the database
        if (values.size() == 0) {
            return 0;
        }

        // Otherwise, get writeable database to updateAzkar the data
        SQLiteDatabase database = mDBHelper.getWritableDatabase();

        // Perform the updateAzkar on the database and get the number of rows affected
        int rowsUpdated = database.update(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, values, selection, selectionArgs);

        // If 1 or more rows were updated, then notify all listeners that the data at the
        // given URI has changed
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows updated
        return rowsUpdated;
    }

//    private int updateTasbih(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
//// Check that the morning is valid
//        Integer count = values.getAsInteger(FAQ_Contract.FAQEntry.COLUMN_TASBIH_COUNT);
//        if (count == null) {
//            throw new IllegalArgumentException("requires count Chick");
//        }// Check that the count is valid
//
//        Integer total = values.getAsInteger(FAQ_Contract.FAQEntry.COLUMN_TASBIH_TOTAL_COUNT);
//        if (total == null) {
//            throw new IllegalArgumentException("requires total count Chick");
//        }// Check that the count is valid
//        // No need to check the breed, any value is valid (including null).
//        // If there are no values to updateAzkar, then don't try to updateAzkar the database
//        if (values.size() == 0) {
//            return 0;
//        }
//
//        // Otherwise, get writeable database to updateAzkar the data
//        SQLiteDatabase database = mDBHelper.getWritableDatabase();
//
//        // Perform the updateAzkar on the database and get the number of rows affected
//        int rowsUpdated = database.update(FAQ_Contract.FAQEntry.TASBIH_TABLE_NAME, values, selection, selectionArgs);
//
//        // If 1 or more rows were updated, then notify all listeners that the data at the
//        // given URI has changed
//        if (rowsUpdated != 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        // Return the number of rows updated
//        return rowsUpdated;
//    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mDBHelper.getWritableDatabase();

        // Track the number of rows that were deleted
        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FAQ:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, selection, selectionArgs);
                break;
            case FAQ_ID:
                // Delete a single row given by the ID in the URI
                selection = FAQ_Contract.FAQEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(FAQ_Contract.FAQEntry.FAQ_TABLE_NAME, selection, selectionArgs);
                break;

            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        // If 1 or more rows were deleted, then notify all listeners that the data at the
        // given URI has changed
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of rows deleted
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case FAQ:
                return FAQ_Contract.FAQEntry.CONTENT_LIST_TYPE;
            case FAQ_ID:
                return FAQ_Contract.FAQEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

}
