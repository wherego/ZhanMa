package com.huruwo.zhanma.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.huruwo.zhanma.db.dbmodel.TotalTable;

import org.litepal.tablemanager.Connector;

import java.io.File;

/**
 * Created by Administrator on 2017/4/14.
 */

public class DBManager {

    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "zhanma.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.huruwo.zhanma";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME + "/databases";  //在手机里存放数据库的位置

    public static int FromFile = 1;
    public static int FromCode = 2;
    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context, int type) {
        this.context = context;
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME, type);
    }

    public SQLiteDatabase openDatabase() {

        return this.database;
    }

    private SQLiteDatabase openDatabase(String dbfile, int type) {
        File dbFile = new File(dbfile);
        if (dbFile.exists()) {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbFile,
                    null);
            return db;
        } else {
            try {
                //从文件导入
                if (type == 1) {
                    // 不存在先创建文件夹
                    //File path = new File(DB_PATH);
                    //if (path.mkdir()) {
                    //    Log.i("DB_PATH", "创建成功");
                    //} else {
                     //   Log.i("DB_PATH", "创建失败");
                   // }
                    //InputStream is = this.context.getResources().openRawResource(
                   //         R.raw.zhanma); //欲导入的数据库
                  //  FileOutputStream fos = new FileOutputStream(dbfile);
                  //  byte[] buffer = new byte[BUFFER_SIZE];
                  //  int count = 0;
                  //  while ((count = is.read(buffer)) > 0) {
                   //     fos.write(buffer, 0, count);
                  //  }
                  //  fos.close();
                  //  is.close();
                }
                //从代码导入
                else if (type == 2) {
                    //数据库的创建
                    SQLiteDatabase db = Connector.getDatabase();
                    //数据库数据导入
                    TotalTable totalTable1 = new TotalTable("Android 重要面试题及答案", "安卓", 100, 80, 20);

                    TotalTable totalTable2 = new TotalTable("测试数据", "安卓", 100, 80, 20);
                    totalTable1.save();
                    totalTable2.save();
                }

                //再次调用本身 返回db
               return openDatabase(dbfile,1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
    //        catch (FileNotFoundException e) {
//                Log.e("Database", "File not found");
//                e.printStackTrace();
//            } catch (IOException e) {
//                Log.e("Database", "IO exception");
//                e.printStackTrace();
//            }


        }


        return null;
    }

    public void closeDatabase() {
        this.database.close();
    }

}
