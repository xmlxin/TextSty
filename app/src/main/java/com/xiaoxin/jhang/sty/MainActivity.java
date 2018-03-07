package com.xiaoxin.jhang.sty;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xiaoxin.jhang.sty.Util.TextStyUtils;
import com.xiaoxin.jhang.sty.adapter.TextAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String CONTENT = "";
    private RecyclerView rvList;
    private EditText etContent;
    TextAdapter mTextAdapter;
    List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etContent = (EditText) findViewById( R.id.et_content);
        rvList = (RecyclerView) findViewById(R.id.rv_list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvList.setLayoutManager(manager);
        mList = new ArrayList<>();
        mList.add("横线字");
        mList.add("三角");
        mList.add("叶子");
        mList.add("花藤");
        mList.add("菊花文A");
        mList.add("菊花文B");
        mList.add("竖直");
        mList.add("反字");
        mTextAdapter = new TextAdapter(R.layout.text_recyle_item,mList);
        rvList.setAdapter(mTextAdapter);

        mTextAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StringBuffer content = TextStyUtils.styTent(position, etContent.getText().toString().trim());
                clipboard(content);
                etContent.setText(content);
            }
        });

    }

    private void clipboard(StringBuffer content) {

        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", content);
        cm.setPrimaryClip(mClipData);
    }


}
