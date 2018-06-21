package jianqiang.com.hostapp;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MyApplication extends Application {
    private static final String apkName = "plugin1.apk";
    private static final String dexName = "plugin1.dex";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        Utils.extractAssets(newBase, apkName);
        
        File dexFile = getFileStreamPath(apkName);
        File optDexFile = getFileStreamPath(dexName);

        try {
            BaseDexClassLoaderHookHelper.patchClassLoader(getClassLoader(), dexFile, optDexFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
