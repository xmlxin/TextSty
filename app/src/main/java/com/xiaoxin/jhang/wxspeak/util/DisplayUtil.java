package com.xiaoxin.jhang.wxspeak.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class DisplayUtil {

        /**
         * 将px值转换为dip或dp值，保证尺寸大小不变 
         *
         * @para scale
         *            （DisplayMetrics类中属???density?? 
         * @return
         */
        public static int px2dp(Context context, float pxValue) {
            return (int) (pxValue / context.getResources().getDisplayMetrics().density + 0.5f);
        }

        /** 
         * 将dip或dp值转换为px值，保证尺寸大小不变 
         *  
         * @param dipValue 
         *            （DisplayMetrics类中属???density??
         * @return 
         */  
        public static int dp2px(Context context, float dipValue) {
            return (int) (dipValue * context.getResources().getDisplayMetrics().density + 0.5f);
        }  
      
        /** 
         * 将px值转换为sp值，保证文字大小不变 
         *  
         * @param pxValue 
         *            （DisplayMetrics类中属???scaledDensity??
         * @return 
         */  
        public static int px2sp(Context context, float pxValue) {
            return (int) (pxValue / context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
        }  
      
        /** 
         * 将sp值转换为px值，保证文字大小不变 
         *  
         * @param spValue 
         *            （DisplayMetrics类中属???scaledDensity??
         * @return 
         */  
        public static int sp2px(Context context, float spValue) {
            return (int) (spValue * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);
        }  
        
    	public static int getScreenWidth(Context context) {
    	    WindowManager manager = (WindowManager) context
    	            .getSystemService(Context.WINDOW_SERVICE);
    	    Display display = manager.getDefaultDisplay();
    	    return display.getWidth();    
    	}    
    	//获取屏幕的高??    
    	public static int getScreenHeight(Context context) {
    	    WindowManager manager = (WindowManager) context
    	            .getSystemService(Context.WINDOW_SERVICE);
    	    Display display = manager.getDefaultDisplay();
    	    return display.getHeight();    
    	} 
    }  