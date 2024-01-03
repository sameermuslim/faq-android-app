package com.example.faq;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.faq.data.FAQ_Contract;
import com.example.faq.data.FAQ_DbHelper;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.faq.databinding.ActivityScrollingBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScrollingActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> , SearchView.OnQueryTextListener{

    private ActivityScrollingBinding binding;
    private static final String TAG = "ScrollingActivity";
    private int category_id;
    private FAQAdapter mFAQAdapter;
    private FAQ_DbHelper mDbHelper;
    SearchView searchView;
    private FAQ_CategoryAdapter mFAQA_cata_dapter;

    // for swiping the list item

    private void bindViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDbHelper = new FAQ_DbHelper(this);

//        searchView =findViewById(R.id.action_search);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                filter(s);
//                return true;
//            }
//        });

    }

    private void filter(String newText) {
        List<String> filteredList= new ArrayList<>();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindViews();
        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        RecyclerView mFAQ_Category_ListView = findViewById(R.id.category_recyclerview);
        RecyclerView mFAQListView = findViewById(R.id.ques_recycle_view);

        mFAQ_Category_ListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mFAQListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mFAQA_cata_dapter = new FAQ_CategoryAdapter(this);
        mFAQ_Category_ListView.setAdapter(mFAQA_cata_dapter);

        mFAQAdapter = new FAQAdapter(this);
        mFAQListView.setAdapter(mFAQAdapter);


        // git add
        //  getSupportLoaderManager().initLoader(121, null,  AzkarActivity.this);
        LoaderManager.getInstance(this).initLoader(121, null, this);


//        Toolbar toolbar = binding.toolbar;
//        setSupportActionBar(toolbar);
//        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
//        toolBarLayout.setTitle(getTitle());


//        FloatingActionButton fab = binding.fab;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDbHelper.insertFAQ(ScrollingActivity.this);
//
//
//                //  mFAQAdapter.notifyAll();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {

        String[] projection = {
                FAQ_Contract.FAQEntry._ID,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_QUESTION,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_TITEL,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_ANSWER,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_COUNT,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_STATE,
                FAQ_Contract.FAQEntry.COLUMN_FAQ_CATEGORY
        };

//       String selection = FAQ_Contract.FAQEntry.COLUMN_FAQ_CATEGORY + "=?";
//        Log.d(TAG, "onCreateLoader: " + category_id);
//        String[] selectionArgs = {String.valueOf(category_id)};
//        // The table to query
        // The columns to return
        // The values for the WHERE clause
        // Don't group the rows
        // Don't filter by row groups

        return new CursorLoader(this, FAQ_Contract.FAQEntry.CONTENT_URI, projection, null, null, null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        mFAQA_cata_dapter.swapCursor(cursor);
        mFAQAdapter.swapCursor(cursor);
//        Log.d(TAG, "count is " + cursor.getCount());
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
