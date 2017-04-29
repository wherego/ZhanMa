package com.huruwo.zhanma.view.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.dbmodel.TotalTable;
import com.huruwo.zhanma.presenter.PresenterQuestionBank;
import com.huruwo.zhanma.util.DensityUtil;
import com.huruwo.zhanma.util.ScreenUtils;
import com.huruwo.zhanma.view.activity.QuestionsDetailActivity;
import com.huruwo.zhanma.view.adapter.QueListRecyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/4/5.
 */

public class QuestionsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,PopupWindow.OnDismissListener{


    Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private PresenterQuestionBank mPresenterQuestionBank;
    private QueListRecyAdapter mQueListRecyAdapter;
    private List<TotalTable> totalTables=new ArrayList<>();
    public boolean isEmpty=false;
    private PopupWindow popupWindow;


    public static QuestionsListFragment newInstance(int id) {
        QuestionsListFragment fragment = new QuestionsListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionlist, container, false);
        unbinder = ButterKnife.bind(this, view);
        mPresenterQuestionBank=new PresenterQuestionBank(this);
        mPresenterQuestionBank.loadQuestionBank();
        //设置刷新颜色
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setProgressViewEndTarget(false, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 80, Resources.getSystem().getDisplayMetrics()));
        mSwipeRefreshLayout.setOnRefreshListener(this);


        //Recy的适配器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mQueListRecyAdapter = new QueListRecyAdapter(getActivity(), totalTables);
        mRecyclerView.setAdapter(mQueListRecyAdapter);
        mQueListRecyAdapter.setOnItemClickLitener(new QueListRecyAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                QuestionsDetailActivity.navigation(getActivity(), totalTables.get(position));
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                openPopupWindow(view,position);
            }
        });
        mRecyclerView.setItemAnimator(null);
         return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        //数据改变 重新加载一次
        mPresenterQuestionBank.loadQuestionBank();
        mQueListRecyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        if (mQueListRecyAdapter.getData() != null) {
            // 假刷新
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mPresenterQuestionBank.loadQuestionBank();
                    mQueListRecyAdapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        } else {

        }
    }

    public void addQuestionBank(final List<TotalTable> totalTables) {
        this.totalTables.clear();
        this.totalTables.addAll(totalTables);
    }

    private void openPopupWindow(View v, int position) {
        //防止重复按按钮
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        //设置PopupWindow的View
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_popupwindow_advice, null);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.PopupWindow);
        //设置位置
        v.getHeight();
        int[] location= new int[2];
        v.getLocationInWindow(location);
        Context context=getContext();
        if (ScreenUtils.getScreenHeight(context) - ScreenUtils.getStatusHeight(context) - location[1] < DensityUtil.dip2px(context, 80)) {
            popupWindow.showAsDropDown(v, ScreenUtils.getScreenWidth(context) / 4 - (int) DensityUtil.dip2px(context, 40), -v.getHeight() - (int) DensityUtil.dip2px(context, 80));
        } else {
            popupWindow.showAsDropDown(v, ScreenUtils.getScreenWidth(context) / 4 - (int) DensityUtil.dip2px(context, 40), -160);
        }
        //设置消失监听
        popupWindow.setOnDismissListener(this);
        //设置PopupWindow的View点击事件
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    //设置屏幕背景透明效果

    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public void onDismiss() {
        setBackgroundAlpha(1);
    }
}
