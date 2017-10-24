package com.xidian.spatial.models.tile;

/**
 * 文件描述：瓦片ID类，由瓦片所在级别和瓦片所在级别的坐标组成
 * 创建作者：陈苗
 * 创建时间：2017/10/24 15:33
 */
public class TileID {
    /**
     * 标识一个瓦片起始点位置的类
     */
    public static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private int level;

    private Point point;

    public TileID() {
        this(0, new Point());
    }

    public TileID(final int level, final Point point) {
        this.level = level;
        this.point = point;
    }

    public TileID(final int level, final int x, final int y) {
        this.level = level;
        this.point = new Point(x, y);
    }
    public int getLevel() {
        return level;
    }
    public Point getPoint() {
        return point;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getX()
    {
        return point.x;
    }
    public int getY()
    {
        return point.y;
    }
    public void setX(int x) {
        point.x = x;
    }

    public void setY(int y) {
        point.y = y;
    }

    @Override
    public String toString() {
        return "TileID{" +
                "level=" + level +
                ", point=" + point +
                '}';
    }

    /**
     * 获取其对应的四叉树编码
     *
     * @return
     */
    public String getQuadtreeCode() {
        int x = point.x;
        int y = point.y;
        int totalNum = (int) Math.pow(2, level);
        StringBuilder sb = new StringBuilder("");
        sb.append(String.valueOf(level));/*瓦片所处层级*/
        if (y >= totalNum) {
            y -= totalNum;/*处于矩形的右边*/
            sb.append("1");
        } else
            sb.append("0");/*处于矩形的左边*/
        for (int tempLevel = level; tempLevel > 0; tempLevel--) {
            int rowNum = (int) Math.pow(2, tempLevel - 1);
            int row = x / rowNum;
            int column = y / rowNum;
            sb.append(String.valueOf(row * 2 + column));
            x %= rowNum;
            y %= rowNum;
        }
        return sb.toString();
    }

    /**
     * 根据四叉树编码进行解析生成瓦片ID对象
     *
     * @param quadTreeCode
     * @return
     */
    public static TileID parse(final String quadTreeCode) {
        int length = quadTreeCode.length();
        for (int i = 1; i < length; i++) {
            int level = Integer.valueOf(quadTreeCode.substring(0, i));
            if (level == (length - i - 1)) {
                String xyQuadTreeCode = quadTreeCode.substring(i + 1);
                int x, y;
                int maxSize = (int) Math.pow(2, level) - 1;
                Point start = new Point(0, 0);/*小区域的左上角坐标点*/
                Point stop = new Point(maxSize, maxSize);/*小区域的右下角坐标点*/
                /*从最高层到最底层，根据高层的四叉树编码不断缩小区域的大小，直到最后区域为一张图片*/
                for (int j = 0, k = xyQuadTreeCode.length(); j < k; j++) {
                    switch (xyQuadTreeCode.charAt(j)) {
                        case '0':
                            stop.x = (start.x + stop.x) / 2;
                            stop.y = (start.y + stop.y) / 2;
                            break;
                        case '1':
                            start.y = (start.y + stop.y + 1) / 2;
                            stop.x = (start.x + stop.x) / 2;
                            break;
                        case '2':
                            start.x = (start.x + stop.x + 1) / 2;
                            stop.y = (start.y + stop.y) / 2;
                            break;
                        case '3':
                            start.x = (start.x + stop.x + 1) / 2;
                            start.y = (start.y + stop.y + 1) / 2;
                            break;
                    }
                }
                x = start.x;
                y = start.y;
                if ('1' == quadTreeCode.charAt(i))
                    y += (int) Math.pow(2, level);
                return new TileID(level, new Point(x, y));
            }
        }
        return null;
    }
}
