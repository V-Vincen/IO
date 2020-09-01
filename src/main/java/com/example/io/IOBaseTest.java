package com.example.io;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author vincent
 */
public class IOBaseTest {

    @Test
    public void t() {
        String str = "Hello_安卓";
        int byteLen = 0;
        try {
            byteLen = str.getBytes("gbk").length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("字节长度：" + byteLen);
    }

    @Test
    public void t2() {
        String str = "Hello_安卓";
        int byteLen = str.getBytes().length;
        System.out.println("字节长度：" + byteLen);
    }

}
