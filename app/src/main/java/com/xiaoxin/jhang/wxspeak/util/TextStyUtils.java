package com.xiaoxin.jhang.wxspeak.util;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author xiaoxin
 * @date 2018/1/6
 * @describe ：
 * 修改内容
 */

public class TextStyUtils {

    private static String transFormDst;

    public static String styTent(int position, String content) {
        if (TextUtils.isEmpty(content)) {
            return content;
        }
        if (position == 9) {
            return transfromEn(content);
        }
        return transform(styConstant(position),content);
    }

    /**
     * 自定义样式从这里添加，也可以配置在string.xml文件
     * @param position
     * @return
     */
    public static String styConstant(int position) {

        String strConstant;
        switch (position) {
            case 0:
                strConstant = "新̶";
                break;
            case 1:
                strConstant = "◤新◢";
                break;
            case 2:
                strConstant = "❦新❧";
                break;
            case 3:
                strConstant = "ζั͡新 ั͡✾";
                break;
            case 4:
                strConstant = "新҉";
                break;
            case 5:
                strConstant = "新ۣۖ";
                break;
            case 6:
                strConstant = "新\n";
                break;
            case 7:
                strConstant = "‮ ‮ 新";
                break;
            case 8:
                strConstant = "新ͧ";
                break;
            case 9:
                strConstant = "新ͧ";
//                strConstant = transfromEn();
                break;
            default:
                strConstant = "";
                break;
        }
        return strConstant;
    }

    private static synchronized String transfromEn(final String content) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TransApi  api = new TransApi(Constant.APP_ID, Constant.SECURITY_KEY);
                    transFormDst =  (new JSONObject(new JSONObject(api.getTransResult(content, "auto", "en")).getJSONArray("trans_result").get(0).toString())).getString("dst");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return transFormDst;
    }

    public static String transform(String strConstant,String content) {
        char[] chars = content.toCharArray();
        StringBuffer stringBuffer = new StringBuffer("");
        for (char ch : chars) {
            stringBuffer.append(strConstant.replace('新', ch));
        }
        return stringBuffer.toString();
    }

}
