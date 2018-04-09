package com.xiaoxin.jhang.wxspeak.service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import com.xiaoxin.jhang.wxspeak.util.Constant;
import com.xiaoxin.jhang.wxspeak.util.SharedPreferencesUtils;

import java.util.List;

/**
 * @author xiaoxin
 * @date 2018/4/08
 * @describe :
 * 修改内容
 */

public class SpeakService extends AccessibilityService {

    private static final String TAG = "SpeakService";
    public static final String TRANSFORM = "TRANSFORM";

    /** 是否转换并发送 */
    public static boolean hasSend;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        hasSend = intent.getBooleanExtra(TRANSFORM, false);
        if (hasSend) {
            if (findEditTextSend(Constant.EDITTEXT_ID)) {
                sendContent();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED) {//动态监听文本框内容变化并实时获取文本
            styContent(event);//实时转换 不可取
        }
    }

    @SuppressLint("NewApi")
    private void styContent(AccessibilityEvent event) {
//        if (!"".equals(event.getText().toString()) && event.getText().toString() != null) {
//            SharedPreferencesUtils wxConfig = SharedPreferencesUtils.init(this, "wxConfig");
//            int speakPosition = wxConfig.getInt("speak", 0);
//            StringBuffer styTent = TextStyUtils.styTent(speakPosition, event.getText().toString());
//            AccessibilityNodeInfo accessibilityNodeInfo = getRootInActiveWindow();
//            if (accessibilityNodeInfo == null) {
//                return;
//            }
//
//            List<AccessibilityNodeInfo> editNodeInfo = accessibilityNodeInfo.findAccessibilityNodeInfosByText(event.getText().toString());
//            if (editNodeInfo.size() > 0) {
//                Bundle arguments = new Bundle();
//                arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, styTent.toString());
//                editNodeInfo.get(0).performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
//            }
//        }
    }

    public String transformStyContent(String content) {

        return com.xiaoxin.jhang.wxspeak.util.TextStyUtils.styTent(SharedPreferencesUtils.
                init(this, Constant.SPCONFIG)
                .getInt(Constant.SPEAK, 0), content);
    }

    @SuppressLint("NewApi")
    private boolean findEditTextSend(String editId) {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> editInfo = rootNode.findAccessibilityNodeInfosByViewId(editId);
            if(editInfo!=null&&!editInfo.isEmpty() && editInfo.size() > 0 ){
                Bundle bundle = new Bundle();
                bundle.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, transformStyContent(editInfo.get(0).getText().toString()));
                editInfo.get(0).performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, bundle);
                return true;
            }
        }
        return false;
    }

    @SuppressLint("NewApi")
    private void sendContent() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> list = nodeInfo
                    .findAccessibilityNodeInfosByText(Constant.SEND_TEXT);
            if (list != null && list.size() > 0) {
                for (AccessibilityNodeInfo n : list) {
                    if(n.getClassName().equals(Constant.BUTTON) || n.getClassName().equals(Constant.TEXTVIEW) && n.isEnabled()){
                        n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                    }
                }

            }
        }
    }

    @Override
    public void onInterrupt() {
        Toast.makeText(this,"辅助服务断开连接了", Toast.LENGTH_SHORT).show();
    }
}
