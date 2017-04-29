package com.huruwo.zhanma.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.dbmodel.ItemTable;
import com.huruwo.zhanma.presenter.PresentError;
import com.huruwo.zhanma.view.adapter.ErrorRecyAdapter;
import com.huruwo.zhanma.view.widget.RecyDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.litepal.LitePalApplication.getContext;

public class ErrorActivity extends BaseActivity  {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ErrorRecyAdapter mErrorRecyAdapter;
    private PresentError mPresentError;
    private List<ItemTable> itemTables;
    //activity启动函数
    public static void navigation(Activity activity) {
        Intent intent = new Intent(activity, ErrorActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ButterKnife.bind(this);

        iniview();

    }

    private void iniview(){
        toolbar.setTitle("错题集");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresentError=new PresentError(this);
        mPresentError.loadError();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecyDecoration(this,
                RecyDecoration.VERTICAL_LIST));
        mErrorRecyAdapter=new ErrorRecyAdapter(this,itemTables);
        recyclerView.setAdapter(mErrorRecyAdapter);
        mErrorRecyAdapter.setOnItemClickLitener(new ErrorRecyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                QuestionsDetailActivity.navigation(getActivity(),totalTables.get(position));
//                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(getActivity(),"长安",Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setItemAnimator(null);
    }

    public void addErrorQuestion(List<ItemTable> itemTables) {
        this.itemTables=itemTables;
    }
}
