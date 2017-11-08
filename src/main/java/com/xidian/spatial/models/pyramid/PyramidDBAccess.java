package com.xidian.spatial.models.pyramid;

import com.xidian.spatial.models.metadata.TilePyramidMetaData;

import java.util.ArrayList;

/**
 * 文件描述：瓦片金字塔数据库访问接口
 * 创建作者：陈苗
 * 创建时间：2017/10/24 20:55
 */
public interface PyramidDBAccess {
    /**
     * 获得金字塔名列表
     * @return
     */
    ArrayList<String> getPyramidNameList();

    /**
     * 根据金字塔名称获得金字塔元数据
     * @param pyramidName 瓦片金字塔名
     * @return
     */
    TilePyramidMetaData getPyramidMetaData(String pyramidName);

    /**
     * 根据指定时间戳，获得此时间戳之前（包括此时间）的所有金字塔版本的元数据
     * @param pyramidName 瓦片金字塔名
     * @param timestamp 时间戳
     * @return
     */
    TilePyramidMetaData getPyramidMetaData(String pyramidName, long timestamp);

    /**
     * 获得金字塔核心元数据
     * @param pyramidName 瓦片金字塔名
     * @return
     */
    TilePyramidMetaData getPyramidCoreMetaData(String pyramidName);

    /**
     * 逻辑删除金字塔
     * @param pyramidName 瓦片金字塔名
     */
    void logicDeletePyramid(String pyramidName);

    /**
     * 获得金字塔版本元数据（非核心元数据）
     * @param pyramidVersionName 瓦片金字塔版本名
     * @return
     */
    TilePyramidMetaData getPyramidVersionMetaData(PyramidVersionName pyramidVersionName);

    /**
     * 获得所有逻辑删除的金字塔版本名
     * @return
     */
    ArrayList<PyramidVersionName> getAllLogicDeletedPyramidVersionNameList();

    /**
     * 获得金字塔的所有版本元数据列表
     * @param pyramidName 瓦片金字塔名
     * @return
     */
    ArrayList<TilePyramidMetaData> getPyramidVersionMetaDataList(String pyramidName);

    /**
     * 逻辑删除金字塔版本
     * @param pyramidVersionName 瓦片金字塔版本名
     */
    void logicDeletePyramidVersion(PyramidVersionName pyramidVersionName);

    /**
     * 找回金字塔版本
     * @param pyramidVersionName 瓦片金字塔版本名
     */
    void restorePyramidVersion(PyramidVersionName pyramidVersionName);

    /**
     * 获得截止此时间戳（包括此时间戳）的金字塔的最新更新时间
     * @param pyramidName
     * @param endTime
     * @return
     */
    long getPyramidLatestUpdateTime(String pyramidName, long endTime);

    /**
     * 判断金字塔是否存在
     * @param pyramidName
     * @return
     */
    boolean pyramidExist(String pyramidName);

    /**
     * 获得金字塔总大小
     * @param pyramidName
     * @return
     */
    long getPyramidTileTotalSize(String pyramidName);

    /**
     * 更新金字塔元数据
     * @param tpmd
     * @return
     */
    boolean updatePyramidVersion(TilePyramidMetaData tpmd);
}
