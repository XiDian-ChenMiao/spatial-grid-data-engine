package com.xidian.spatial.models.index;

/**
 * 文件描述：瓦片索引类，由瓦片行键和瓦片值组成
 * 创建作者：陈苗
 * 创建时间：2017/10/26 9:33
 */
public class TileIndex {
    private TileIndexRowKey rowKey;
    private TileIndexValue value;

    public TileIndex(final TileIndexRowKey rowKey, final TileIndexValue value) {
        this.rowKey = rowKey;
        this.value = value;
    }

    public TileIndexRowKey getRowKey() {
        return rowKey;
    }

    public TileIndexValue getValue() {
        return value;
    }
}
