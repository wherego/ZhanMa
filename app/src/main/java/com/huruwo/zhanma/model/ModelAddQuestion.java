package com.huruwo.zhanma.model;

import com.huruwo.zhanma.db.bmobmodel.BmTotalTable;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/4/27.
 */

public class ModelAddQuestion {

    public interface BmTotalTableListener{
        void onSuccess(List<BmTotalTable> list);

        void onFailed(String errString);
    }


    public void getTotalTable(final BmTotalTableListener listener) {
        BmobQuery<BmTotalTable> query = new BmobQuery<BmTotalTable>();
        query.findObjects(new FindListener<BmTotalTable>() {
            @Override
            public void done(List<BmTotalTable> list, BmobException e) {
                if (list != null) {
                    //通过接口传出去
                    listener.onSuccess(list);
                } else {
                    listener.onFailed(e.getMessage());
                }
            }
        });
    }
}
