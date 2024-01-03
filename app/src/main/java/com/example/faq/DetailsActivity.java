package com.example.faq;

import static com.example.faq.FAQAdapter.ID_INTENT;
import static com.example.faq.FAQAdapter.THE_DATA_ARRAY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView quesText =findViewById(R.id.ques_text_details);
        TextView answerText =findViewById(R.id.answer_text_details);
        Intent ques_intent= getIntent();
        String received_id = String.valueOf(ques_intent.getIntExtra(ID_INTENT,0));
        ArrayList<String> the_question=ques_intent.getStringArrayListExtra(THE_DATA_ARRAY);
        quesText.setText(the_question.get(0));
     //   answerText.setText(the_question.get(2));

        // Stractcur of ArrayList: 0: question,1: title,2: answer,3: category

       // Toast.makeText(this, "the is id " + received_id+"the question is "+the_question.get(0), Toast.LENGTH_SHORT).show();
    }
}