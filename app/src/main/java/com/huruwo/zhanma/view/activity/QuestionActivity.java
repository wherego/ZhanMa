package com.huruwo.zhanma.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.dbmodel.ItemTable;
import com.huruwo.zhanma.db.dbmodel.TotalTable;
import com.huruwo.zhanma.presenter.PresenterQuestion;
import com.huruwo.zhanma.util.TimeUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity   {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_question_title)
    TextView mTvQuestionTitle;
    @BindView(R.id.button_delete_question)
    Button mButtonDeleteQuestion;
    @BindView(R.id.button_error_question)
    Button mButtonErrorQuestion;
    @BindView(R.id.ll_question)
    LinearLayout llQuestion;
    @BindView(R.id.ll_shortanswer)
    LinearLayout llShortanswer;
    @BindView(R.id.tv_shortanswer)
    TextView tvShortanswer;
    @BindView(R.id.rl_answer)
    RelativeLayout rlAnswer;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private PresenterQuestion mPresenterQuestion;
    private TotalTable totalTable;
    private List<ItemTable> itemTables;
    private int num = 0;
    private int recLen = 0;

    //activity启动函数
    public static void navigation(Activity activity, TotalTable totalTable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("totalTable", totalTable);
        Intent intent = new Intent(activity, QuestionActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }
    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        iniview();
    }

    private void iniview() {
        //ToolBar设置
        mToolbar.setTitle("Android基础练习");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        totalTable = (TotalTable) bundle.getParcelable("totalTable");

        mPresenterQuestion = new PresenterQuestion(this);
        mPresenterQuestion.loadQuestion(totalTable);
        mHandler.postDelayed(runnable,1000);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            recLen++;
            tvTime.setText(TimeUtil.SecondsToTime(recLen));
            tvTime.postDelayed(this, 1000);
        }
    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击back键finish当前activity
        switch (item.getItemId()) {
            case android.R.id.home:
                setTotalTable();
//                QuestionsResultActivity.navigation(this);
                finish();
                break;
        }
        return true;
    }


    @OnClick({R.id.button_delete_question, R.id.button_error_question, R.id.rl_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_delete_question:
//                QuestionsResultActivity.navigation(this);
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                finish();
                setEasyQuestion();

                break;
            case R.id.button_error_question:
                setErrorQuestion();
                break;
            case R.id.rl_answer:

                    llShortanswer.setVisibility(View.VISIBLE);
                     rlAnswer.setVisibility(View.GONE);
                break;
        }
    }


    public void addQuestion(List<ItemTable> itemTables) {
        this.itemTables = itemTables;
        setQuestion();
    }

    private void setQuestion() {
        if (num < itemTables.size()) {
            if(totalTable.getId()==2) {
            }
            else {
            }

            num = num + 1;
        } else {
            Toast.makeText(getBaseContext(), "已经是最后一题了", Toast.LENGTH_SHORT).show();
            setTotalTable();
        }
    }

    private void setEasyQuestion() {
        rlAnswer.setVisibility(View.VISIBLE);
        llShortanswer.setVisibility(View.GONE);
        ContentValues values = new ContentValues();
        values.put("issimple", true);
        DataSupport.update(ItemTable.class, values, num + 1);
        setQuestion();
    }

    private void setErrorQuestion() {
        rlAnswer.setVisibility(View.VISIBLE);
        llShortanswer.setVisibility(View.GONE);
        ContentValues values = new ContentValues();
        values.put("iserror", true);
        DataSupport.update(ItemTable.class, values, num + 1);
        setQuestion();
    }

    //退出前设置状态
    private void setTotalTable() {
        //总题数
        int count_num = itemTables.size();
        //完成数量和错题数量
        int count_finish = 0;
        int count_error = 0;
        for (ItemTable itemTable : itemTables) {

        }

        ContentValues values = new ContentValues();
        values.put("finish_num", count_finish);
        values.put("error_num", count_error);
        values.put("count_num", count_num);
        Log.e("xxx", DataSupport.update(TotalTable.class, values, totalTable.getId()) + "");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setTotalTable();
//            QuestionsResultActivity.navigation(this);
            finish();
        }

        return false;
    }

}
