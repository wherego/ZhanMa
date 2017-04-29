package com.huruwo.zhanma.db.bmobmodel;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/27.
 */

public class BmTotalTable extends BmobObject{


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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String source;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getCount_num() {
        return count_num;
    }

    public void setCount_num(int count_num) {
        this.count_num = count_num;
    }

    public int getError_num() {
        return error_num;
    }

    public void setError_num(int error_num) {
        this.error_num = error_num;
    }

    public int getFinish_num() {
        return finish_num;
    }

    public void setFinish_num(int finish_num) {
        this.finish_num = finish_num;
    }
}
