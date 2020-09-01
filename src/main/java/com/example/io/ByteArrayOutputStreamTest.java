package com.example.io;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author vincent
 */
public class ByteArrayOutputStreamTest {

    /**
     * 测试将内容写入到 ByteArrayOutputStream 中并打印出来，不需要关闭流.
     */
    @Test
    public void byteArrayStreamTest() {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream(8);
        String str = "Hello World!";
        try {
            byteOut.write(str.getBytes());
        } catch (IOException e) {
            System.out.println("写入字节数据出错!");
        }

        byte[] buf = byteOut.toByteArray();
        for (byte b : buf) {
            System.out.print((char) b);
        }
    }
}
