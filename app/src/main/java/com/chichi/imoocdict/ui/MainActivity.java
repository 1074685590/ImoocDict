package com.chichi.imoocdict.ui;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chichi.imoocdict.R;
import com.chichi.imoocdict.bean.YoudaoResponse;
import com.chichi.imoocdict.common.Constant;
import com.chichi.imoocdict.util.HttpUtil;
import com.chichi.imoocdict.util.JsonUtil;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private final String TAG = getClass().getCanonicalName();
  private TextView mTvResult;
  private ImageView mIvVoice;
  private EditText mEdtKeyWord;
  private TextView mTvQuery;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    mTvResult = (TextView) findViewById(R.id.tv_result);
    mIvVoice = (ImageView) findViewById(R.id.iv_voice);
    mEdtKeyWord = (EditText) findViewById(R.id.edt_key_word);
    mTvQuery = (TextView) findViewById(R.id.tv_query);

    mTvQuery.setOnClickListener(this);
    mIvVoice.setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.tv_query) {
      queryContent();
    } else if (id == R.id.iv_voice) {
      start();
    }
  }

  private void start() {
    Intent intent = new Intent();
    bindParams(intent);//必须要自己设置音效，否则会报错 NullPointerException
    intent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
    startActivityForResult(intent, 1);
    mEdtKeyWord.setText("");
  }

  private void bindParams(Intent intent) {
    intent.putExtra(Constant.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
    intent.putExtra(Constant.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
    intent.putExtra(Constant.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
    intent.putExtra(Constant.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
    intent.putExtra(Constant.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
  }

  private void queryContent() {
    String content = mEdtKeyWord.getText().toString();
    try {
      HttpUtil.request(Constant.PATH_QUERY + content, new Callback() {
        @Override public void onFailure(Call call, IOException e) {
          Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
        }

        @Override public void onResponse(Call call, Response response) throws IOException {
          if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
          }

          String responseData = response.body().string();

          final YoudaoResponse youdaoResponse = JsonUtil.parseJSON(responseData);

          runOnUiThread(new Runnable() {
            @Override public void run() {
              mTvResult.setText(youdaoResponse.translation.get(0));
            }
          });
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      Bundle results = data.getExtras();
      ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
      mEdtKeyWord.setText(nbest.get(0));
    }
  }
}
