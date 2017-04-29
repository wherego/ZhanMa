package com.huruwo.zhanma.model;

import com.huruwo.zhanma.db.dbmodel.ItemTable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */

public class ModelError   {

    public List<ItemTable> getErrorQuestionList() {
        return DataSupport.where("iserror = ?", "1").find(ItemTable.class);
    }
}
