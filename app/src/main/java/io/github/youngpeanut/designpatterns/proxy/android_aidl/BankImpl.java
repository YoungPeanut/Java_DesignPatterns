package io.github.youngpeanut.designpatterns.proxy.android_aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/**
 * 因为客户端无法直接通过这个打包的Binder和服务端通信，因此客户端必须借助Proxy类来和服务端通信，
 * 这里Proxy的作用就是代理的作用，客户端所有的请求全部通过Proxy来代理，具体工作流程为：
 *
 * 1 Proxy接收到客户端的请求后，会将客户端的请求参数打包到Parcel对象中，//data.writeInt(uid);
 * 2 然后将Parcel对象通过它内部持有的Ibinder对象传送到服务端，//mRemote.transact(TRANSACTION_queryMoney, data, reply, 0);
 * 3 服务端接收数据、执行方法后返回结果给客户端的Proxy，//onTransact + reply.writeLong(result); + reply.readLong();
 * 4 Proxy解析数据后返回给客户端的真正调用者。
 *
 * 很显然，上述所分析的就是典型的代理模式。至于Binder如何传输数据，这涉及到很底层的知识，这个很难搞懂，但是数据传输的核心思想是共享内存。
 *
 * Created by chenshao on 16/10/18.
 */
public class BankImpl extends Binder implements IBank {

    public BankImpl() {
        this.attachInterface(this,DESCRIPTOR);
    }

    @Override
    public long queryMoney(int uid) throws RemoteException {
        return uid * 10;
    }

    /**
     *  ???
     * @param obj
     * @return
     */
    public static IBank asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IBank))) {
            return ((IBank) iin);
        }
        return new BankImpl.Proxy(obj);
    }

    /**
     * from : IBinder
     * @return
     */
    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        switch (code){
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case TRANSACTION_queryMoney:
                data.enforceInterface(DESCRIPTOR);
                int uid = data.readInt();
                long result = this.queryMoney(uid);//...
                reply.writeNoException();
                reply.writeLong(result);
                return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IBank{
        private IBinder mRemote;

        /**
         *
         * @param remote 这个参数实际上就是服务端Service中的onBind方法返回的Binder对象在客户端重新打包后的结果，
         *
         */
        Proxy(IBinder remote) {
            mRemote = remote;
        }

        @Override
        public long queryMoney(int uid) throws RemoteException {
            Parcel data = Parcel.obtain();
            Parcel reply = Parcel.obtain();
            long result;
            try {
                data.writeInterfaceToken(DESCRIPTOR);
                data.writeInt(uid);
                mRemote.transact(TRANSACTION_queryMoney, data, reply, 0);
                reply.readException();
                result = reply.readLong();
            } finally {
                reply.recycle();
                data.recycle();
            }
            return result;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }
        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }
    }
}
