package com.example.faq.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;

public final class FAQ_Contract {



    public static final String CONTENT_AUTHORITY = "com.example.faq";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAQ = "faq";
    Context context;


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private FAQ_Contract() {
    }


    /**
     * Inner class that defines constant values for the Toosha database Amal Table.
     * Each entry in the table represents a single Amal.
     */
//    public static final class AmalEntry implements BaseColumns {
//        // Constant Values of prayers
//        public static final int FJR = 1;
//        public static final int ZOHOR = 2;
//        public static final int ASR = 3;
//        public static final int MAGHRIB = 4;
//        public static final int ISHA = 5;
//        public static final int TAHAJUD = 6;
//        public static final int CHARITY = 7;
//        public static final int STUDY = 8;
//        public static final int SPORT = 9;
//
//
//        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FAQ);
//
//        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAQ;
//
//        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAQ;

//        /**
//         * Name of the Amal Table
//         * <p>
//         * Type: TEXT
//         */
//        public final static String AMAL_TABLE_NAME = "amal";
//        /**
//         * Unique ID number for the Amal (only for use in the database table).
//         * <p>
//         * Type: INTEGER
//         */
//        public final static String _ID = BaseColumns._ID;
//        /**
//         * Name of the Amal.
//         * <p>
//         * Type: TEXT
//         */
//        public final static String COLUMN_AMAL_NAME = "amal_name";
//    }

    /**
     * Inner class that defines constant values for the Toosha database Tasbih Table.
     * Each entry in the table represents a single Tasbih.
     */
    public static final class FAQEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_FAQ);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAQ;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FAQ;


        /**
         * Name of the Tasbih table.

         */
        public final static String FAQ_TABLE_NAME = "faq";

        /* Unique ID number for the Daily Amal (only for use in the database table).
         *
         * Type: INTEGER
         */
        public static final String _ID = BaseColumns._ID;
        /**
         * arabic meaning of the Tasbih.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_FAQ_QUESTION = "question";
        //Type: TEXT
        public static final String COLUMN_FAQ_ANSWER = "answer";
        //Type: TEXT
         /**
         * Count of the tasbih.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_FAQ_COUNT = "count";
        /**
         * Category of the tasbih.
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_FAQ_CATEGORY = "category";
        //Type: BOOLEAN
        public static final String COLUMN_FAQ_STATE = "state";
        //Type: INTEGER
        public static final String COLUMN_TASBIH_TOTAL_COUNT = "total_count";
        //Type: TEXT
        public static final String COLUMN_FAQ_TITEL = "fazilat";
    }


}

