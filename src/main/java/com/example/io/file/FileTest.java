package com.example.io.file;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author vincent
 */
public class FileTest {
    /**
     * 1. public File(String pathname)：以 pathname 为路径创建 File 对象，可以是绝对路径或者相对路径，如果 pathname 是相对路径，则默认的当前路径在系统属性 user.dir 中存储。
     * 绝对路径：是一个固定的路径,从盘符开始
     * 相对路径：是相对于某个位置开始
     * 2. public File(String parent,String child)：以 parent 为父路径，child 为子路径创建 File 对象。
     * 3. public File(File parent,String child)：根据一个父 File 对象和子文件路径创建 File 对象。
     */
    @Test
    public void t() throws IOException {
        File dir = new File("/Users/vincent/IDEA_Project/my_project/IO/dir");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File dir2 = new File("dir2");
        if (!dir2.exists()) {
            dir2.mkdirs();
        }

        File dir3 = new File("dir3", "dir3.1");
        if (!dir3.exists()) {
            dir3.mkdirs();
        }

        File dir2_1 = new File(dir2, "dir2.1");
        if (!dir2_1.exists()) {
            dir2_1.mkdirs();
        }

        File file = new File(dir, "a.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Test
    public void t2() {
        File file = new File("/Users/vincent/IDEA_Project/my_project/IO", "a.txt");
        if (file.exists()) {
            //继续文件的操作
        } else {
            try {
                boolean result = file.createNewFile();
                System.out.println(result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void t3() {
        File file = new File("/Users/vincent/IDEA_Project/my_project/IO");
        File[] files = file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        Arrays.stream(files).forEach(System.out::println);
    }
}










