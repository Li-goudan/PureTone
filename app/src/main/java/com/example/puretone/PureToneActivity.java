package com.example.puretone;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PureToneActivity extends Activity implements View.OnClickListener {
    private PlayThread mPlayThread;

    Switch btnPlay;
    Switch btnLeft;
    Switch btnRight;
    Button btnStop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (Switch) findViewById(R.id.switch1);
        btnLeft = (Switch) findViewById(R.id.switch2);
        btnRight = (Switch) findViewById(R.id.switch3);
    //    btnStop = (Button) findViewById(R.id.btn_stop);
        btnPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    playSound(true,true);
                }else {
                    if (null != mPlayThread) {
                        mPlayThread.stopPlay();
                        mPlayThread = null;
                    }
                }
            }
        });
        btnLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    playSound(true,false);
                }else {
                    if (null != mPlayThread) {
                        mPlayThread.stopPlay();
                        mPlayThread = null;
                    }
                }
            }
        });
        btnRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    playSound(false,true);
                }else {
                    if (null != mPlayThread) {
                        mPlayThread.stopPlay();
                        mPlayThread = null;
                    }
                }
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            if (null != mPlayThread) {
                mPlayThread.stopPlay();
                mPlayThread = null;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        /*switch (view.getId()) {
            case R.id.btn_play:
                playSound(true,true);
                break;
            case R.id.btn_left:
                playSound(true,false);
                break;
            case R.id.btn_right:
                playSound(false,true);
                break;
            case R.id.btn_stop:
                if (null != mPlayThread) {
                    mPlayThread.stopPlay();
                    mPlayThread = null;
                }
                break;
        }*/
    }

    private void playSound(boolean left, boolean right) {
        if (null != mPlayThread) {
            mPlayThread.stopPlay();
            mPlayThread = null;
        }
        mPlayThread = new PlayThread(400);
        mPlayThread.setChannel(left,right);
        mPlayThread.start();
    }


}