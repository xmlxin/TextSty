package com.xiaoxin.jhang.wxspeak.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.xiaoxin.jhang.wxspeak.R;
import com.xiaoxin.jhang.wxspeak.service.SpeakService;

import static android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;

/**
 * Created by jinliangshan on 16/12/26.
 * http://blog.csdn.net/iromkoear/article/details/68936832
 */
public class TrackerWindowManager {
    private static Context mContext;
    private static WindowManager mWindowManager;
    private static View view;

    public TrackerWindowManager(Context context) {
        mContext = context;
        mWindowManager = (WindowManager)context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

    }

    private static View mFloatingView;
    private static  WindowManager.LayoutParams LAYOUT_PARAMS;

    public static void initWindowParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.x = 0;
        params.y = 0;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.LEFT | Gravity.TOP;
        params.type = TYPE_SYSTEM_ALERT; //TYPE_PHONE
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        LAYOUT_PARAMS = params;
    }

    public static void addView() {
//        initWindowParams();
//        if(mFloatingView == null){
//            mFloatingView = new FloatingView(mContext);
//            mFloatingView.setLayoutParams(LAYOUT_PARAMS);
//            mWindowManager.addView(mFloatingView, LAYOUT_PARAMS);
//        }
    }

    public static void removeView(){
        initWindowParams();
        if(mFloatingView != null){
            mWindowManager.removeView(mFloatingView);
            mFloatingView = null;
        }
    }

    public  void showWindow(final Context context) {
        final WindowManager mWindow = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams param = new WindowManager.LayoutParams();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_ball, null);
            ImageView button = (ImageView) view.findViewById(R.id.img_ball);
            int screenWidth = mWindow.getDefaultDisplay().getWidth();
            int screenHeight = mWindow.getDefaultDisplay().getHeight();
            param.x = screenWidth;
            param.y = screenHeight / 2;
            param.width = WindowManager.LayoutParams.WRAP_CONTENT;
            param.height = WindowManager.LayoutParams.WRAP_CONTENT;
            param.gravity = Gravity.LEFT | Gravity.TOP;
            param.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;//TYPE_SYSTEM_ALERT  TYPE_TOAST
            param.format = PixelFormat.RGBA_8888;
            param.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;//设备常亮
            view.setLayoutParams(param);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startService(
                            new Intent(context, SpeakService.class)
                                    .putExtra(SpeakService.TRANSFORM, true)
                    );
                }
            });
            mWindow.addView(view, param);
        }
    }

}
