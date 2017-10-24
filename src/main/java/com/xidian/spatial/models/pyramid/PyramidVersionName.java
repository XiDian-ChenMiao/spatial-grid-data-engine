package com.xidian.spatial.models.pyramid;

/**
 * 文件描述：
 * 创建作者：陈苗
 * 创建时间：2017/10/24 20:56
 */
public class PyramidVersionName {
    public static final String VERSION_NAME_SEPARATOR = "@";
    private String pyramidName = "";
    private long updateTime = 0;

    public PyramidVersionName() {
        this("", 0);
    }

    public PyramidVersionName(String pyramidName, long updateTime) {
        this.pyramidName = pyramidName;
        this.updateTime = updateTime;
    }

    public String getPyramidName() {
        return this.pyramidName;
    }

    public long getUpdateTime() {
        return this.updateTime;
    }

    public void setPyramidName(String pyramidName) {
        this.pyramidName = pyramidName;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public static PyramidVersionName parse(String pyramidVersionName) {
        String[] args = pyramidVersionName.split(VERSION_NAME_SEPARATOR);
        if (args.length != 2) {
            return null;
        }
        return new PyramidVersionName(args[0], Long.valueOf(args[1]));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(pyramidName);
        sb.append(VERSION_NAME_SEPARATOR);
        sb.append(updateTime);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        PyramidVersionName object = (PyramidVersionName) obj;
        return this.pyramidName.equals(object.pyramidName) && this.updateTime == object.updateTime;
    }

    @Override
    public int hashCode() {
        return this.pyramidName.hashCode() + Long.valueOf(this.updateTime).hashCode();
    }
}
