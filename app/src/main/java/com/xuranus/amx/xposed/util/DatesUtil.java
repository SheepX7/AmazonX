package com.xuranus.amx.xposed.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatesUtil {

    public static String getLoggerSysDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ/");
        return simpleDateFormat.format(new Date());
    }
}
