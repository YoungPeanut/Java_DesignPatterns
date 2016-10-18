package io.github.youngpeanut.designpatterns.proxy.android_aidl;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/**
 * 查询余额，只有传递一个int的id过来，银行就会将你的余额设置为id*10
 * Created by chenshao on 16/10/18.
 */
public interface IBank extends IInterface {
    String DESCRIPTOR = "io.github.youngpeanut.designpatterns.proxy.android.IBank";
    int TRANSACTION_queryMoney = IBinder.FIRST_CALL_TRANSACTION;//+0  ?

    long queryMoney(int uid) throws RemoteException;
}
