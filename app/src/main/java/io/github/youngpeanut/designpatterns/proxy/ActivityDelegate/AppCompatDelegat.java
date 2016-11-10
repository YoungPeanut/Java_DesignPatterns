package io.github.youngpeanut.designpatterns.proxy.ActivityDelegate;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * 示例代码，详见android.support.v7.app.AppCompatDelegate
 * Created by chenshao on 16/11/10.
 */
public abstract class AppCompatDelegat {


    /**
     * Private constructor
     */
    AppCompatDelegat() {}

    public abstract void onCreate(Bundle savedInstanceState);

    public abstract void onDestroy();

    public abstract void setContentView(View v, ViewGroup.LayoutParams lp);

    public abstract void addContentView(View v, ViewGroup.LayoutParams lp);


    private static AppCompatDelegat create(Context context, Window window,
                                            AppCompatCallback callback) {
        final int sdk = Build.VERSION.SDK_INT;
//        if (BuildCompat.isAtLeastN()) {
//            return new AppCompatDelegateImplN(context, window, callback);
//        } else if (sdk >= 23) {
//            return new AppCompatDelegateImplV23(context, window, callback);
//        } else if (sdk >= 14) {
//            return new AppCompatDelegateImplV14(context, window, callback);
//        } else
        if (sdk >= 11) {
            return new AppCompatDelegatImplV11(context, window, callback);
        } else {
            return new AppCompatDelegatImplV9(context, window, callback);
        }
    }


}
