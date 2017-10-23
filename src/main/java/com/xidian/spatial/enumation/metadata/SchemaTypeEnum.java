package com.xidian.spatial.enumation.metadata;

/**
 * 文件描述：金字塔元数据中投影类型枚举
 * 创建作者：陈苗
 * 创建时间：2017/10/23 16:21
 */
public enum SchemaTypeEnum {
    /**
     * 未知类型
     */
    SCHEMA_UNKNOWN,
    /**
     * 地理经纬度坐标投影
     */
    SCHEMA_GEODETIC,
    /**
     * 墨卡托投影
     */
    SCHEMA_MERCATOR;
}
