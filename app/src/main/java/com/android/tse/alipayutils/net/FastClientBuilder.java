package com.android.tse.alipayutils.net;

import android.content.Context;

import java.io.File;
import java.util.WeakHashMap;

/**
 * Created by Tse on 2018/1/11.
 */

public class FastClientBuilder {


    private static final WeakHashMap<String, Object> PARAMS = getParams();
    private String mUrl = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    FastClientBuilder() {
    }

    public final FastClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final FastClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final FastClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final FastClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final FastClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final FastClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final FastClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final FastClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final FastBuildClient build() {
        return new FastBuildClient(mUrl,
                mDownloadDir, mExtension, mName,
                mFile, mContext);
    }
}
