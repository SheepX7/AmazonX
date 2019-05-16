package com.xuranus.amx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xuranus.amx.base.BaseActivity;
import com.xuranus.amx.fragment.AccountFragment;
import com.xuranus.amx.fragment.HomeFragment;

import java.util.ArrayList;

public class AmazonXActivity extends BaseActivity {

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private String[] mTitles = {"首页", "账号", "系统", "更多"};
    private int[] mIconUnselectIds = {R.mipmap.tab_category_normal, R.mipmap.tab_file_normal,
            R.mipmap.tab_remote_normal, R.mipmap.tab_category_normal};
    private int[] mIconSelectIds = {R.mipmap.tab_category_press, R.mipmap.tab_file_press,
            R.mipmap.tab_remote_press, R.mipmap.tab_category_press};

    private HomeFragment homeFragment;
    private AccountFragment accountFragment;

    private CommonTabLayout mTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabEntities();
        selectFragments(0);
        initView();
    }


    private void selectFragments(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        // Hide fragment
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }

        if (accountFragment != null) {
            transaction.hide(accountFragment);
        }

        // Add and show fragment by position
        switch (position) {
            case 0:
                if (null == homeFragment) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content_view, homeFragment, "Home");
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (null == accountFragment) {
                    accountFragment = new AccountFragment();
                    transaction.add(R.id.content_view, accountFragment, "Account");
                } else {
                    transaction.show(accountFragment);
                }
                break;
        }

        transaction.commitAllowingStateLoss();

    }

    private void initView() {
        mTabLayout = findViewById(R.id.tl_3);
        mTabLayout.setTabData(mTabEntities);

        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                selectFragments(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private void initTabEntities() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
    }


    /**
     * Tab Beans
     */
    public static class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }

}
