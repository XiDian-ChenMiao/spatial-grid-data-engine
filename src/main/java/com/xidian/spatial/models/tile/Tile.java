package com.xidian.spatial.models.tile;

/**
 * 文件描述：瓦片类，由瓦片ID，瓦片长度和瓦片数据部分组成
 * 创建作者：陈苗
 * 创建时间：2017/10/24 15:32
 */
public class Tile {
    /**
     * 瓦片ID
     */
    private TileID tileId;
    /**
     * 瓦片长度
     */
    private int length;
    /**
     * 瓦片数据
     */
    private byte[] data;

    public Tile() {
        this(new TileID(), null);
    }

    public Tile(final TileID tileId, final byte[] data) {
        this.tileId = tileId;
        this.data = data;
        this.length = this.data == null ? 0 : data.length;
    }
}
