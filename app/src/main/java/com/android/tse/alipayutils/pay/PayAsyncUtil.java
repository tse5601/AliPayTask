package com.android.tse.alipayutils.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;

/**
 * Created by Tse on 2018/1/11.
 */

public class PayAsyncUtil extends AsyncTask<String, Void, String> {

    private final Activity ACTIVITY;
    private final IAliPayResultListener LISTENER;

    //订单支付成功
    private static final String PAY_STATUS_SUCCESS = "9000";
    //正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    private static final String PAY_STATUS_PAYING = "8000";
    //订单支付失败
    private static final String PAY_STATUS_FAIL = "4000";
    //用户中途取消
    private static final String PAY_STATUS_CANCEL = "6001";
    //网络连接出错
    private static final String PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncUtil(Activity activity, IAliPayResultListener listener) {
        this.ACTIVITY = activity;
        this.LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPayStatus = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        return payTask.pay(alPayStatus, true);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        final PayResult payResult = new PayResult(result);
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();

        switch (resultStatus) {
            case PAY_STATUS_SUCCESS:
                if (LISTENER != null) {
                    LISTENER.onPaySuccess();
                }
                break;
            case PAY_STATUS_FAIL:
                if (LISTENER != null) {
                    LISTENER.onPayFail();
                }
                break;
            case PAY_STATUS_PAYING:
                if (LISTENER != null) {
                    LISTENER.onPaying();
                }
                break;
            case PAY_STATUS_CANCEL:
                if (LISTENER != null) {
                    LISTENER.onPayCancel();
                }
                break;
            case PAY_STATUS_CONNECT_ERROR:
                if (LISTENER != null) {
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
