package com.huruwo.zhanma.presenter;

import com.huruwo.zhanma.db.dbmodel.TotalTable;
import com.huruwo.zhanma.model.ModelQuestion;
import com.huruwo.zhanma.view.activity.QuestionActivity;

/**
 * Created by Administrator on 2017/4/14.
 */

public class PresenterQuestion   {

    private ModelQuestion mModelQuestion;
    private QuestionActivity mQuestionActivity;
    public PresenterQuestion(QuestionActivity mQuestionActivity) {
        this.mQuestionActivity = mQuestionActivity;
        this.mModelQuestion = new ModelQuestion();
    }


    public void loadQuestion(TotalTable totalTable) {
        mQuestionActivity.addQuestion(mModelQuestion.getQuestionListById(totalTable));
    }
}
