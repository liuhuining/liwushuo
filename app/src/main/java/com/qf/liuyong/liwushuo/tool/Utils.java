package com.qf.liuyong.liwushuo.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangjw on 2016/11/3.
 */
public class Utils {

    public static String formatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd EE");
        return simpleDateFormat.format(new Date(time));
    }
}
