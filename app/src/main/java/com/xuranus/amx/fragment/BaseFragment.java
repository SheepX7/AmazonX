package com.xuranus.amx.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xuranus.amx.R;

public class BaseFragment extends Fragment {

    public TextView tvNavigation;

    public void initNavigation(View covertView) {
        tvNavigation = covertView.findViewById(R.id.tv_common_navigation);
    }
    
    public void showShortToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
