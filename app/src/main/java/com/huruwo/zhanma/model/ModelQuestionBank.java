package com.huruwo.zhanma.model;

import com.huruwo.zhanma.db.dbmodel.TotalTable;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ModelQuestionBank   {
    public TotalTable getQuestionBank(int id) {
        TotalTable totalTable= DataSupport.find(TotalTable.class,id);
        return totalTable;
    }

    public List<TotalTable> getAllQuestionBankList() {
        List<TotalTable> totalTables= DataSupport.findAll(TotalTable.class);
        return totalTables;
    }
}
