package com.huruwo.zhanma.db.TransformBean;

import com.huruwo.zhanma.db.bmobmodel.BmTotalTable;
import com.huruwo.zhanma.db.dbmodel.TotalTable;

/**
 * Created by Administrator on 2017/4/27.
 */

public class TransForm {

    public  static  TotalTable  BmToLite(BmTotalTable bmTotalTable){

        TotalTable totalTable=new TotalTable(bmTotalTable.getTable_name(),bmTotalTable.getTags(),bmTotalTable.getCount_num(),bmTotalTable.getFinish_num(),bmTotalTable.getError_num());


        return totalTable;

    }



}
