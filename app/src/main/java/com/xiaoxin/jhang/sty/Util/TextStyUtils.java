package com.xiaoxin.jhang.sty.Util;

import android.content.Context;
import android.util.Log;
import com.xiaoxin.jhang.sty.R;

import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * @author xiaoxin
 * @date 2018/1/6
 * @describe ：
 * 修改内容
 */

public class TextStyUtils {

    public static StringBuffer randomString(Context context,String content) {
        Random random = new Random();
        int randomInt = random.nextInt(12);
        StringBuffer text = new StringBuffer("");
        switch (randomInt) {
            case 0:
                text =ua(context,content);
                break;
            case 1:
                text = a(context,content);
                break;
            case 2:
                text = ac_styTool(context,content);
                break;
            case 3:
                text = aac_styTool(context,content);
                break;
            case 4:
                text = c_styTool(context,content);
                break;
            case 5:
                text = clickvip(content);
                break;
            case 6:
                text = clicksvip(content);
                break;
            case 7:
                text = click(content);
                break;
            case 8:
                text = bclick(content);
                break;
            case 9:
                text = cclick(content);
                break;
            case 10:
                dclick(content);
                break;
            case 11:
                text = eclick(content);
                break;
            default:
                text = new StringBuffer(content);
        }
        return text;
    }

    /**
     * 横线字
     * @param context
     * @param content
     * @return
     */
    public static StringBuffer ua(Context context,String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "妮̶";
        for (char anA : a) {
            b.append(mo.replace('妮', anA));
        }
        return b;
    }

    public static StringBuffer a(Context context,String content) {

        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = context.getResources().getString(R.string.android_gifc);
        for (char anA : a) {
            b.append(mo.replace('n', anA));
        }
        return b;
    }

    public static StringBuffer ac_styTool(Context context,String content) {

        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = context.getResources().getString(R.string.android_gifb);
        for (char anA : a) {
            b.append(mo.replace('n', anA));
        }
        return b;
    }

    public static StringBuffer aac_styTool(Context context,String content) {

        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = context.getResources().getString(R.string.android_gifa);
        for (char anA : a) {
            b.append(mo.replace('n', anA));
        }
        return b;
    }

    /**
     * 竖线A
     */
    public static StringBuffer c_styTool(Context context,String content) {

        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = context.getResources().getString(R.string.android_gifa);
        for (char anA : a) {
            b.append(mo.replace('n', anA));

        }
      return b;
    }

    /**
     * 三角
     */
    public static StringBuffer clickvip(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = null;
        try {
            mo = "◤Q◢";
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (char anA : a) {
            try {
                b.append(mo.replace('Q', anA));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
      return b;
    }

    /**
     * 叶子
     */
    public static StringBuffer clicksvip(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "❦H❧";
        for (char anA : a) {
            b.append(mo.replace('H', anA));
        }
       return b;
    }

    /**
     * 花藤
     */
    public static StringBuffer click(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "ζั͡爱 ั͡✾";
        for (char anA : a) {
            b.append(mo.replace('爱', anA));
        }
        return b;
    }

    /**
     * 菊花文
     */
    public static StringBuffer bclick(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "爱҉";
        for (char anA : a) {
            b.append(mo.replace('爱', anA));
        }
        return b;
    }

    /**
     * 菊花文B
     */
    public static StringBuffer cclick(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "敏ۣۖ";
        for (char anA : a) {
            b.append(mo.replace('敏', anA));
        }
        return b;

    }

    /**
     * 竖直
     */
    public static StringBuffer dclick(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "爱\n";
        for (char anA : a) {
            b.append(mo.replace('爱', anA));
        }
        return b;
    }

    /**
     * 反字
     */
    public static StringBuffer eclick(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "‮ ‮ ‮爱";
        for (char anA : a) {
            b.append(mo.replace('爱', anA));
        }
        Log.e(TAG, "eclick: "+b );
        return b;
    }
}
