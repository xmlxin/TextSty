package com.xiaoxin.jhang.sty.Util;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * @author xiaoxin
 * @date 2018/1/6
 * @describe ：
 * 修改内容
 */

public class TextStyUtils {

    public static StringBuffer styTent(int position,String content) {

        StringBuffer text;
        switch (position) {
            case 0:
                text = lineText(content);
                break;
            case 1:
                text = triangle(content);
                break;
            case 2:
                text = Leaf(content);
                break;
            case 3:
                text = flower(content);
                break;
            case 4:
                text = chrysantheOne(content);
                break;
            case 5:
                text = chrysantheTwo(content);
                break;
            case 6:
                text = verticalLine(content);
                break;
            case 7:
                text = cntrary(content);
                break;
            default:
                text = new StringBuffer(content);
        }
        return text;
    }

    /**
     * 横线字
     * @param content
     * @return
     */
    public static StringBuffer lineText(String content) {
        char[] a = content.toCharArray();
        StringBuffer b = new StringBuffer("");
        String mo = "妮̶";
        for (char anA : a) {
            b.append(mo.replace('妮', anA));
        }
        return b;
    }

    /**
     * 三角
     */
    public static StringBuffer triangle(String content) {
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
    public static StringBuffer Leaf(String content) {
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
    public static StringBuffer flower(String content) {
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
    public static StringBuffer chrysantheOne(String content) {
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
    public static StringBuffer chrysantheTwo(String content) {
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
    public static StringBuffer verticalLine(String content) {
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
    public static StringBuffer cntrary(String content) {
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
