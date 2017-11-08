package com.xidian.spatial.enumation;

/**
 * 文件描述：元数据中的数据类型枚举
 * 创建作者：陈苗
 * 创建时间：2017/10/23 16:09
 */
public enum DataTypeEnum {
    /**
     * 未知数据类型
     */
    DATA_UNKNOW,
    /**
     * 栅格影像数据
     */
    DATA_RASTER_IMAGE,
    /**
     * 栅格高程数据
     */
    DATA_RASTER_DEM,
    /**
     * 矢量电子地图数据
     */
    DATA_VECTOR_MAP,
    /**
     * 矢量数据
     */
    DATA_VECTOR_VECTOR;
}
