package com.xidian.spatial.models.index;


import com.xidian.spatial.models.pyramid.PyramidVersionName;
import com.xidian.spatial.models.tile.TileID;

/**
 * 瓦片索引行键类 ，包括瓦片的金字塔版本名与瓦片的Id
 * 格式：金字塔名称@日期#瓦片Id(级别+行号+列号)
 */
public class TileIndexRowKey {
    /**
     * 瓦片索引行键分隔符
     */
    public static final String TILE_INDEX_ROWKEY_SEPARATOR = "#";
    /**
     * 金字塔版本信息
     */
    private PyramidVersionName pyramidVersionName;
    /**
     * 瓦片ID信息
     */
    private TileID tileID;
    /**
     * 基数
     */
    public static final int RADIX = 4;

    public TileIndexRowKey(PyramidVersionName pyramidVersionName, TileID tileID) {
        this.pyramidVersionName = pyramidVersionName;
        this.tileID = tileID;
    }

    public PyramidVersionName getPyramidVersionName() {
        return pyramidVersionName;
    }

    public TileID getTileID() {
        return this.tileID;
    }

    public void setPyramidVersionName(PyramidVersionName pyramidVersionName) {
        this.pyramidVersionName = pyramidVersionName;
    }

    public void setTileID(TileID tileID) {
        this.tileID = tileID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(pyramidVersionName.toString());
        sb.append(TILE_INDEX_ROWKEY_SEPARATOR);
        sb.append(tileID.getQuadtreeCode());
        return sb.toString();
    }

    public static TileIndexRowKey parseTileIndexRowKey(String tileIndexRowKey) {
        String[] args = tileIndexRowKey.split(TILE_INDEX_ROWKEY_SEPARATOR);
        return new TileIndexRowKey(PyramidVersionName.parse(args[0]),
                TileID.parse(args[1]));
    }

    public static String getQuadtreeCode(String tileIndexRowKey) {
        return tileIndexRowKey.split(TILE_INDEX_ROWKEY_SEPARATOR)[1];
    }

    /**
     *
     * @param isSchemaGeodetic 是否为地理经纬度投影
     * @param minLevel 最小级别
     * @param quadTreeCode 由级别和行列号组成的四叉树编码
     * @return
     */
    public static long getIndexOrderNumber(boolean isSchemaGeodetic, int minLevel, String quadTreeCode) {
        long order = 0;
        final int length = quadTreeCode.length();
        for (int i = 1; i < length; i++) {
            int level = Integer.valueOf(quadTreeCode.substring(0, i));
            if (level == (length - i - 1)) {
                order = (long) ((Math.pow(RADIX, level) - Math.pow(RADIX, minLevel)) / 3);//前minlevel到level-1级的瓦片个数
                order = isSchemaGeodetic ? order * 2 : order;
                String flagXYQuadTreeCode = quadTreeCode.substring(i);
                for (int j = 0, numberlength = level + 1; j < numberlength; j++) {
                    order += Integer.valueOf(String.valueOf(flagXYQuadTreeCode.charAt(level - j))) * (long) Math.pow(RADIX, j);
                }
                return order;
            }
        }
        return order;
    }
}
