package com.huruwo.zhanma.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.TransformBean.TransForm;
import com.huruwo.zhanma.db.bmobmodel.BmTotalTable;
import com.huruwo.zhanma.presenter.PresenterAddQuestion;
import com.huruwo.zhanma.view.adapter.AddQueRecyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddQuestionActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;
    private PresenterAddQuestion mPresenterAddQuestion;
    private AddQueRecyAdapter mAddQueRecyAdapter;

    //activity启动函数
    public static void navigation(Activity activity) {
        Intent intent = new Intent(activity, AddQuestionActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterAddQuestion=new PresenterAddQuestion(this);
        mPresenterAddQuestion.loadTotalTable();
            setContentView(R.layout.activity_add_question);
            ButterKnife.bind(this);
    }

    public void loadData(List<BmTotalTable> totalTables){
        iniview(totalTables);
    }

    private void iniview(List<BmTotalTable> totalTables){
        toolbar.setTitle("导入题库");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAddQueRecyAdapter = new AddQueRecyAdapter(this, totalTables);
        mRecyclerview.setAdapter(mAddQueRecyAdapter);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mAddQueRecyAdapter.setOnItemClickLitener(new AddQueRecyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                //如何导入到本地 先加载到数据库
                List<BmTotalTable> bmTotalTables=mAddQueRecyAdapter.getData();
                TransForm.BmToLite(bmTotalTables.get(position)).save();
                showToast("添加成功");
                finish();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }


}


