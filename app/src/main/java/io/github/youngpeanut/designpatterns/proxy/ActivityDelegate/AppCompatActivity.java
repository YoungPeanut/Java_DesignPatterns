package io.github.youngpeanut.designpatterns.proxy.ActivityDelegate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by chenshao on 16/11/10.
 */
public class AppCompatActivity  {
    private AppCompatDelegat mDelegate;


//    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final AppCompatDelegat delegate = getDelegate();
        delegate.onCreate(savedInstanceState);

    }





    /**
     * @return The {@link AppCompatDelegat} being used by this Activity.
     */
    @NonNull
    public AppCompatDelegat getDelegate() {
        if (mDelegate == null) {
//            mDelegate = AppCompatDelegat.create(this, this);
        }
        return mDelegate;
    }

}
