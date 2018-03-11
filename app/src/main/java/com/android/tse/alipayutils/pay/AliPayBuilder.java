package com.android.tse.alipayutils.pay;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.android.tse.alipayutils.net.FastBuildClient;
import com.android.tse.alipayutils.net.callback.ISuccess;

/**
 * Created by Tse on 2018/1/11.
 */

public class AliPayBuilder {


    //设置支付回调监听
    private IAliPayResultListener mIAlPayResultListener = null;
    private Activity mActivity = null;

    private AlertDialog mDialog = null;
    private int mOrderID = -1;

    private AliPayBuilder(Activity activity) {
        this.mActivity = activity;
        this.mDialog = new AlertDialog.Builder(activity.getApplicationContext()).create();
    }

    public static AliPayBuilder create(Activity activity) {
        return new AliPayBuilder(activity);
    }

    public void beginPaNotification() {
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(params);
        }
    }

    public AliPayBuilder setPayResultListener(IAlPayResultListener listener) {
        this.mIAlPayResultListener = listener;
        return this;
    }

    public AliPayBuilder setAliPayOrder(int orderId) {
        this.mOrderID = orderId;
        return this;
    }

    private void alPay(int orderId) {
        final String singUrl = "你的服务端支付地址" + orderId;
        //获取签名字符串
        FastBuildClient.builder()
                .url(singUrl)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String paySign = JSON.parseObject(response).getString("result");
                        //必须是异步的调用客户端支付接口
                        final PayAsyncUtil payAsyncTask = new PayAsyncUtil(mActivity, mIAlPayResultListener);
                        payAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paySign);
                    }
                })
                .build()
                .post();
    }

}
