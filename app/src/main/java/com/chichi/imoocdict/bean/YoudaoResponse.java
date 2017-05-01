package com.chichi.imoocdict.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by chichi on 2017/5/1.
 */

public class YoudaoResponse {

  public List<String> translation;

  public Basic basic;

  public String query;

  public int errorCode;

  @SerializedName("web") public List<Web> webList;
}
