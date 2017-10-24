package com.xidian.spatial.models.tile;

import com.xidian.spatial.models.pyramid.PyramidAccess;

/**
 * 文件描述：瓦片数据模型类
 * 创建作者：陈苗
 * 创建时间：2017/10/24 19:02
 */
public abstract class TileData implements PyramidAccess {
    /**
     * 金字塔名称
     */
    protected String pyramidName;

    public String getPyramidName()
    {
        return pyramidName;
    }
}
