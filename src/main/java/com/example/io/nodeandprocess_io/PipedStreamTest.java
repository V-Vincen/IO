package com.example.io.nodeandprocess_io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author vincent
 * 管道流：PipedInputStream 和 PipedOutputStream（节点流）
 */
public class PipedStreamTest {
    /**
     * 主入口方法.
     *
     * @param args 字符串数组参数
     */
    public static void main(String[] args) {
        PipedSender sender = new PipedSender(new PipedOutputStream());
        PipedReceiver receiver = new PipedReceiver(new PipedInputStream());

        try {
            // 将管道输入流和管道的输出流进行连接.
            receiver.getPipedIn().connect(sender.getPipedOut());

            // 启动线程
            sender.start();
            receiver.start();
        } catch (IOException e) {
            System.out.println("发送接收消息出错!");
        }
    }

    /**
     * 管道流：PipedInputStream
     */
    public static class PipedSender extends Thread {
        /**
         * 管道输出流对象，它和管道输入流（PipedInputStream）对象绑定。从而可以将数据发送给“管道输入流”。
         */
        private PipedOutputStream pipedOut;

        public PipedSender(PipedOutputStream pipedOut) {
            this.pipedOut = pipedOut;
        }

        public PipedOutputStream getPipedOut() {
            return pipedOut;
        }

        @Override
        public void run() {
            String strInfo = "Hello World!";
            try {
                pipedOut.write(strInfo.getBytes());
                pipedOut.close();
            } catch (IOException e) {
                System.out.println("向管道中写入数据出错!");
            }
        }
    }

    /**
     * 管道流：PipedOutputStream
     */
    public static class PipedReceiver extends Thread {
        /**
         * 管道输入流对象，它和管道输出流（PipedOutputStream）对象绑定。从而可以接收 “管道输出流” 的数据。
         */
        private PipedInputStream pipedIn;

        public PipedReceiver(PipedInputStream pipedIn) {
            this.pipedIn = pipedIn;
        }

        public PipedInputStream getPipedIn() {
            return pipedIn;
        }

        @Override
        public void run() {
            byte[] buf = new byte[2048];
            try {
                int len = pipedIn.read(buf);
                System.out.println(new String(buf, 0, len));
                pipedIn.close();
            } catch (IOException e) {
                System.out.println("从管道中读取数据出错!");
            }
        }
    }
}


