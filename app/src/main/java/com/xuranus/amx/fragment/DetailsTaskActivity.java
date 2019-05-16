package com.xuranus.amx.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xuranus.amx.R;
import com.xuranus.amx.base.BaseActivity;
import com.xuranus.amx.detailtask.DetailShoppingFragment;
import com.xuranus.amx.detailtask.DetailWishListFragment;

@SuppressLint("Registered")
public class DetailsTaskActivity extends BaseActivity {


    public static final String KEY_DISPLAY_DIFF = "display";

    // 来源状态标识
    public static final int NEED_ADD_SHOPPINGCART = 0;
    public static final int NEED_ADD_WISHLIST = 1;


    //------------------ Object ------------------
    private Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_task);
        // 这里使用Fragment，针对不同的情况处理展示不同的Fragment
        initData();
        initView();
        initBindData();
    }

    private void initView() {

    }

    private void initBindData() {

    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            int flag = intent.getIntExtra(KEY_DISPLAY_DIFF, -1);
            switch (flag) {
                case NEED_ADD_SHOPPINGCART:
                    mFragment = new DetailShoppingFragment();
                    break;
                case NEED_ADD_WISHLIST:
                    mFragment = new DetailWishListFragment();
                    break;
            }

            if (null != mFragment) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.add(R.id.content_view, mFragment);
                transaction.show(mFragment);
                transaction.commit();
            }

        }
    }


}
