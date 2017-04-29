package com.huruwo.zhanma.db.bmobmodel;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/27.
 */

public class BmItemTable extends BmobObject  {

    //标题
    private String title;
    //内容
    private String content;
    //状态 未做/不熟/简单
    private int state;

    public BmTotalTable getBmTotalTable() {
        return bmtotalTable;
    }

    public void setBmTotalTable(BmTotalTable bmtotalTable) {
        this.bmtotalTable = bmtotalTable;
    }

    private BmTotalTable bmtotalTable; //外键 属于哪个题库


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


}
