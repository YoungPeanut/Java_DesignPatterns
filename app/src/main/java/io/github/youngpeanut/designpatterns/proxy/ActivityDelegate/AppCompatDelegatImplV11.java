package io.github.youngpeanut.designpatterns.proxy.ActivityDelegate;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatCallback;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by chenshao on 16/11/10.
 */
public class AppCompatDelegatImplV11 extends AppCompatDelegat {
    public static final String TAG = "AppCompatDelegatImplV9";

    public AppCompatDelegatImplV11(Context context, Window window, AppCompatCallback callback) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate impl by AppCompatDelegatImplV9");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy impl by AppCompatDelegatImplV9");

    }

    @Override
    public void setContentView(View v, ViewGroup.LayoutParams lp) {
        Log.d(TAG,"setContentView impl by AppCompatDelegatImplV9");

    }

    @Override
    public void addContentView(View v, ViewGroup.LayoutParams lp) {
        Log.d(TAG,"addContentView impl by AppCompatDelegatImplV9");

    }
}
