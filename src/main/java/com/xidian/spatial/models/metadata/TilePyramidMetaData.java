package com.xidian.spatial.models.metadata;

import com.xidian.spatial.enumation.metadata.DataTypeEnum;
import com.xidian.spatial.enumation.metadata.SchemaTypeEnum;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * 文件描述：瓦片金字塔模型类
 * 创建作者：陈苗
 * 创建时间：2017/10/23 16:38
 */
public class TilePyramidMetaData {
    private static final Logger logger = LoggerFactory.getLogger(TilePyramidMetaData.class);
    /**
     * 核心元数据信息
     */
    public String name = "";/*金字塔名称*/
    public DataTypeEnum dataType = DataTypeEnum.DATA_UNKNOW;/*数据类型*/
    public SchemaTypeEnum schemaType = SchemaTypeEnum.SCHEMA_GEODETIC;/*投影类型*/
    public String tileFormat = "";/*瓦片压缩格式*/
    public int tileSize = 0;/*瓦片大小*/
    /**
     * 非核心元数据信息
     */
    public String version = ""; /*金字塔版本*/
    public long updateTime = 0l; /*金字塔更新时间*/
    public int minLevel = Integer.MAX_VALUE; /*最小级别*/
    public int maxLevel = Integer.MIN_VALUE; /*最大级别*/
    public double east = -180.0; /*东经*/
    public double west = 180.0;/*西经*/
    public double south = 90.0;/*南纬*/
    public double north = -90.0;/*北纬*/
    public double minResolution = -1.0; /*最小分辨率*/
    public double maxResolution = -1.0; /*最大分辨率*/
    public String keywords = ""; /*描述信息*/

    public boolean deletes = false;/*是否已经删除*/
    public boolean quadtree = false;/*是否已经是四叉树方式*/

    @Override
    public String toString() {
        return "TilePyramidMetaData{" +
                "name='" + name + '\'' +
                ", dataType=" + dataType +
                ", schemaType=" + schemaType +
                ", tileFormat='" + tileFormat + '\'' +
                ", tileSize=" + tileSize +
                ", version='" + version + '\'' +
                ", updateTime=" + updateTime +
                ", minLevel=" + minLevel +
                ", maxLevel=" + maxLevel +
                ", east=" + east +
                ", west=" + west +
                ", south=" + south +
                ", north=" + north +
                ", minResolution=" + minResolution +
                ", maxResolution=" + maxResolution +
                ", keywords='" + keywords + '\'' +
                ", deletes=" + deletes +
                ", quadtree=" + quadtree +
                '}';
    }

    /**
     * 从金字塔元数据配置文档中获取信息
     * @param xmlFile
     * @return
     */
    private static Element getInfo(String xmlFile) {
        StringReader read = new StringReader(xmlFile);
        SAXBuilder builder = new SAXBuilder();
        Document document;
        try {
            document = builder.build(read);
        } catch (Exception e) {
            logger.error("read configuration " + xmlFile + "error");
            return null;
        }
        Element rootElement = document.getRootElement();
        return rootElement;
    }

    /**
     * 通过配置文件获取瓦片金字塔元数据对象
     * @param xmlFile 配置文件名
     * @return
     */
    public static TilePyramidMetaData get(String xmlFile) {
        Element element = getInfo(xmlFile);
        if (element == null)
            return null;
        TilePyramidMetaData metadata = new TilePyramidMetaData();
        metadata.name = element.getAttributeValue("name");
        metadata.dataType = DataTypeEnum.valueOf(element.getAttributeValue("dataType"));
        metadata.version = element.getAttributeValue("version");
        metadata.updateTime = Long.valueOf(element
                .getAttributeValue("updateTime"));
        metadata.keywords = element.getAttributeValue("keywords");
        metadata.schemaType = SchemaTypeEnum.valueOf(element.getAttributeValue("schemaType"));
        metadata.tileSize = Integer.valueOf(element
                .getAttributeValue("tileSize"));
        metadata.tileFormat = element
                .getAttributeValue("tileFormat");
        metadata.minLevel = Integer.valueOf(element
                .getAttributeValue("minLevel"));
        metadata.maxLevel = Integer.valueOf(element
                .getAttributeValue("maxLevel"));
        metadata.east = Double.valueOf(element
                .getAttributeValue("east"));
        metadata.west = Double.valueOf(element
                .getAttributeValue("west"));
        metadata.north = Double.valueOf(element
                .getAttributeValue("north"));
        metadata.south = Double.valueOf(element
                .getAttributeValue("south"));
        metadata.minResolution = Double.valueOf(element
                .getAttributeValue("minResolution"));
        metadata.maxResolution = Double.valueOf(element
                .getAttributeValue("maxResolution"));
        return metadata;
    }
}
