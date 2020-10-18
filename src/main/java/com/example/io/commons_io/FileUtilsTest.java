package com.example.io.commons_io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author vincent
 */
public class FileUtilsTest {

    @Test
    public void t() throws IOException {
        //获取指定文件
        File file = FileUtils.getFile("/Users/vincent/IDEA_Project/my_project/IO/a.txt");

        //获取指定目录下的："123.txt", "456.txt"
        File file1 = FileUtils.getFile(new File("/Users/vincent/IDEA_Project/my_project/IO/"), "123.txt", "456.txt");

        //获取临时目录 File 对象
        File tempDirectory = FileUtils.getTempDirectory();
        System.out.println(tempDirectory);
        //获取临时目录路径字符串
        String tempDirectoryPath = FileUtils.getTempDirectoryPath();
        System.out.println(tempDirectoryPath);

        //获取用户主目录 File 对象
        File userDirectory = FileUtils.getUserDirectory();
        System.out.println(userDirectory);
        //获取用户主目录路径字符串
        String userDirectoryPath = FileUtils.getUserDirectoryPath();
        System.out.println(userDirectoryPath);

        //以可读的方式，返回文件的大小EB, PB, TB, GB, MB, KB or bytes
        String byteCountToDisplaySize = FileUtils.byteCountToDisplaySize(10000000);
        System.out.println(byteCountToDisplaySize); // 9 MB
        String byteCountToDisplaySize1 = FileUtils.byteCountToDisplaySize(1);
        System.out.println(byteCountToDisplaySize1); // 1 bytes

        //读取文件大小 sizeOf：返回Long；sizeOfAsBigInteger：返回BigInteger
        Long lon = FileUtils.sizeOf(new File("/Users/vincent/IDEA_Project/my_project/IO"));
        System.out.println(lon);
        BigInteger bigInteger = FileUtils.sizeOfAsBigInteger(new File("/Users/vincent/IDEA_Project/my_project/IO"));
        System.out.println(bigInteger);

        //根据 URL 获取文件，new URL("file://Users/vincent/IDEA_Project/my_project/IO/touch")
        File file2 = FileUtils.toFile(new URL("file://Users/vincent/IDEA_Project/my_project/IO"));
        System.out.println(file2);

        //文件地址转为 URL
        URL[] urls = FileUtils.toURLs(new File("/Users/vincent/IDEA_Project/my_project/IO"));
        Arrays.stream(urls).forEach(System.out::println);

        //将输入流的内容复制到新文件
        FileUtils.copyInputStreamToFile(new FileInputStream("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/a.txt"),
                new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/a.txt"));

        //判断父目录是否包含子目录或者子文件
        boolean b = FileUtils.directoryContains(new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/"),
                new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/a1.txt"));
        System.out.println(b);

        //根据 extensions 拓展规则，搜索文件；recursive 是否递归，返回文件迭代器
        Iterator<File> iterateFiles = FileUtils.iterateFiles(new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils"), new String[]{"png", "txt"}, true);
        while (iterateFiles.hasNext()) {
            File next = iterateFiles.next();
            System.out.println(next);
        }

        //根据 extensions 拓展规则，搜索文件；recursive 是否递归，返回文件集合
        Collection<File> files = FileUtils.listFiles(new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils"), new String[]{"png", "txt"}, true);
        files.forEach(System.out::println);


        //根据 FileFileFilter 自定义文件过滤器，和 DirectoryFileFilter 自定义目录过滤器，搜索文件和目录，返回文件和目录集合
        Collection<File> listFilesAndDirs = FileUtils.listFilesAndDirs(new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils"),
                new FileFileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.getPath().endsWith("png");
                    }
                }, DirectoryFileFilter.DIRECTORY);
        listFilesAndDirs.forEach(System.out::println);
    }

    @Test
    public void createFileOrDirectory() throws IOException {
        /**
         * 	touch(File file)：创建文件，如果文件存在则更新时间；如果不存在，创建一个空文件。
         * 	forceMkdir(File directory)：强制创建文件目录，如果文件目录存在，会抛出异常。
         */
        FileUtils.touch(new File("/Users/vincent/IDEA_Project/my_project/IO/touch.txt"));
        FileUtils.forceMkdir(new File("/Users/vincent/IDEA_Project/my_project/IO/touch"));
    }

    @Test
    public void copyFile() throws IOException {
        /**
         * copyFile(File srcFile, File destFile)：此方法将目录 A 下的 abc.txt 拷贝到目录 B 下的 abc.txt；如果有同名文件则替换，没有则新建。（如果前者文件不存在则报错）。
         */
        File srcFile = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/abc.txt");
        File destFile = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy/abc/abc.txt");
        FileUtils.copyFile(srcFile, destFile);
    }

    @Test
    public void copyFileToDirectory() throws IOException {
        /**
         * copyFileToDirectory(File srcFile, File destDir)：此方法将目录 A 下的 abc.txt 拷贝到指定目录下，如果有同名文件则替换，没有则新建。（如果前者文件不存在则报错）。
         */
        File srcFile = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/abc.txt");
        File destDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy");
        FileUtils.copyFileToDirectory(srcFile, destDir);
    }

    @Test
    public void copyDirectory() throws IOException {
        /**
         * copyDirectory(File srcDir, File destDir)：复制文件夹（文件夹里面的文件内容也会复制）。
         */
        File srcDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils");
        File destDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy");
        FileUtils.copyDirectory(srcDir, destDir);


        /**
         * 带过滤器的复制文件夹
         * copyDirectory(File srcDir, File destDir, FileFilter filter)：
         * FileFilter：DirectoryFileFilter.DIRECTORY，只复制文件目录。
         */
        File destDir2 = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy2");
        FileUtils.copyDirectory(srcDir, destDir2, DirectoryFileFilter.DIRECTORY);
    }

    @Test
    public void copyDirectoryToDirectory() throws IOException {
        /**
         * copyDirectoryToDirectory(File sourceDir, File destinationDir)：
         * 此方法将目录 A 下的所有的文件夹及文件（包含目录 A），复制到目录 B 下；
         * 如果目录 B 下也有一个目录 A，则两个目录合并，
         * 如果目录 A 下有文件 a，同时目录 B 下的目录 A 中，也有一个文件 a，则目录 A 下的文件 a 替换目录 B 下的目录 A 下的文件 a。
         * 总结：如果有同名目录则合并，如果有同名文件则替换。（如果前者目录不存在则报错，如果后者目录不存在则新建）
         * 例子（结构示意图）：
         * A (srcDir)
         * |
         * |--   a.txt (内容：aaa)
         * |-- abc.txt (内容：abc)
         *
         * B (destDir):
         * |
         * |-- A (dir)
         *     |
         *     |-- a.txt (内容：vvv)
         *
         * copyDirectoryToDirectory(srcDir, destDir)：
         * B (destDir)
         * |
         * |-- A (dir)
         *     |
         *     |--   a.txt (内容：aaa)
         *     |-- abc.txt (内容：abc)
         */
        File srcDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc");
        File destDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy");
        FileUtils.copyDirectoryToDirectory(srcDir, destDir);
    }

    @Test
    public void moveDirectory() throws IOException {
        /**
         * 	moveDirectory(File srcDir, File destDir)：移动目录（复制并删除），目标目录与原目录中如果存在同名目录会报错（也可重命名）。
         * 	moveDirectoryToDirectory(File src, File destDir, boolean createDestDir)：移动目录到目标目录下；createDestDir 目录不存在是否创建。
         * 	moveFile(File srcFile, File destFile)：移动文件（复制并删除），目标目录下存在文件则报错（也可重命名）。
         * 	moveFileToDirectory(File srcFile, File destDir, boolean createDestDir)：移动文件到目标目录下；createDestDir 目录不存在是否创建。
         */
        File srcDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abcd");
        File destDir = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils2");
        FileUtils.moveDirectory(srcDir, destDir);
        FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);

        File srcFile = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abcd/a.txt");
        File destFile = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abcd/ab.txt");
        FileUtils.moveFile(srcFile, destFile);
        FileUtils.moveFileToDirectory(srcFile, destDir, false);
    }

    @Test
    public void openInputAndOutStream() throws IOException {
        /**
         * openInputStream(File file)：获取文件输入流。
         * openOutputStream(File file)：获取文件输出流。
         */
        InputStream in = FileUtils.openInputStream(new File("/Users/vincent/IDEA_Project/my_project/IO/inputStream.txt"));

        File file = new File("/Users/vincent/IDEA_Project/my_project/IO/outputStream.txt");
        OutputStream os = FileUtils.openOutputStream(file);
        os = FileUtils.openOutputStream(file, true);//是否追加的形式添加内容
    }

    @Test
    public void deleteDirectoryAndQuietly() throws IOException {
        /**
         * deleteDirectory(File directory)：删除目录或文件，无法删除会抛异常。
         * deleteQuietly(File file)：安静删除目录或文件，无法删除时也不会抛异常。
         * cleanDirectory(File directory)：删除该目录下的所有内容（保留该目录）。
         * forceDelete(File file)：强制删除目录或文件。
         * forceDeleteOnExit(File file)：当 JVM 退出时，删除目录或文件。
         */
        FileUtils.deleteDirectory(new File("/Users/vincent/IDEA_Project/my_project/IO/dir"));
        FileUtils.deleteQuietly(new File("/Users/vincent/IDEA_Project/my_project/IO/dir2"));
        FileUtils.cleanDirectory(new File("/Users/vincent/IDEA_Project/my_project/IO/fileutilscopy"));
        FileUtils.forceDelete(new File("/Users/vincent/IDEA_Project/my_project/IO/readerAndWriter2.txt"));
        FileUtils.forceDeleteOnExit(new File("/Users/vincent/IDEA_Project/my_project/IO/readerAndWriter3.txt"));
    }

    @Test
    public void readXxx() throws IOException {
        /**
         * readFileToByteArray(File file)：读取目标文件，返回文件内容的 bytes 数组。
         * readFileToString(File file)：读取目标文件，返回文件内容字符串（Deprecated）。
         * readFileToString(File file, Charset charsetName)：读取目标文件，返回文件内容字符串（确认字符集）。
         * readFileToString(File file, String charsetName)：读取目标文件，返回文件内容字符串（确认字符集）。
         * readLines(File file)：读取目标文件每一行数据，返回 list。
         * readLines(File file, Charset charset)：读取目标文件每一行数据，返回 list（确认字符集）。
         * readLines(File file, String charsetName)：：读取目标文件每一行数据，返回 list（确认字符集）。
         * lineIterator(File file)：读取目标文件每一行数据，返回迭代器。
         * lineIterator(File file, String charsetName)：读取目标文件每一行数据，返回迭代器（确认字符集）。
         */
        File file = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/a.txt");
        byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
        System.out.println(new String(readFileToByteArray));
        System.out.println();

        String readFileToString = FileUtils.readFileToString(file, "UTF-8");
        System.out.println(readFileToString);
        String readFileToStringCharset = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        System.out.println(readFileToStringCharset);
        System.out.println();

        List<String> readLines = FileUtils.readLines(file, "UTF-8");
        readLines.forEach(System.out::println);
        List<String> readLinesCharset = FileUtils.readLines(file, StandardCharsets.UTF_8);
        readLinesCharset.forEach(System.out::println);
        System.out.println();

        LineIterator lineIterator = FileUtils.lineIterator(file);
        while (lineIterator.hasNext()) {
            String next = lineIterator.next();
            System.out.println(next);
        }
        LineIterator lineIteratorCharset = FileUtils.lineIterator(file, "UTF-8");
        while (lineIteratorCharset.hasNext()) {
            String next = lineIteratorCharset.next();
            System.out.println(next);
        }
    }

    @Test
    public void writeXxx() throws IOException {
        /**
         * CharSequence 接口：String、StringBuilder、StringBuffer 都是实现了它。
         * write(File file, CharSequence data)：将字符串写入目标文件（Deprecated）。
         * write(File file, CharSequence data, boolean append)：将字符串写入目标文件；append 为 true 时追加，false 时为覆盖（Deprecated）。
         * write(File file, CharSequence data, Charset charset)：将字符串写入目标文件，并设置字符集。
         * write(File file, CharSequence data, Charset charset, boolean append)：将字符串写入目标文件，并设置字符集；append 为 true 时追加，false 时为覆盖。
         * write(File file, CharSequence data, String charsetName)：将字符串写入目标文件，并设置字符集。
         * write(File file, CharSequence data, String charsetName, boolean append)：将字符串写入目标文件，并设置字符集；append 为 true 时追加，false 时为覆盖。
         * writeByteArrayToFile(File file, byte[] data)：将文件内容以 bytes 数组写入文件。
         * writeByteArrayToFile(File file, byte[] data, boolean append)：将文件内容以 bytes 数组写入文件；append 为 true 时追加，false 时为覆盖。
         * writeByteArrayToFile(File file, byte[] data, int off, int len)：将文件内容以 bytes 数组写入文件。
         * writeByteArrayToFile(File file, byte[] data, int off, int len, boolean append)：将文件内容以 bytes 数组写入文件；append 为 true 时追加，false 时为覆盖。
         * writeLines(File file, Collection<?> lines)：将集合中的数据逐行写入文件中。
         * writeLines(File file, Collection<?> lines, boolean append)：将集合中的数据逐行写入文件中；append 为 true 时追加，false 时为覆盖。
         * writeLines(File file, Collection<?> lines, String lineEnding)：将集合中的数据逐行写入文件中，并设置行与行直接的分隔符。
         * writeLines(File file, Collection<?> lines, String lineEnding, boolean append)：将集合中的数据逐行写入文件中，并设置行与行直接的分隔符；append 为 true 时追加，false 时为覆盖。
         * writeLines(File file, String charsetName, Collection<?> lines)：将集合中的数据逐行写入文件中，并设置字符集。
         * writeLines(File file, String charsetName, Collection<?> lines, boolean append)：将集合中的数据逐行写入文件中，并设置字符集；append 为 true 时追加，false 时为覆盖。
         * writeLines(File file, String charsetName, Collection<?> lines, String lineEnding)：将集合中的数据逐行写入文件中，并设置字符集，并设置行与行直接的分隔符。
         * writeLines(File file, String charsetName, Collection<?> lines, String lineEnding, boolean append)：将集合中的数据逐行写入文件中，并设置字符集，并设置行与行直接的分隔符；append 为 true 时追加，false 时为覆盖。
         * writeStringToFile(File file, String data)：将字符串写入文件（Deprecated）。
         * writeStringToFile(File file, String data, boolean append)：将字符串写入文件；append 为 true 时追加，false 时为覆盖（Deprecated）。
         * writeStringToFile(File file, String data, Charset charset)：将字符串写入文件，并设置字符集。
         * writeStringToFile(File file, String data, Charset charset, boolean append)：将字符串写入文件，并设置字符集；append 为 true 时追加，false 时为覆盖。
         * writeStringToFile(File file, String data, String charsetName)：将字符串写入文件，并设置字符集。
         * writeStringToFile(File file, String data, String charsetName, boolean append)：将字符串写入文件，并设置字符集；append 为 true 时追加，false 时为覆盖。
         */
        File file = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/abc.txt");
        FileUtils.write(file, "write(File file, CharSequence data)\n");
        FileUtils.write(file, "write(File file, CharSequence data, boolean append)\n", true);
        FileUtils.write(file, "write(File file, CharSequence data, Charset charset)\n", StandardCharsets.UTF_8);
        FileUtils.write(file, "write(File file, CharSequence data, Charset charset, boolean append)\n", StandardCharsets.UTF_8, true);
        FileUtils.write(file, "(File file, CharSequence data, String charsetName)\n", "UTF-8");
        FileUtils.write(file, "write(File file, CharSequence data, String charsetName, boolean append)\n", "UTF-8", true);

        File file2 = new File("/Users/vincent/IDEA_Project/my_project/IO/fileutils/abc/abcd.txt");
        byte[] readFileToByteArray = FileUtils.readFileToByteArray(file2);
        FileUtils.writeByteArrayToFile(file, readFileToByteArray);
        FileUtils.writeByteArrayToFile(file, readFileToByteArray, true);
        FileUtils.writeByteArrayToFile(file, readFileToByteArray, 0, new String(readFileToByteArray).length());
        FileUtils.writeByteArrayToFile(file, readFileToByteArray, 0, new String(readFileToByteArray).length(), true);

        List<String> readLines = FileUtils.readLines(file2, StandardCharsets.UTF_8);
        FileUtils.writeLines(file, readLines);
        FileUtils.writeLines(file, readLines, true);
        FileUtils.writeLines(file, readLines, "\n");
        FileUtils.writeLines(file, readLines, "\n", true);
        FileUtils.writeLines(file, "UTF-8", readLines);
        FileUtils.writeLines(file, "UTF-8", readLines, true);
        FileUtils.writeLines(file, "UTF-8", readLines, "\n");
        FileUtils.writeLines(file, "UTF-8", readLines, "\n", true);

        String readFileToString = FileUtils.readFileToString(file2, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(file, readFileToString);
        FileUtils.writeStringToFile(file, readFileToString, true);
        FileUtils.writeStringToFile(file, readFileToString, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(file, readFileToString, StandardCharsets.UTF_8, true);
        FileUtils.writeStringToFile(file, readFileToString, "UTF-8");
        FileUtils.writeStringToFile(file, readFileToString, "UTF-8", true);
    }

    @Test
    public void isFileNewerOrOld() {
        /**
         * isFileNewer(File file, Date date)：比较指定的文件是否比指定的日期新。
         * isFileNewer(File file, File reference)：比较前一个文件是否比后一个文件新。
         * isFileNewer(File file, Instant instant)：比较指定的文件是否比指定的瞬间更新。
         * isFileNewer(File file, long timeMillis)：比较指定的文件是否比指定的时间引用新。
         * isFileOlder(File file, Date date)：比较指定的文件是否比指定的日期旧。
         * isFileOlder(File file, File reference)：比较前一个文件是否比后一个文件旧。
         * isFileOlder(File file, Instant instant)：比较指定的文件是否比指定的瞬间更旧。
         * isFileOlder(File file, long timeMillis)：比较指定的文件是否比指定的时间引用旧。
         */
        File file1 = new File("/Users/vincent/IDEA_Project/my_project/IO/copyFile.png");
        File file2 = new File("/Users/vincent/IDEA_Project/my_project/IO/new.txt");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.SEPTEMBER, 11);
        Date time = calendar.getTime();
        boolean fileNewer = FileUtils.isFileNewer(file2, time);
        System.out.println(fileNewer);

        boolean fileNewer2 = FileUtils.isFileNewer(file1, file2);
        System.out.println(fileNewer2);
        boolean fileNewer3 = FileUtils.isFileNewer(file2, file1);
        System.out.println(fileNewer3);

        boolean fileNewer4 = FileUtils.isFileNewer(file2, System.currentTimeMillis());
        System.out.println(fileNewer4);
        System.out.println();


        boolean fileOlder = FileUtils.isFileOlder(file2, time);
        System.out.println(fileOlder);

        boolean fileOlder2 = FileUtils.isFileOlder(file1, file2);
        System.out.println(fileOlder2);
        boolean fileOlder3 = FileUtils.isFileOlder(file2, file1);
        System.out.println(fileOlder3);

        boolean fileOlder4 = FileUtils.isFileOlder(file2, System.currentTimeMillis());
        System.out.println(fileOlder4);
    }
}






