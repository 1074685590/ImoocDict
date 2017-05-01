package com.chichi.imoocdict.util;

import android.util.Log;
import java.io.IOException;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by chichi on 2017/5/1.
 */

public class HttpUtil {

  private static final String TAG = "HttpUtil";
  private static OkHttpClient client = new OkHttpClient();

  public static void request(String url, Callback callback) throws IOException {
    Log.e(TAG, url);
    Request request = new Request.Builder().url(url).build();
    client.newCall(request).enqueue(callback);
  }
}
