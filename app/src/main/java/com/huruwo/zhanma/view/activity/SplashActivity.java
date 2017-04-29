package com.huruwo.zhanma.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.huruwo.zhanma.R;
import com.huruwo.zhanma.db.bmobmodel.BmItemTable;
import com.huruwo.zhanma.db.bmobmodel.BmTotalTable;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/4/3.
 */

public class SplashActivity extends BaseActivity {


    @BindView(R.id.splash_bg_img)
    KenBurnsView splashBgImg;
    @BindView(R.id.splash_root_layout)
    RelativeLayout splashRootLayout;
    @BindView(R.id.tv_appname)
    TextView tvAppname;
    @BindView(R.id.tv_appname_phonetic)
    TextView tvAppnamePhonetic;
    @BindView(R.id.im_logo)
    ImageView imLogo;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //初始操作
        iniview();
        inidata();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(4000, new LinearInterpolator());
        splashBgImg.setTransitionGenerator(generator);
        setAnimation();
//        splashBgImg.setTransitionListener(new KenBurnsView.TransitionListener() {
//            @Override
//            public void onTransitionStart(Transition transition) {
//            }
//
//            @Override
//            public void onTransitionEnd(Transition transition) {
//
//                //动画结束后跳转
//                jumpToMain();
//            }
//        });


    }

    private void inidata() {

        //创建数据库
       //DBManager dbManager = new DBManager(this, 2);


    }


    private void iniview() {
        //沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
    }

    //设置动画
    private void setAnimation() {
        //添加addOnLayoutChangeListener是为了能够精确测量VIew的大小
        splashRootLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                splashRootLayout.removeOnLayoutChangeListener(this);

                final int transX_tv = tvAppname.getMeasuredWidth() >> 1;
                final int transY_tv = tvAppname.getMeasuredHeight();

                final int transX_img = imLogo.getMeasuredWidth() >> 1;
                final int transY_img = imLogo.getMeasuredHeight();

                tvAppname.animate()
                        .translationY(-transX_tv / 4)
                        .translationX(transX_tv)
                        .setDuration(500)
                        .setStartDelay(500)
                        .setInterpolator(new LinearInterpolator());

                tvAppnamePhonetic.setAlpha(0);
                tvAppnamePhonetic.animate()
                        .alpha(1)
                        .translationY(transY_tv / 4)
                        .translationX(transX_tv)
                        .setDuration(500)
                        .setStartDelay(500)
                        .setInterpolator(new LinearInterpolator())
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                imLogo.animate()
                                        .rotation(-90)
                                        .translationX(-transX_img)
                                        .setDuration(500)
                                        .setStartDelay(500)
                                        .setInterpolator(new LinearInterpolator()).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        jumpToMain();
                                    }
                                });
                            }
                        });
            }

        });

        mHandler = new Handler();

    }
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
        splashBgImg.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        splashBgImg.pause();
    }

    private void jumpToMain() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动函数
                MainActivity.navigation(SplashActivity.this);
            }
        }, 300);


    }

    /**
     * 插入对象
     */
    private void InsertData() {

        final BmTotalTable bmTotalTable = new BmTotalTable();
        bmTotalTable.setObjectId("e175c8e116");
                    BmItemTable businessAdvice = new BmItemTable();
                    businessAdvice.setContent("阿德伍德沃德驱动器");
                    businessAdvice.setTitle("打算的大");
                    businessAdvice.setState(0);
                    businessAdvice.setBmTotalTable(bmTotalTable);
                    businessAdvice.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                showToast("添加成功");
                                finish();
                            } else {
                                showToast(e.getMessage());
                            }
                        }
                    });
    }

}
