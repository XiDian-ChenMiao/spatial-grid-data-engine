package com.xidian.spatial.models.pyramid;

import com.xidian.spatial.models.tile.Tile;

/**
 * 文件描述：访问金字塔中瓦片的接口
 * 创建作者：陈苗
 * 创建时间：2017/10/24 15:30
 */
public interface PyramidAccess {
    /**
     * 获取瓦片的接口
     * @param level 层级
     * @param x 起始坐标行坐标
     * @param y 其实坐标纵坐标
     * @return
     */
    Tile getTile(int level, int x, int y);
}
