package com.xiaoxin.jhang.wxspeak;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.xiaoxin.jhang.wxspeak.util.Constant;
import com.xiaoxin.jhang.wxspeak.util.TextStyUtils;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author xiaoxin
 * @date 2018/4/08
 * @describe ：只支持wx:6.6.5
 * 修改内容
 */

public class WxMain implements IXposedHookLoadPackage {

    private static final String TAG = "WxMain";
    XSharedPreferences xSharedPreferences;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.appInfo == null || (lpparam.appInfo.flags & (ApplicationInfo.FLAG_SYSTEM |
                ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0) {
            return;
        }

        final String packageName = lpparam.packageName;

        if (Constant.TARGET_PACKAGE_MMS.equals(packageName)) {
            XposedHelpers.findAndHookMethod(Application.class,
                    "attach",
                    Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Context context = (Context) param.args[0];
                            ClassLoader classLoader = context.getClassLoader();

                            wxSendMsg(context,classLoader);

                        }
                    });
        }

    }

    private void wxSendMsg(final Context ctx, ClassLoader classLoader) {
        try {
            XposedHelpers.findAndHookMethod(Constant.SEND_MSG_MM_CLASS, classLoader,
                    Constant.SEND_MSG_MM_METHOD, String.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            //微信内部类MicroMsg.ChatFooter
                            param.args[0] = TextStyUtils.styTent(reload().getInt(Constant.SPEAK, 0), (String) param.args[0]);
                            super.beforeHookedMethod(param);
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XSharedPreferences reload(){
        if (xSharedPreferences == null){
            xSharedPreferences = new XSharedPreferences(BuildConfig.APPLICATION_ID, Constant.SPCONFIG);
            xSharedPreferences.makeWorldReadable();
        }else {
            xSharedPreferences.reload();
        }
        return xSharedPreferences;
    }

    /**
     * @param context
     * @param pkgName
     * @return
     */
    private String getVersionName(Context context, String pkgName) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(pkgName, 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
