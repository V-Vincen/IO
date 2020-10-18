package com.example.io.nodeandprocess_io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author vincent
 * 字节流：FileInputStream 和 FileOutputStream（节点流）
 */
public class InputAndOutPutStreamTest {

    @Test
    public void inputStream() throws IOException {
        //1. 造文件
        File file = new File("inputStream.txt");

        //2. 造流
        FileInputStream fis = new FileInputStream(file);

        //3. 读数据
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            String str = new String(bytes, 0, len);
            System.out.println(str);
        }

        //4. 关闭流
        fis.close();
    }

    /**
     * 图片复制
     */
    @Test
    public void inputStreamAndOutPutStream() throws IOException {
        FileInputStream fis = new FileInputStream("io.png");
        FileOutputStream fos = new FileOutputStream("io2.png");

        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        fos.close();
        fis.close();
    }

    private void copyFile(String srcPath, String destPath) throws IOException {
        FileInputStream fis = new FileInputStream(srcPath);
        FileOutputStream fos = new FileOutputStream(destPath);

        byte[] bytes = new byte[1024];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }

        fos.close();
        fis.close();
    }

    @Test
    public void copyFileTest() throws IOException {
        long start = System.currentTimeMillis();
        copyFile("copyFile.png", "copyFile2.png");
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 7 毫秒
    }
}













