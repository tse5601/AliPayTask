package com.android.tse.alipayutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import butterknife.BindView;
import butterknife.OnClick;

import com.android.tse.alipayutils.net.FastBuildClient;
import com.android.tse.alipayutils.net.callback.ISuccess;
import com.android.tse.alipayutils.pay.AliPayBuilder;
import com.android.tse.alipayutils.pay.IAliPayResultListener;

import java.util.WeakHashMap;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements IAliPayResultListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R2.id.btn_ali_pay)
    void onClickPay() {
        createAliPay();
    }

    private void createAliPay() {
        final String orderUrl = "订单API";
        final WeakHashMap<String, Object> orderParams = new WeakHashMap<>();
        //加入你的参数
        FastBuildClient.builder()
                .url(orderUrl)
                .loader(getContext())
                .params(orderParams)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        //进行具体的支付
                        final int orderId = JSON.parseObject(response).getInteger("result");
                        AliPayBuilder.create(MainActivity.this)
                                .setPayResultListener(MainActivity.this)
                                .setAliPayOrder(orderId);
                    }
                })
                .build()
                .post();
    }

    @Override
    public void onPaySuccess() {

    }

    @Override
    public void onPaying() {

    }

    @Override
    public void onPayFail() {

    }

    @Override
    public void onPayCancel() {

    }

    @Override
    public void onPayConnectError() {

    }
}
