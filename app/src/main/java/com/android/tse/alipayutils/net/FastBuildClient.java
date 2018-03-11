package com.android.tse.alipayutils.net;

import android.content.Context;

import java.io.File;
import java.util.Map;

/**
 * Created by Tse on 2018/1/11.
 */

public final class FastBuildClient {

    private final String URL;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final File FILE;
    private final Context CONTEXT ;

    FastBuildClient(String url,
                    Map<String, Object> params,
                    String downloadDir,
                    String extension,
                    String name,
                    File file,
                    Context context) {
        this.URL = url;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.FILE = file;
        this.CONTEXT = context;
    }

    public static FastClientBuilder builder() {
        return new FastClientBuilder();
    }


}
