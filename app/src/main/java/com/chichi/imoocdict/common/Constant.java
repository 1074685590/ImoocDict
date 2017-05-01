package com.chichi.imoocdict.common;

/**
 * Created by chichi on 2017/5/1.
 */

public class Constant {
  public static final String PATH_BASE = "http://fanyi.youdao.com/openapi.do?";
  public static final String PATH_QUERY =
      PATH_BASE + "keyfrom=AndroidVoiceDict&key=1652454009&type=data&doctype=json&version=1.1&q=";

  public static final String EXTRA_SOUND_START = "sound_start";
  public static final String EXTRA_SOUND_END = "sound_end";
  public static final String EXTRA_SOUND_SUCCESS = "sound_success";
  public static final String EXTRA_SOUND_ERROR = "sound_error";
  public static final String EXTRA_SOUND_CANCEL = "sound_cancel";
}
