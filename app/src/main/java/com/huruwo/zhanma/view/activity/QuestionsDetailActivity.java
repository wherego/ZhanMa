package com.huruwo.zhanma.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.dbmodel.TotalTable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class QuestionsDetailActivity extends BaseActivity {


    @BindView(R.id.button)
    Button button;
    @BindView(R.id.piechart)
    PieChartView pieChartView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private CollapsingToolbarLayoutState state;

    private TotalTable totalTable;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    //activity启动函数
    public static void navigation(Activity activity, TotalTable totalTable) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("totalTable", totalTable);
        Intent intent = new Intent(activity, QuestionsDetailActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_detail);
        ButterKnife.bind(this);

        iniView();
        iniData();

    }


    private void iniView() {

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        totalTable = (TotalTable) bundle.getParcelable("totalTable");


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //监听AppBarLayout 变化
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                        collapsingToolbarLayout.setTitle("");//设置title不显示
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        collapsingToolbarLayout.setTitle(totalTable.getTable_name());//设置title为EXPANDED
//                        playButton.setVisibility(View.VISIBLE);//隐藏播放按钮
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {
//                            playButton.setVisibility(View.GONE);//由折叠变为中间状态时隐藏播放按钮
                        }
                        collapsingToolbarLayout.setTitle(totalTable.getTable_name());//设置title为INTERNEDIATE
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                }
            }
        });

    }

    private void iniData() {
        setPieChart();
    }

    private void setPieChart() {
        //饼模块集合
        List values = new ArrayList<>();
        //添加饼模块（数值，模块颜色）
        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.


        SliceValue sliceValueCount = new SliceValue(totalTable.getCount_num() - totalTable.getFinish_num(), getResources().getColor(R.color.grey_400));
        float ratio_unfinish = (float) (totalTable.getCount_num() - totalTable.getFinish_num()) / (float) totalTable.getCount_num() * 100;
        sliceValueCount.setLabel("未完成" + decimalFormat.format(ratio_unfinish) + "%");
        values.add(sliceValueCount);
        SliceValue sliceValueFinish = new SliceValue(totalTable.getFinish_num(), getResources().getColor(R.color.cyan_400));
        float ratio_finish = (float) totalTable.getFinish_num() / (float) totalTable.getCount_num() * 100;
        sliceValueFinish.setLabel("完成" + decimalFormat.format(ratio_finish) + "%");
        values.add(sliceValueFinish);

//        SliceValue sliceValueError=new SliceValue(totalTable.getCount_num()-totalTable.getFinish_num(), getResources().getColor(R.color.red_500));
        float ratio_error = (float) (totalTable.getFinish_num() - totalTable.getError_num()) / (float) totalTable.getFinish_num() * 100;
//        sliceValueError.setLabel("错误"+decimalFormat.format(ratio_error)+"%");
//        values.add(sliceValueError);


        PieChartData pieChartData = new PieChartData(values);


        /*****************************饼中文字设置************************************/
        //是否显示文本内容(默认为false)
        pieChartData.setHasLabels(true);
        //是否点击饼模块才显示文本（默认为false,为true时，setHasLabels(true)无效）
        //	pieChartData.setHasLabelsOnlyForSelected(true);
        //文本内容是否显示在饼图外侧(默认为false)
        pieChartData.setHasLabelsOutside(false);
        //文本字体大小
        pieChartData.setValueLabelTextSize(12);
        //文本文字颜色
        pieChartData.setValueLabelsTextColor(Color.WHITE);
        //设置文本背景颜色
        pieChartData.setValueLabelBackgroundColor(Color.RED);
        //设置文本背景颜色时，必须设置自动背景为false
        pieChartData.setValueLabelBackgroundAuto(false);
        //设置是否有文字背景
        pieChartData.setValueLabelBackgroundEnabled(false);
        /*****************************中心圆设置************************************/
        //饼图是空心圆环还是实心饼状（默认false,饼状）
        pieChartData.setHasCenterCircle(true);
        //中心圆的颜色（需setHasCenterCircle(true)，因为只有圆环才能看到中心圆）
        pieChartData.setCenterCircleColor(Color.WHITE);
        //中心圆所占饼图比例（0-1）
        pieChartData.setCenterCircleScale(0.6f);
        /*=====================中心圆文本（可以只设置一个文本）==========/
        /*--------------------第1个文本----------------------*/
        //中心圆中文本
        pieChartData.setCenterText1("正确率" + ratio_error + "%");
        //中心圆的文本颜色
        pieChartData.setCenterText1Color(Color.GRAY);
        //中心圆的文本大小
        pieChartData.setCenterText1FontSize(16);
       /*--------------------第2个文本----------------------*/
        //中心圆中文本
        if (ratio_error >= 80) {
            pieChartData.setCenterText2("很厉害");
        } else if (ratio_error >= 70) {
            pieChartData.setCenterText2("还不错");
        } else if (ratio_error >= 60) {
            pieChartData.setCenterText2("一般般");
        } else if (ratio_error < 60)
        {
            pieChartData.setCenterText2("有待加强");
        }
            //中心圆的文本颜色
            pieChartData.setCenterText2Color(Color.RED);
        //中心圆的文本大小
        pieChartData.setCenterText2FontSize(20);
        //饼图各模块的间隔(默认为0)
        pieChartData.setSlicesSpacing(3);
        pieChartView.setPieChartData(pieChartData);
        //整个饼图所占视图比例（0-1）
        pieChartView.setCircleFillRatio(1f);
        //饼图是否可以转动（默认为true）
        pieChartView.setChartRotationEnabled(false);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        QuestionActivity.navigation(this, totalTable);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击back键finish当前activity
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
