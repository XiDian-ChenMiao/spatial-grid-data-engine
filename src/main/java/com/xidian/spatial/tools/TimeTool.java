package com.xidian.spatial.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件描述：关于时间的处理工具
 * 创建作者：陈苗
 * 创建时间：2017/10/25 20:22
 */
public class TimeTool {
    /**
     * 返回当前时间对应的17位long值
     * @return
     */
    public static long getCurrentTimeMillisLong()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return Long.valueOf(formatter.format(date));
    }
}
