package com.huruwo.zhanma.presenter;

import android.util.Log;

import com.huruwo.zhanma.db.bmobmodel.BmTotalTable;
import com.huruwo.zhanma.model.ModelAddQuestion;
import com.huruwo.zhanma.view.activity.AddQuestionActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */

public class PresenterAddQuestion {

    private ModelAddQuestion mModelAddQuestion;
    private AddQuestionActivity mAddQuestionActivity;

    public PresenterAddQuestion(AddQuestionActivity mAddQuestionActivity) {
        this.mAddQuestionActivity = mAddQuestionActivity;
        this.mModelAddQuestion = new ModelAddQuestion();
    }

    public void loadTotalTable() {
        mModelAddQuestion.getTotalTable(new ModelAddQuestion.BmTotalTableListener() {
            @Override
            public void onSuccess(List<BmTotalTable> list) {
                mAddQuestionActivity.loadData(list);
                Log.e("sadas",list.size()+"");
            }

            @Override
            public void onFailed(String errString) {
                Log.e("sadas",errString);
            }
        });

    }
}
