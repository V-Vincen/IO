package com.example.io;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * @author vincent
 * 字符流：FileReader 和 FileWriter（节点流）
 */
public class ReaderAndWriterTest {

    @Test
    public void reader() {
        //1. 实例 File 类的对象，指明要操作的文件
        File file = new File("reader.txt");

        //2. 提供 FileReader 的对象，用于数据的输入
        FileReader fr = null;
        try {
            fr = new FileReader(file);

            //3. 数据读入
            int data;
            while ((data = fr.read()) != -1) {
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();

            //4. 流的关闭
            try {
                if (Objects.nonNull(fr)) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void reader2() throws IOException {
        File file = new File("reader.txt");
        FileReader fr = new FileReader(file);

        char[] chars = new char[5];
        int len;
        while ((len = fr.read(chars)) != -1) {
//            for (int i = 0; i < len; i++) {
//                System.out.print(chars[i]);
//            }
//            System.out.println();

            String str = new String(chars, 0, len);
            System.out.println(str);
        }
        fr.close();
    }

    @Test
    public void writer() throws IOException {
        //1. 实例 File 类的对象，指明要写出的文件
        File file = new File("writer.txt");

        //2. 提供 FileWriter 的对象，用于数据的输出
        //覆盖
        FileWriter fw = new FileWriter(file);
        //在原有文件的基础上追加
//        FileWriter fw = new FileWriter(file, true);

        //3. 数据读入
        fw.write("I have a dream!!!\n");
        fw.write("You need to have a dream!!!");

        //4. 流的关闭
        fw.close();
    }

    @Test
    public void readerAndWriter() throws IOException {
        FileReader fr = new FileReader("reader.txt");
        FileWriter fw = new FileWriter("readerAndWriter.txt");

        char[] chars = new char[1024];
        int len;
        while ((len = fr.read(chars)) != -1) {
            fw.write(chars, 0, len);
        }

        fw.close();
        fr.close();
    }
}










