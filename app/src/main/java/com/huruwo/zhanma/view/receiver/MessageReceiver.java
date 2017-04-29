package com.huruwo.zhanma.view.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.huruwo.zhanma.util.ExampleUtil;

import static com.huruwo.zhanma.view.activity.MainActivity.KEY_EXTRAS;
import static com.huruwo.zhanma.view.activity.MainActivity.KEY_MESSAGE;
import static com.huruwo.zhanma.view.activity.MainActivity.MESSAGE_RECEIVED_ACTION;

/**
 * Created by Administrator on 2017/4/29.
 */

public class MessageReceiver extends BroadcastReceiver {
    private Context context;


    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
            String messge = intent.getStringExtra(KEY_MESSAGE);
            String extras = intent.getStringExtra(KEY_EXTRAS);
            StringBuilder showMsg = new StringBuilder();
            showMsg.append( messge );
            if (!ExampleUtil.isEmpty(extras)) {
                showMsg.append( extras );
            }
            ShowDialog(showMsg.toString());
        }
    }

    private void setCostomMsg(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    private void ShowDialog(String msg) {
        String [] msgs=msg.split("\\|\\|");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(msgs, null);
        builder.setTitle("更新提示");
        builder.setPositiveButton("前往更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
                Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(intent2, "打开方式"));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("稍后提示", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}