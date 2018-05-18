package com.example.administrator.a1512qmvp.app;

import android.app.Application;

import com.dash.zxinglibrary.activity.ZXingLibrary;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2018/5/11.
 */

public class Myapp extends Application {
    {
        PlatformConfig.setWeixin("wx396ea2b17e2f8938", "e21c38fb0064a9631b05957f6bec73bd");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
}
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
    }
}
