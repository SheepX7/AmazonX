package com.xuranus.amx.view.desktop;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.xuranus.amx.AmazonX;
import com.xuranus.amx.R;
import com.xuranus.amx.log.ConsoleLog;
import com.xuranus.amx.log.XLog;
import com.xuranus.amx.view.log.LogView;
import com.xuranus.amx.xposed.util.DatesUtil;

public class DesktopCommand extends FrameLayout implements LogView {

    private TextView mCommandLog;
    private TextView mCommandClose;
    private ConsoleLog consoleLog;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_ERROR = -1;
    private static final int TYPE_OK = 1;
    private Context mContext;
    private static final int MIN_HEIGHT = 300;
    private Handler mH;

    public DesktopCommand(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public DesktopCommand(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DesktopCommand(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final float density = context.getResources().getDisplayMetrics().density;
        View convertView = LayoutInflater.from(context).inflate(R.layout.command_log, null);
        findViewsByConvertView(convertView);
        consoleLog = new ConsoleLog(this, null);
        mContext = context;
        mH = new Handler(context.getMainLooper());
        addView(convertView);
    }


    private void findViewsByConvertView(View convertView) {
        mCommandClose = convertView.findViewById(R.id.tv_command_close);
        mCommandLog = convertView.findViewById(R.id.tv_command_log);
    }

    @Override
    public synchronized void appendLog(String tag, final LEVEL level, final String text) {

        mH.post(new Runnable() {
            @Override
            public void run() {
                int color = Color.WHITE;
                switch (level) {
                    case ERRO:
                        color = Color.RED;
                        break;
                    case INFO:
                        color = Color.GRAY;
                        break;
                }

                int start = mCommandLog.length();
                mCommandLog.append(appendLogSystemInfo(text, level));
                int end = mCommandLog.length();
                ((Spannable) mCommandLog.getText()).setSpan(new ForegroundColorSpan(color), start, end, 0);
                mCommandLog.append("\n");
                int offset = mCommandLog.getLineCount() * mCommandLog.getLineHeight();
                if (offset > mCommandLog.getHeight()) {
                    mCommandLog.scrollTo(0, offset - mCommandLog.getHeight());
                }
            }
        });

    }

    @Override
    public XLog getLogger() {
        return consoleLog;
    }

    private String appendLogSystemInfo(String str, LEVEL level) {
        StringBuilder builder = new StringBuilder();
        builder.append(DatesUtil.getLoggerSysDate());
        builder.append(level.name() + "/");
        builder.append(AmazonX.getInstance().getPackageName() + ":");
        builder.append(str);
        return builder.toString();
    }
}
