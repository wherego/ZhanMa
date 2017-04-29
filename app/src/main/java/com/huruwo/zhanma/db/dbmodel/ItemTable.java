package com.huruwo.zhanma.db.dbmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/4/13.
 */

public class ItemTable extends DataSupport implements Parcelable {
    //可以不写自动生成
    private int id;
    //标题
    private String title;
    //内容
    private String content;
    //状态 未做/不熟/简单
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static Creator<ItemTable> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeInt(this.state);
    }

    public ItemTable() {
    }

    protected ItemTable(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<ItemTable> CREATOR = new Parcelable.Creator<ItemTable>() {
        @Override
        public ItemTable createFromParcel(Parcel source) {
            return new ItemTable(source);
        }

        @Override
        public ItemTable[] newArray(int size) {
            return new ItemTable[size];
        }
    };
}
