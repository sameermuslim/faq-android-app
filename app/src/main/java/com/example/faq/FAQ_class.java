package com.example.faq;

import android.os.Parcel;
import android.os.Parcelable;

public class FAQ_class implements Parcelable {
    public static final Creator<FAQ_class> CREATOR = new Creator<FAQ_class>() {

        @Override
        public FAQ_class createFromParcel(Parcel parcel) {
            return new FAQ_class(parcel);
        }

        @Override
        public FAQ_class[] newArray(int size) {
            return new FAQ_class[size];
        }


    };
    private int viewType;
    private int faq_id;
    private String question;
    private String title;
    private String answer;
    private int count;
    private int state;
    private int category;

    public FAQ_class() {
    }

    public FAQ_class(int faq_id, String question, String title, String farsi, int count, int state, int category) {
        this.faq_id = faq_id;
        this.question = question;
        this.title = title;
        this.answer = farsi;
        this.count = count;
        this.state = state;
        this.category = category;
    }
    public FAQ_class(int faq_id, int count, int state, int category) {
        this.faq_id = faq_id;
        this.count = count;
        this.state = state;
        this.category = category;
    }

    public FAQ_class(int faq_id, String question, int count) {
        this.faq_id = faq_id;
        this.question = question;
        this.count = count;
    }

    public FAQ_class(int category, int count, int total_count) {

    }

    protected FAQ_class(Parcel parcel) {
        this.faq_id = parcel.readInt();
        this.viewType = parcel.readInt();
        this.question = parcel.readString();
        this.answer = parcel.readString();
        this.count = parcel.readInt();
        this.state = parcel.readInt();
        this.category = parcel.readInt();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getFaq_id() {
        return faq_id;
    }

    public void setFaq_id(int faq_id) {
        this.faq_id = faq_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Tasbih{" +
                "tasbih_id=" + faq_id +
                ", arabic='" + question + '\'' +
                ", farsi='" + answer + '\'' +
                ", count=" + count +
                ", state=" + state +
                ", category=" + category +
                '}';
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(this.faq_id);
        parcel.writeString(this.question);
        parcel.writeString(this.answer);
        parcel.writeInt(this.count);
        parcel.writeInt(this.viewType);
        parcel.writeInt(this.state);
        parcel.writeInt(this.category);
        parcel.writeInt(this.faq_id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}