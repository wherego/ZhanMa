package com.huruwo.zhanma.presenter;

import com.huruwo.zhanma.model.ModelError;
import com.huruwo.zhanma.view.activity.ErrorActivity;

/**
 * Created by Administrator on 2017/4/16.
 */

public class PresentError   {
    private ModelError modelError;
    private ErrorActivity mErrorActivity;


    public PresentError(ErrorActivity mErrorActivity) {
        this.mErrorActivity = mErrorActivity;
        this.modelError = new ModelError();
    }

    public void loadError() {
        mErrorActivity.addErrorQuestion(modelError.getErrorQuestionList());
    }
}
