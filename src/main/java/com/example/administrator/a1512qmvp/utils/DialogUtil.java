package com.example.administrator.a1512qmvp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Administrator on 2018/5/18.
 */

public class DialogUtil {

    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在加载...");
        progressDialog.setCancelable(false);
        return progressDialog;
    }

}
