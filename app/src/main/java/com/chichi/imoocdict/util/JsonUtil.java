package com.chichi.imoocdict.util;

import com.chichi.imoocdict.bean.YoudaoResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chichi on 2017/5/1.
 */

public class JsonUtil {

  public static YoudaoResponse parseJSON(String response) {
    Gson gson = new GsonBuilder().create();
    YoudaoResponse youdaoResponse = gson.fromJson(response, YoudaoResponse.class);
    return youdaoResponse;
  }
}
