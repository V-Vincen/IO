package com.example.io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author vincent
 * 缓冲流：（处理流）提高写入写出的速度
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 */
public class BufferedTest {

    @Test
    public void bufferedStreamTest() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("io.png"));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("io3.png"));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        bos.close();
        bis.close();
    }

    private void bufferCopyFile(String srcPath, String destPath) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcPath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destPath));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }

        bos.close();
        bis.close();
    }

    @Test
    public void bufferCopyFileTest() throws IOException {
        long start = System.currentTimeMillis();
        bufferCopyFile("copyFile.png", "copyFile3.png");
        long end = System.currentTimeMillis();
        System.out.println(end - start);// 3 毫秒
    }

    @Test
    public void bufferedReaderAndWriter() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("readerAndWriter.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("readerAndWriter2.txt"));

        char[] chars = new char[1024];
        int len;
        while ((len = br.read(chars)) != -1) {
            bw.write(chars, 0, len);
        }

        bw.close();
        br.close();
    }

    @Test
    public void bufferedReaderAndWriter2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("readerAndWriter.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("readerAndWriter3.txt"));

        String data;
        while ((data = br.readLine()) != null) {
//            bw.write(data + "\n");
            bw.write(data);//dat 中不包含换行符
            bw.newLine();//提供换行的操作
        }

        bw.close();
        br.close();
    }
}
