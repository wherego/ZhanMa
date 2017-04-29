package com.huruwo.zhanma.model;

import com.huruwo.zhanma.db.dbmodel.ItemTable;
import com.huruwo.zhanma.db.dbmodel.TotalTable;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ModelQuestion   {
    public ItemTable getQuestion(int id) {
        return null;
    }

    public List<ItemTable> getQuestionListById(TotalTable totalTable) {
        List<ItemTable> itemTables= totalTable.getItem_list_forkey();
        return itemTables;
    }
}
