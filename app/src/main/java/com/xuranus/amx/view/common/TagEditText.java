
package com.xuranus.amx.view.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuranus.amx.R;


public class TagEditText extends LinearLayout {
    public TagEditText(Context context) {
        super(context);
        initView(context, null);
    }

    public TagEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TagEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        setOrientation(HORIZONTAL);

        // init textview
        TextView mTag = new TextView(context);
        mTag.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams tagLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tagLayoutParams.gravity = Gravity.CENTER;

        addView(mTag, tagLayoutParams);

        // init edittext
        EditText mEdit = new EditText(context);
        mEdit.setGravity(Gravity.CENTER | Gravity.LEFT);
        mEdit.setLines(1);

        LinearLayout.LayoutParams editLayoutPatams = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT);
        editLayoutPatams.weight = 1;
        addView(mEdit, editLayoutPatams);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.tet);
            String headTag = ta.getString(R.styleable.tet_tet_text);
            if (headTag != null) {
                mTag.setText(headTag);
            }

            ta.recycle();
        }
    }
}
