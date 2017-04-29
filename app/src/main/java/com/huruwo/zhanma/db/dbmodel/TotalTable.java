package com.huruwo.zhanma.db.dbmodel;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

public class TotalTable extends DataSupport implements Parcelable {
    //可以不写自动生成
    private int id;
   //题库名
    private String table_name;
    //标签
    private String tags;
    //总数
    private int count_num;
    //错误
    private int error_num;
    //完成
    private int  finish_num;
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getTable_name() {
        return table_name;
    }

    public String getTags() {
        return tags;
    }

    public int getCount_num() {
        return count_num;
    }

    public int getError_num() {
        return error_num;
    }

    public int getFinish_num() {
        return finish_num;
    }

    public void setItem_list(List<ItemTable> item_list) {
        this.item_list = item_list;
    }

   public TotalTable(String table_name,String tags,int count_num,int finish_num,int error_num){
       this.count_num=count_num;
       this.finish_num=finish_num;
       this.error_num=error_num;
       this.tags=tags;
       this.table_name=table_name;
   }

    public List<ItemTable> getItem_list() {
        return item_list;
    }


    public  List<ItemTable> getItem_list_forkey() {
        //获得对应id下的所有item
        return DataSupport.where("totaltable_id = ?", String.valueOf(id)).find(ItemTable.class);
    }

    //一对多
    private List<ItemTable> item_list = new ArrayList<ItemTable>();  //一对多


    public void setId(int id) {
        this.id = id;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCount_num(int count_num) {
        this.count_num = count_num;
    }

    public void setError_num(int error_num) {
        this.error_num = error_num;
    }

    public void setFinish_num(int finish_num) {
        this.finish_num = finish_num;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.table_name);
        dest.writeString(this.tags);
        dest.writeInt(this.count_num);
        dest.writeInt(this.error_num);
        dest.writeInt(this.finish_num);
        dest.writeList(this.item_list);
    }

    protected TotalTable(Parcel in) {
        this.id = in.readInt();
        this.table_name = in.readString();
        this.tags = in.readString();
        this.count_num = in.readInt();
        this.error_num = in.readInt();
        this.finish_num = in.readInt();
        this.item_list = new ArrayList<ItemTable>();
        in.readList(this.item_list, ItemTable.class.getClassLoader());
    }

    public static final Parcelable.Creator<TotalTable> CREATOR = new Parcelable.Creator<TotalTable>() {
        @Override
        public TotalTable createFromParcel(Parcel source) {
            return new TotalTable(source);
        }

        @Override
        public TotalTable[] newArray(int size) {
            return new TotalTable[size];
        }
    };
}
