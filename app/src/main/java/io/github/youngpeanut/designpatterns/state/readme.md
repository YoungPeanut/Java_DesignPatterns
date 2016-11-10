
https://zhuanlan.zhihu.com/p/23293088

「谁来定义状态的转换」。可以由状态的使用者（Person）实现，也可以由每个状态各自实现，各有利弊。

状态模式，状态模式，尼玛，状态机，
尼玛，声明周期就是状态模式啊。
这样看来，Activity／Fragment分分钟就是一个状态机啊
所以，看山不是山
Fragment的状态是由FragmentManager管理的。
```

    /**
     * Return a private FragmentManager for placing and managing Fragments
     * inside of this Fragment.
     */
    final public FragmentManager getChildFragmentManager() {
        if (mChildFragmentManager == null) {
            instantiateChildFragmentManager();
            if (mState >= RESUMED) {
                mChildFragmentManager.dispatchResume();
            } else if (mState >= STARTED) {
                mChildFragmentManager.dispatchStart();
            } else if (mState >= ACTIVITY_CREATED) {
                mChildFragmentManager.dispatchActivityCreated();
            } else if (mState >= CREATED) {
                mChildFragmentManager.dispatchCreate();
            }
        }
        return mChildFragmentManager;
    }

```

dispatchStart／dispatchResume／dispatchCreate，这，分分钟要找到声明周期的回调方法的节奏。
从dispatchResume跟进去看看，
moveToState －－ 走到FragmentManager#void moveToState(Fragment f, int newState, int transit, int transitionStyle,boolean keepActive)
 前方高能： switch (f.mState) {

                case Fragment.RESUMED:
                    if (newState < Fragment.RESUMED) {
                        if (DEBUG) Log.v(TAG, "movefrom RESUMED: " + f);
                        f.performPause();
                    }
 }
 f.performPause();／／把球踢回给Fragment
 onPause();
 明朗了。
