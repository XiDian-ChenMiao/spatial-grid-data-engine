package com.xidian.spatial.models.index;

/**
 * 瓦片索引值类，包括瓦片所处的瓦片打包文件的文件名、瓦片在打包文件中的偏移量、瓦片在瓦片打包文件中所占的大小 和瓦片是否被逻辑删除标志位
 */
public class TileIndexValue {
    public static final String TILE_INDEX_VALUE_SEPARATOR = "#";
    private String filename;
    private int offset;
    /**
     * 瓦片在瓦片打包文件中所占的大小
     * (魔数（long）大小 + 魔数（long）大小 + level（int）大小 + x（int）大小 + y（int）大小 + 瓦片大小（int）大小 + 瓦片数据大小（byte[]）大小)
     * 8 + 8 + 4 + 4 + 4 + 4 + tile.size()
     */
    private int length;
    private String delete;

    public TileIndexValue() {
        this("", 0, 0, "");
    }

    public TileIndexValue(final String filename, final int offset, final int length, final String delete) {
        this.filename = filename;
        this.offset = offset;
        this.length = length;
        this.delete = delete;
    }

    public static TileIndexValue parseTileIndexValue(String tileIndexValue) {
        String[] args = tileIndexValue.split(TILE_INDEX_VALUE_SEPARATOR);
        return new TileIndexValue(args[0], Integer.valueOf(args[1]), Integer.valueOf(args[2]), args[3]);
    }

    public String getFilename() {
        return filename;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public String getDelete() {
        return delete;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        sb.append(filename);
        sb.append(TILE_INDEX_VALUE_SEPARATOR);
        sb.append(offset);
        sb.append(TILE_INDEX_VALUE_SEPARATOR);
        sb.append(length);
        sb.append(TILE_INDEX_VALUE_SEPARATOR);
        sb.append(delete);
        return sb.toString();
    }
}
