package com.xidian.spatial.tools;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;

/**
 * 文件描述：压缩工具类
 * 创建作者：陈苗
 * 创建时间：2017/10/25 20:34
 */
public class ZipTool {
    private static Logger logger = LoggerFactory.getLogger(ZipTool.class);
    /**
     * 将一个文件写入到压缩流中，即完成压缩
     * @param sourceFile 要压缩的文件或文件夹
     * @param basePath   相对路径
     * @param zos        压缩流
     */
    public static void zip(File sourceFile, String basePath, ZipOutputStream zos) {
        InputStream is = null;
        try {
            if (sourceFile.isDirectory()) {
                basePath = basePath + sourceFile.getName() + "/";
                zos.putNextEntry(new ZipEntry(basePath));
                File[] files = sourceFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    zip(file, basePath, zos);
                }
            } else {
                zos.putNextEntry(new ZipEntry(basePath + sourceFile.getName()));
                is = new FileInputStream(sourceFile);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buf = new byte[1024];
                int length = -1;
                while ((length = bis.read(buf)) != -1) {
                    zos.write(buf, 0, length);
                    zos.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 压缩函数
     * @param sourcefilename 源文件名
     * @param zipfilename 压缩包文件名
     */
    public static void zip(String sourcefilename, String zipfilename) {
        File sourceFile = new File(sourcefilename);
        String basePath = "";
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(new File(zipfilename));
            zos.setEncoding("UTF-8");
            zip(sourceFile, basePath, zos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压一个文件
     * @param zipfilename 解压的文件
     * @param destDir     解压的目录
     */
    public static void unZip(String zipfilename, String destDir) {
        File file = new File(zipfilename);
        OutputStream os = null;
        InputStream is = null;
        if (!file.isFile() || !file.getName().endsWith(".zip")) {
            logger.error("can't unzip " + zipfilename);
        } else {
            destDir = destDir.endsWith("\\") ? destDir : destDir + "\\";
            byte b[] = new byte[1024];
            int length;
            ZipFile zipFile;
            try {
                zipFile = new ZipFile(file, "gbk");
                Enumeration enumeration = zipFile.getEntries();
                ZipEntry zipEntry = null;
                while (enumeration.hasMoreElements()) {
                    zipEntry = (ZipEntry) enumeration.nextElement();
                    File loadFile = new File(destDir + zipEntry.getName());
                    if (zipEntry.isDirectory()) {
                        if (!loadFile.exists()) {
                            loadFile.mkdirs();
                        }
                    } else {
                        if (!loadFile.getParentFile().exists()) {
                            loadFile.getParentFile().mkdirs();
                        }
                        os = new FileOutputStream(loadFile);
                        is = zipFile.getInputStream(zipEntry);
                        while ((length = is.read(b)) > 0) {
                            os.write(b, 0, length);
                            os.flush();
                        }
                    }

                }
                logger.info("unzip " + zipfilename + " completely.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private static void compress(File sourceFile, String basePath, ZipOutputStream zos) {
        if (!sourceFile.exists()) {
            logger.error("source file " + sourceFile.getName() + " no exists");
            return;
        }
        if (sourceFile.isDirectory()) {
            compressDir(sourceFile, basePath, zos);
        } else {
            compressFile(sourceFile, basePath, zos);
        }
    }

    private static void compressDir(File dir, String basePath, ZipOutputStream zos) {
        try {
            zos.putNextEntry(new ZipEntry(basePath + dir.getName() + "/"));
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                compress(files[i], basePath + dir.getName() + "/", zos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void compressFile(File file, String basePath, ZipOutputStream zos) {
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            zos.putNextEntry(new ZipEntry(basePath + file.getName()));
            byte[] buf = new byte[1024];
            int length = -1;
            while ((length = bis.read(buf)) != -1) {
                zos.write(buf, 0, length);
                zos.flush();
            }
        } catch (Exception e) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
