package com.example.gdziu.handlerthreadbasics;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class MyHandlerThread extends HandlerThread implements Handler.Callback{

    protected Handler theHandlerUI;
    protected Handler theHandlerOnThread;

    public MyHandlerThread(Handler aHandler) {
        super("MyHandlerThread");
        theHandlerUI = aHandler;
    }

    @Override
    protected void onLooperPrepared() {

        theHandlerOnThread = new Handler(getLooper(), this);
    }

    public static final int MSG_EMPTY_ID = 100;

    public void calculate() {
        theHandlerOnThread.sendEmptyMessage(MSG_EMPTY_ID);
    }

    public static final int MSG_RESPONSE_ID = 101;

    @Override
    public boolean handleMessage(Message aMessage) {
        String astrResponse;

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (aMessage.what) {
            case MSG_EMPTY_ID:
                astrResponse="handleMessage:MSG_EMPTY_ID";
                break;
            default:
                return false;
        }
        if (null!=theHandlerUI){

            Message aMessageResponse=theHandlerUI.obtainMessage(MSG_RESPONSE_ID,astrResponse);
            theHandlerUI.sendMessage(aMessageResponse);
        }
        return true;
    }
}
