package com.xuranus.amx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xuranus.amx.R;

import static com.xuranus.amx.fragment.DetailsTaskActivity.KEY_DISPLAY_DIFF;
import static com.xuranus.amx.fragment.DetailsTaskActivity.NEED_ADD_SHOPPINGCART;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mAddShoppingCart;
    private LinearLayout mAddWishList;
    private LinearLayout mClearShoppingCart;
    private LinearLayout mClearWishList;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.home, null);
        initView(convertView);
        return convertView;
    }

    private void initView(View convertView) {

        mAddShoppingCart = convertView.findViewById(R.id.ll_add_shopping);
        mAddWishList = convertView.findViewById(R.id.ll_add_wish);
        mClearShoppingCart = convertView.findViewById(R.id.ll_clear_shoppingcart);
        mClearWishList = convertView.findViewById(R.id.ll_clear_wishlist);

        mAddShoppingCart.setOnClickListener(this);
        mAddWishList.setOnClickListener(this);
        mClearWishList.setOnClickListener(this);
        mClearShoppingCart.setOnClickListener(this);

        initNavigation(convertView);
        tvNavigation.setText("淘宝刷单");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_shopping:
                // 可以考虑两种方案：
                // 1. 从服务器拉取购物信息，然后执行加购任务，此时不需要额外的页面去支撑加购信息
                // 2. 本地新开填写购物信息页面，用户自己根据需求填写加购信息，执行加购任务
                // 这里暂时采取第二种方案，填写信息页统一用一个页面DetailsTaskActivity
                showShortToast("Todo add shopping cart");
                Intent intent = new Intent(getActivity(), DetailsTaskActivity.class);
                intent.putExtra(KEY_DISPLAY_DIFF, NEED_ADD_SHOPPINGCART);
                getActivity().startActivity(intent);
                break;
            case R.id.ll_add_wish:
                showShortToast("Todo add wish");
                break;
            case R.id.ll_clear_shoppingcart:
                showShortToast("Todo clear shopping cart");
                break;
            case R.id.ll_clear_wishlist:
                showShortToast("Todo clear wishlist");
                break;
        }
    }


}
