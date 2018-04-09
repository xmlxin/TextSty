package com.xiaoxin.jhang.wxspeak;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.jhang.wxspeak.adapter.TextAdapter;
import com.xiaoxin.jhang.wxspeak.util.AccessibilityUtil;
import com.xiaoxin.jhang.wxspeak.util.Constant;
import com.xiaoxin.jhang.wxspeak.util.SharedPreferencesUtils;
import com.xiaoxin.jhang.wxspeak.util.TextStyUtils;
import com.xiaoxin.jhang.wxspeak.util.TrackerWindowManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvList;
    private EditText etContent;
    TextAdapter mTextAdapter;
    List<String> mList;
    private SharedPreferencesUtils wxConfig;
    private TrackerWindowManager mWindowManagerUtil;
    public static String text;
    public boolean hasOnclick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (!isModuleActive()) {
//            Toast.makeText(this, "模块未激活", Toast.LENGTH_SHORT).show();
//        }

        mWindowManagerUtil = new TrackerWindowManager(this);
        wxConfig = SharedPreferencesUtils.init(this, Constant.SPCONFIG);
        etContent = (EditText) findViewById( R.id.et_content);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        initAdapter();

        showDialog();
    }

    private void initAdapter() {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mList.add("横线字");
        mList.add("三角");
        mList.add("叶子");
        mList.add("花藤");
        mList.add("菊花文A");
        mList.add("菊花文B");
        mList.add("竖直");
        mList.add("反字");
        mList.add("u型字");
        mList.add("自定义文本");
        mTextAdapter = new TextAdapter(R.layout.text_recyle_item,mList);
        rvList.setAdapter(mTextAdapter);

        mTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (9 == position){
                    Toast.makeText(MainActivity.this,"暂不支持",Toast.LENGTH_SHORT).show();
                }else {
                    if (hasOnclick) {
                        etContent.setText("");
                    }else {
                        text = etContent.getText().toString().trim();
                        hasOnclick = !hasOnclick;
                    }
                    transform(position,text);
                }
            }
        });
    }

    private void transform(int position,String content) {
        clipboard(TextStyUtils.styTent(position, content));
        etContent.setText(TextStyUtils.styTent(position, content));
        wxConfig.putInt(Constant.SPEAK,position);
    }

    private void clipboard(String content) {

        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText("Label", content));
    }

    /**
     * 隐藏桌面图标
     * @param isHide
     */
    public void hideLauncherIcon(boolean isHide) {
        PackageManager packageManager = this.getPackageManager();
        int hide = isHide ? PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                : PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        packageManager.setComponentEnabledSetting(getAliasComponentName(),
                hide, PackageManager.DONT_KILL_APP);
    }

    private ComponentName getAliasComponentName() {
        return new ComponentName(MainActivity.this, BuildConfig.APPLICATION_ID);
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("需要开启辅助功能吗?")
                .setMessage("如果不能使用xposed,开启辅助功能配合悬浮窗权限效果也不错哦")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AccessibilityUtil.checkAccessibility(MainActivity.this);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MainActivity.this,"好吧(ಥ﹏ಥ)",Toast.LENGTH_SHORT).show();
                    }
                }).show().show();
    }

    /**
     * 模块是否启用
     *
     * @return
     */
    private static boolean isModuleActive() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWindowManagerUtil.showWindow(this);
    }
}
