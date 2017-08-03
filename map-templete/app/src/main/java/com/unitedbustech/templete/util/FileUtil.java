package com.unitedbustech.templete.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author yufei0213
 */
public class FileUtil {

    /**
     * 重命名
     *
     * @param path
     * @param oldName
     * @param newName
     */
    public static void renameFile(String path, String oldName, String newName) {

        try {

            if (!oldName.equals(newName)) {

                File oldfile = new File(path + "/" + oldName);
                File newfile = new File(path + "/" + newName);
                if (!oldfile.exists()) {

                    throw new Exception("file not exit");
                }
                if (newfile.exists()) {

                    throw new Exception(path + newName + "has exit");
                } else {

                    oldfile.renameTo(newfile);
                }
            } else {

                throw new Exception("newname equals oldname");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 创建文件
     *
     * @throws IOException
     */
    public static File createFile(String path, String fileName) throws IOException {

        File file = new File(path, fileName);
        file.createNewFile();

        return file;
    }

    /**
     * 创建目录
     *
     * @param dirName
     */
    public static File createDir(String dirName) {

        File dir = new File(dirName);

        try {

            if (!dir.exists())
                dir.mkdirs();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dir;
    }

    /**
     * 判断文件是否存在，存在返回TRUE
     *
     * @param file
     * @return
     */
    public static boolean isFileExist(String file) {

        boolean result = false;

        File temp = new File(file);

        try {

            if (temp.exists())
                result = true;
            else
                result = false;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     * 判断文件夹是否为空
     *
     * @param filePath
     * @return
     */
    public static boolean isFolderEmpty(String filePath) {

        boolean result = false;

        File temp = new File(filePath);

        try {

            if (temp.exists()) {

                if (temp.isDirectory()) {

                    if (temp.listFiles().length == 0)
                        result = true;
                    else
                        result = false;
                } else {

                    throw new Exception("is not folder");
                }
            } else {

                throw new Exception("file is not exit");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    /**
     * 清空文件夹
     *
     * @param folderPath
     */
    public static void emptyFolder(String folderPath) {

        if (folderPath == null)
            return;

        try {

            File f = new File(folderPath);

            if (f.exists()) {

                if (f.isDirectory()) {

                    if (f.listFiles().length == 0) {

                        f.delete();
                    } else {

                        File delFile[] = f.listFiles();

                        int i = f.listFiles().length;

                        for (int j = 0; j < i; j++) {

                            if (delFile[j].isDirectory())
                                emptyFolder(delFile[j].getAbsolutePath());

                            delFile[j].delete();
                        }
                    }
                } else {

                    throw new Exception("is not folder");
                }
            } else {

                throw new Exception("file is not exit");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 删除文件或文件夹
     *
     * @param filePath
     */
    public static void delFiles(String filePath) {

        if (filePath == null)
            return;

        try {

            File f = new File(filePath);

            if (f.exists()) {

                if (f.isDirectory()) {

                    if (f.listFiles().length == 0) {

                        f.delete();
                    } else {

                        emptyFolder(filePath);
                        f.delete();
                    }
                } else {

                    f.delete();
                }
            } else {

                throw new Exception("file is not exit");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     */
    public static void copyFile(String oldPath, String newPath) {

        try {

            if (new File(oldPath).exists()) {

                InputStream inStream = new FileInputStream(oldPath);
                FileOutputStream fs = new FileOutputStream(newPath);

                int byteread;
                byte[] buffer = new byte[1024 * 50];
                while ((byteread = inStream.read(buffer)) != -1) {

                    fs.write(buffer, 0, byteread);
                }

                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩文件
     *
     * @param filePath 待压缩的文件路径
     * @return 压缩后的文件
     */
    public static File zip(String filePath) {

        File target = null;
        File source = new File(filePath);

        if (source.exists()) {

            String zipName = source.getName() + ".zip";
            target = new File(source.getParent(), zipName);
            if (target.exists()) {

                target.delete();
            }

            FileOutputStream fos = null;
            ZipOutputStream zos = null;

            try {

                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));

                addEntry("/", source, zos);
            } catch (IOException e) {

                throw new RuntimeException(e);
            } finally {

                try {

                    zos.close();
                    fos.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

        return target;
    }

    /**
     * 解压文件
     *
     * @param filePath 压缩文件路径
     */
    public static void unzip(String filePath) {

        File source = new File(filePath);
        if (source.exists()) {

            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {

                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {

                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {

                        target.getParentFile().mkdirs();
                    }

                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {

                        bos.write(buffer, 0, read);
                    }

                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {

                throw new RuntimeException(e);
            } finally {

                try {

                    zis.close();
                    bos.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描添加文件Entry
     *
     * @param base   基路径
     * @param source 源文件
     * @param zos    Zip文件输出流
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {

        String entry = base + source.getName();
        if (source.isDirectory()) {

            for (File file : source.listFiles()) {

                addEntry(entry + "/", file, zos);
            }
        } else {

            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {

                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {

                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {

                try {

                    bis.close();
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
