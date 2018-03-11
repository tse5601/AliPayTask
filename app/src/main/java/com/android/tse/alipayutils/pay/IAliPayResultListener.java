package com.android.tse.alipayutils.pay;

/**
 * Created by Tse on 2018/1/11.
 */

public interface IAliPayResultListener {
    void onPaySuccess();

    void onPaying();

    void onPayFail();

    void onPayCancel();

    void onPayConnectError();
}
