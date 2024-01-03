package com.example.faq;

import static androidx.constraintlayout.widget.Constraints.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.faq.data.FAQ_Contract;
import com.example.faq.data.FAQ_DbHelper;

import java.util.ArrayList;
import java.util.List;

class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {

    private ArrayList<FAQ_class> tasbihs;
    private Context mContext;
    private FAQ_DbHelper dbHelper;
    private Cursor mCursor;
    public static String ID_INTENT="currentID";
    public static String THE_DATA_ARRAY="the_array_data";
    View mView;

    public FAQAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
       // Toast.makeText(mContext, "constrat", Toast.LENGTH_SHORT).show();
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + mCursor.getCount());

        mCursor.moveToPosition(position);


        @SuppressLint("Range") int id = mCursor.getInt(mCursor.getColumnIndex(FAQ_Contract.FAQEntry._ID));
        @SuppressLint("Range") String question = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_QUESTION));
        @SuppressLint("Range") String title = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_TITEL));
        @SuppressLint("Range") String answer = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_ANSWER));
        @SuppressLint("Range") String count = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_COUNT));
        @SuppressLint("Range") String state = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_STATE));
        @SuppressLint("Range") String category = mCursor.getString(mCursor.getColumnIndex(FAQ_Contract.FAQEntry.COLUMN_FAQ_CATEGORY));


        holder.question.setText(question);
        holder.clickFAQItem(id,question,title,answer,category);
//        holder.messagebox(mContext,fazilat);
//
//        holder.clickTasbihItem(id, category, count, total_count);
//        holder.edit_icon_onClick(id, count, arabic, pashto, farsi,english,fazilat, total_count);
//        holder.delete_icon_OnClick(id);

    }

    @Override
    public int getItemCount() {

        if (mCursor == null) {
            return 0;
        }
      //  Toast.makeText(mContext, "getcout: "+ this.mCursor.getCount(), Toast.LENGTH_SHORT).show();
        return this.mCursor.getCount();

    }



    public Cursor swapCursor(Cursor cursor) {
        if (mCursor == cursor) {
            return null;
        }
        Cursor cursor2 = mCursor;
        mCursor = cursor;
        if (cursor != null) {
            notifyDataSetChanged();
        }
        return cursor2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        FAQ_DbHelper mDbHelper;
        ImageView edit_icon, delete_icon;

        private View mView;
        private TextView question, countText;
        private LinearLayoutCompat mLinearLayout;
        private SQLiteDatabase db;
        private Vibrator vibrator;
        private ImageView infoImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            mLinearLayout = itemView.findViewById(R.id.list_item);

            this.question = itemView.findViewById(R.id.ques_text);


//            delete_icon = itemView.findViewById(R.id.delete_icon);
//            edit_icon = mView.findViewById(R.id.editable_icon);
//            vibrator = (Vibrator) mContext.getSystemService(VIBRATOR_SERVICE);
//            countText = itemView.findViewById(R.id.count);
//            infoImage=itemView.findViewById(R.id.info);


        }
        public void clickFAQItem(int the_id, String question, String title, String answer, String category) {
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra(ID_INTENT, Integer.valueOf(the_id));
                    ArrayList<String> theData= new ArrayList<String>();
                    theData.add(question);
                    theData.add(title);
                    theData.add(answer);
                    theData.add(category);
                    intent.putStringArrayListExtra(THE_DATA_ARRAY,theData);
                    mContext.startActivity(intent);

                }
            });
        }


//
    }

}



