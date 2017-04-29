package com.huruwo.zhanma.presenter;

import android.util.Log;

import com.huruwo.zhanma.model.ModelQuestionBank;
import com.huruwo.zhanma.view.fragment.QuestionsListFragment;

/**
 * Created by Administrator on 2017/4/14.
 */

public class PresenterQuestionBank  {

    private ModelQuestionBank mModelQuestionBank;
    private QuestionsListFragment mQuestionsListFragment;
    public PresenterQuestionBank(QuestionsListFragment mQuestionsListFragment) {
        this.mQuestionsListFragment = mQuestionsListFragment;
        this.mModelQuestionBank = new ModelQuestionBank();
    }



    public void loadQuestionBank() {
        if(mModelQuestionBank.getAllQuestionBankList()!=null&&mModelQuestionBank.getAllQuestionBankList().size()>0) {
            mQuestionsListFragment.addQuestionBank(mModelQuestionBank.getAllQuestionBankList());
        }
        else
            mQuestionsListFragment.isEmpty=true;
    }
}
