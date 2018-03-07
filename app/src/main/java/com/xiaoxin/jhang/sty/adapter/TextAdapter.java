package com.xiaoxin.jhang.sty.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.jhang.sty.R;

import java.util.List;

/**
 * Created by chris on 2018/3/6 0006.
 */

public class TextAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TextAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_sty,item);
    }
}
