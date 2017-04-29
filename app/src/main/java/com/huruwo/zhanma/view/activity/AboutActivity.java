package com.huruwo.zhanma.view.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.huruwo.zhanma.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.about_header_img)
    ImageView aboutHeaderImg;
    @BindView(R.id.version_name_text)
    TextView versionNameText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.nest_scroll_view)
    NestedScrollView nestScrollView;
    @BindView(R.id.tv_github)
    TextView tvGithub;
    @BindView(R.id.tv_qq)
    TextView tvQq;
    @BindView(R.id.tv_email)
    TextView tvEmail;

    //activity启动函数
    public static void navigation(Activity activity) {
        Intent intent = new Intent(activity, AboutActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        toolbar.setTitle("关于");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
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

    @OnClick({R.id.tv_github, R.id.tv_qq, R.id.tv_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_github:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse(getString(R.string.github)));
                startActivity(Intent.createChooser(intent, "请选择浏览器"));
                break;
            case R.id.tv_qq:
                ClipboardManager cmbqq = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipDataqq = ClipData.newPlainText("content", getString(R.string.QQ));
                cmbqq.setPrimaryClip(clipDataqq);
                showToast("QQ号:"+getString(R.string.QQ)+"已复制到粘贴板");
                break;
            case R.id.tv_email:
                ClipboardManager cmbemali = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipDataemail = ClipData.newPlainText("content", getString(R.string.e_mail));
                cmbemali.setPrimaryClip(clipDataemail);
                showToast("QQEmail:"+getString(R.string.e_mail)+"已复制到粘贴板");
                break;
        }
    }
}
