package com.chichi.imoocdict.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by chichi on 2017/5/1.
 */

public class Basic {

  @SerializedName("us_phonetic") String usPhonetic;

  String phonetic;

  @SerializedName("uk_phonetic") String ukPhonetic;

  List<String> explains;

  public String getUsPhonetic() {
    return usPhonetic;
  }

  public String getPhonetic() {
    return phonetic;
  }

  public String getUkPhonetic() {
    return ukPhonetic;
  }

  public List<String> getExplains() {
    return explains;
  }
}
