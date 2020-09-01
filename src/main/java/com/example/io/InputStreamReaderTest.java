package com.example.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author vincent
 * 转换流：InputStreamReader 和 OutputStreamWriter（处理流）
 */
public class InputStreamReaderTest {

    @Test
    public void inputStreamReaderTest() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("inputStreamReader.txt"), StandardCharsets.UTF_8);
        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            String str = new String(chars, 0, len);
            System.out.println(str);
        }
    }

    @Test
    public void inputStreamReaderAndOutputStreamWriterTest() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("inputStreamReader.txt"), StandardCharsets.UTF_8);
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("outputStreamReader.txt"), "GBK");
        char[] chars = new char[1024];
        int len;
        while ((len = isr.read(chars)) != -1) {
            osw.write(chars, 0, len);
        }
        osw.close();
        isr.close();
    }
}








