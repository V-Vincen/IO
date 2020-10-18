package com.example.io.commons_io;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
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
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author vincent
 */
public class IOUtilsTest {

    @Test
    public void Buffer() throws IOException {
        /**
         * buffer 方法创建 Buffered Stream、Buffered Reader、Buffered Writer。
         * buffer(InputStream inputStream)：接受 InputStream 并返回 BufferedInputStream。
         * buffer(OutputStream outputStream)：接受 OutputStream 并返回缓冲的 OutputStream。
         * buffer(Reader reader)：获取 Reader 并返回 BufferedReader。
         * buffer(Writer writer)：接受 Writer 并返回 BufferedWriter。
         */
        //InputStream
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        BufferedInputStream bufferedInputStream = IOUtils.buffer(inputStream);

        //We can test the contents by printing it as a String as shown below
        System.out.println(IOUtils.toString(bufferedInputStream, StandardCharsets.UTF_8));

        //Reader
        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        BufferedReader bufferedReader = IOUtils.buffer(reader);


        //OutputStream
        OutputStream outputStream = new FileOutputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        BufferedOutputStream bufferedOutputStream = IOUtils.buffer(outputStream);


        //Writer
        Writer writer = new FileWriter("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        BufferedWriter bufferedWriter = IOUtils.buffer(writer);
        //Can write into the output stream
        bufferedWriter.write('a');
        bufferedWriter.close();
    }

    @Test
    public void copyAndCopyLarge() throws IOException {
        /**
         * copy：内部使用的其实还是 copyLarge 方法。因为 copy 能拷贝 Integer.MAX_VALUE 的字节数据，即 2^31-1。
         * copyLarge：这个方法适合拷贝较大的数据流，比如2G以上。
         *
         * copy(InputStream input, OutputStream output)
         * copy(InputStream input, Writer output)
         * copy(InputStream input, Writer output, Charset inputCharset)
         * copy(InputStream input, Writer output, String inputCharsetName)
         * copy(Reader input, OutputStream output)
         * copy(Reader input, OutputStream output, Charset outputCharset)
         * copy(Reader input, OutputStream output, String outputCharsetName)
         * copy(Reader input, Writer output)
         * copyLarge(InputStream input, OutputStream output)
         * copyLarge(InputStream input, OutputStream output, byte[] buffer)
         * copyLarge(InputStream input, OutputStream output, long inputOffset, long length)
         * copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte[] buffer)
         * copyLarge(Reader input, Writer output)
         * copyLarge(Reader input, Writer output, char[] buffer)
         * copyLarge(Reader input, Writer output, long inputOffset, long length)
         * copyLarge(Reader input, Writer output, long inputOffset, long length, char[] buffer)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        OutputStream outputStream = new FileOutputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-output-file.txt");
        int numberOfBytesCopied = IOUtils.copy(inputStream, outputStream);
        System.out.println(numberOfBytesCopied);//30
        outputStream.close();

        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        Writer writer = new FileWriter("/Users/vincent/IDEA_Project/my_project/IO/sample-output-file.txt", false);//boolean append：是否继续追加
        int numberOfCharsRead = IOUtils.copy(reader, writer);
        System.out.println(numberOfCharsRead);//30
        writer.close();

        long numberOfBytesCopied2 = IOUtils.copyLarge(inputStream, outputStream);
        System.out.println(numberOfBytesCopied2); //30
        outputStream.close();
        long numberOfCharsRead2 = IOUtils.copyLarge(reader, writer);
        System.out.println(numberOfCharsRead2); //30
        writer.close();
    }

    @Test
    public void readXxx() throws IOException {
        /**
         * read：从一个流中读取内容。
         * readFully：这个方法会读取指定长度的流，如果读取的长度不够，就会抛出异常。
         * readLines：可以从流中读取内容，并转换为 List<String> 集合。
         *
         * read(InputStream input, byte[] buffer)
         * read(InputStream input, byte[] buffer, int offset, int length)
         * read(Reader input, char[] buffer)
         * read(Reader input, char[] buffer, int offset, int length)
         * readFully(InputStream input, byte[] buffer)
         * readFully(InputStream input, byte[] buffer, int offset, int length)
         * readFully(Reader input, char[] buffer)
         * readFully(Reader input, char[] buffer, int offset, int length)
         * readLines(InputStream input)
         * readLines(InputStream input, Charset charset)
         * readLines(InputStream input, String charsetName)
         * readLines(Reader input)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        byte[] bytes = new byte[7]; //Creating a byte array of size 7.
        int numberOfBytesRead = IOUtils.read(inputStream, bytes);
        System.out.println(numberOfBytesRead);//7
        System.out.println(new String(bytes)); //1-Will-
        System.out.println();

        byte[] bytes2 = new byte[7]; //Creating a byte array of size 7.
        int numberOfBytesRead2 = IOUtils.read(inputStream, bytes, 1, 5);
        System.out.println(numberOfBytesRead2);//5
        System.out.println(new String(bytes2)); //1-Wil
        System.out.println();


        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        char[] chars = new char[7];
        int numberOfCharsRead = IOUtils.read(reader, chars);
        System.out.println(numberOfCharsRead);//5
        System.out.println(new String(chars)); //1-Wil
        System.out.println();

        char[] chars2 = new char[7];
        int numberOfCharsRead2 = IOUtils.read(reader, chars, 0, 5);
        System.out.println(numberOfCharsRead2);//5
        System.out.println(new String(chars2)); //1-Wil
        System.out.println();


        byte[] bytesReadFully = new byte[4];
        //但是如果读取20个byte，就会出错了。java.io.EOFException: Length to read: 20 actual: 11
//        byte[] bytesReadFully = new byte[20];
        InputStream is = IOUtils.toInputStream("hello world", StandardCharsets.UTF_8);
        IOUtils.readFully(is, bytesReadFully);
        System.out.println(new String(bytesReadFully));//hell
        System.out.println();


        InputStream inputStreamReadLins = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        List<String> lines = IOUtils.readLines(inputStreamReadLins, StandardCharsets.UTF_8);
        lines.forEach(System.out::println);//1-Will-90 2-James-87 3-Kyle-67
    }

    @Test
    public void skipAndSkipFully() throws IOException {
        /**
         * skip：跳过指定长度的流。
         * skipFully：这个方法类似 skip，只是如果忽略的长度大于现有的长度，就会抛出异常。
         *
         * skip(InputStream input, long toSkip)
         * skip(ReadableByteChannel input, long toSkip)
         * skip(Reader input, long toSkip)
         * skipFully(InputStream input, long toSkip)
         * skipFully(ReadableByteChannel input, long toSkip)
         * skipFully(Reader input, long toSkip)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        long numBytesSkipped = IOUtils.skip(inputStream, 5);
        System.out.println(numBytesSkipped);//5
        System.out.println(IOUtils.toString(inputStream, StandardCharsets.UTF_8));//l-90 2-James-87 3-Kyle-67

        InputStream inputStreamSkipFully = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        IOUtils.skipFully(inputStream, 50);
        System.out.println(IOUtils.toString(inputStreamSkipFully, StandardCharsets.UTF_8));//java.io.EOFException: Bytes to skip: 50 actual: 0
    }

    @Test
    public void toByteArray() throws IOException {
        /**
         * toByteArray：返回字节数组。
         *
         * toByteArray(InputStream input)
         * toByteArray(InputStream input, int size)
         * toByteArray(InputStream input, long size)
         * toByteArray(Reader input)
         * toByteArray(Reader input, Charset charset)
         * toByteArray(Reader input, String charsetName)
         * toByteArray(String input)
         * toByteArray(URI uri)
         * toByteArray(URL url)
         * toByteArray(URLConnection urlConn)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        byte[] bytes = IOUtils.toByteArray(inputStream);
        System.out.println(new String(bytes));//1-Will-90 2-James-87 3-Kyle-67
        System.out.println();

        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        byte[] bytes2 = IOUtils.toByteArray(reader, StandardCharsets.UTF_8);
        System.out.println(new String(bytes2));//1-Will-90 2-James-87 3-Kyle-67
    }

    @Test
    public void toCharArray() throws IOException {
        /**
         * toCharArray：返回字符数组。
         *
         * toCharArray(InputStream is)
         * toCharArray(InputStream is, Charset charset)
         * toCharArray(InputStream is, String charsetName)
         * toCharArray(Reader input)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        char[] chars = IOUtils.toCharArray(inputStream, StandardCharsets.UTF_8);
        System.out.println(new String(chars));//1-Will-90 2-James-87 3-Kyle-67
        System.out.println();

        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        char[] chars2 = IOUtils.toCharArray(reader);
        System.out.println(new String(chars2));//1-Will-90 2-James-87 3-Kyle-67
    }

    @Test
    public void toInputStream() throws IOException {
        /**
         * toInputStream：返回字节输入流。
         *
         * toInputStream(CharSequence input)
         * toInputStream(CharSequence input, Charset charset)
         * toInputStream(CharSequence input, String charsetName)
         * toInputStream(String input)
         * toInputStream(String input, Charset charset)
         * toInputStream(String input, String charsetName)
         */
        InputStream generatedInputStream = IOUtils.toInputStream("Vincent is so handsome!!!", StandardCharsets.UTF_8);
        System.out.println(IOUtils.toString(generatedInputStream, StandardCharsets.UTF_8));
    }

    @Test
    public void staticToString() throws IOException {
        /**
         * toString：返回字符串。
         *
         * toString(byte[] input)
         * toString(byte[] input, String charsetName)
         * toString(InputStream input)
         * toString(InputStream input, Charset charset)
         * toString(InputStream input, String charsetName)
         * toString(Reader input)
         * toString(URI uri)
         * toString(URI uri, Charset encoding)
         * toString(URI uri, String charsetName)
         * toString(URL url)
         * toString(URL url, Charset encoding)
         * toString(URL url, String charsetName)
         */
        InputStream inputStream = new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        System.out.println(IOUtils.toString(inputStream, StandardCharsets.UTF_8));//1-Will-90 2-James-87 3-Kyle-67

        Reader reader = new FileReader("/Users/vincent/IDEA_Project/my_project/IO/sample-file.txt");
        System.out.println(IOUtils.toString(reader));//1-Will-90 2-James-87 3-Kyle-67
    }

    @Test
    public void writerXxx() throws IOException {
        /**
         * write：把数据写入到输出流中。
         * writeChunked：把字节或者字符数组，用分块写入的方式，写入到另一个输出流。
         * writeLines：把 List<string> 的集合写入到输出流中。
         *
         * write(byte[] data, OutputStream output)
         * write(byte[] data, Writer output)
         * write(byte[] data, Writer output, Charset charset)
         * write(byte[] data, Writer output, String charsetName)
         * write(char[] data, OutputStream output)
         * write(char[] data, OutputStream output, Charset charset)
         * write(char[] data, OutputStream output, String charsetName)
         * write(char[] data, Writer output)
         * write(CharSequence data, OutputStream output)
         * write(CharSequence data, OutputStream output, Charset charset)
         * write(CharSequence data, OutputStream output, String charsetName)
         * write(CharSequence data, Writer output)
         * write(StringBuffer data, OutputStream output)
         * write(StringBuffer data, OutputStream output, String charsetName)
         * write(StringBuffer data, Writer output)
         * write(String data, OutputStream output)
         * write(String data, OutputStream output, Charset charset)
         * write(String data, OutputStream output, String charsetName)
         * write(String data, Writer output)
         * writeChunked(byte[] data, OutputStream output)
         * writeChunked(char[] data, Writer output)
         * writeLines(Collection<?> lines, String lineEnding, OutputStream output)
         * writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset charset)
         * writeLines(Collection<?> lines, String lineEnding, OutputStream output, String charsetName)
         * writeLines(Collection<?> lines, String lineEnding, Writer writer)
         */
        OutputStream outputStream = new FileOutputStream("/Users/vincent/IDEA_Project/my_project/IO/sample-output-file.txt");
        IOUtils.write("data1", outputStream, StandardCharsets.UTF_8);
        IOUtils.write(System.lineSeparator(), outputStream, StandardCharsets.UTF_8);
        IOUtils.write("data2", outputStream, StandardCharsets.UTF_8);
        outputStream.close();

        Writer writer = new FileWriter("/Users/vincent/IDEA_Project/my_project/IO/sample-output-file.txt");
        IOUtils.write("data3", writer);
        IOUtils.write(System.lineSeparator(), writer);
        IOUtils.write("data4", writer);
        IOUtils.write(System.lineSeparator(), writer);


        List<String> list = Lists.newArrayList("hello", "list", "to", "file");
        IOUtils.writeLines(list, IOUtils.LINE_SEPARATOR, writer);
        writer.close();
    }
}
