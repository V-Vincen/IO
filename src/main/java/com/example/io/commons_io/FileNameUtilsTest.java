package com.example.io.commons_io;

import com.google.common.collect.Lists;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author vincent
 */
public class FileNameUtilsTest {

    @Test
    public void t() throws IOException {
        String fileDirectory = "/Users/vincent/IDEA_Project/my_project/IO/filenameutils";
        String fileName = "abc.txt";
        String fileFullName = fileDirectory + "/" + fileName;

        //此方法将路径规范化为标准格式
        String noEndSeparator = FilenameUtils.normalizeNoEndSeparator(fileFullName);
        System.out.println("(1)" + noEndSeparator);

        //合并目录和文件名为文件全路径
        String concat = FilenameUtils.concat(fileDirectory, fileFullName);
        System.out.println("(2)" + concat);

        //文件路径去除目录和后缀后的文件名
        String baseName = FilenameUtils.getBaseName(fileFullName);
        System.out.println("(3)" + baseName);

        //获取文件的后缀
        String extension = FilenameUtils.getExtension(fileFullName);
        System.out.println("(4)" + extension);

        //获取文件的完整目录
        String fullPath = FilenameUtils.getFullPath(fileFullName);
        System.out.println("(5)" + fullPath);

        //获取文件的完整目录不包含结束符
        String fullPathNoEndSeparator = FilenameUtils.getFullPathNoEndSeparator(fileFullName);
        System.out.println("(6)" + fullPathNoEndSeparator);

        //获取文件完整的名称，包含后缀
        String name = FilenameUtils.getName(fileFullName);
        System.out.println("(7)" + name);

        //获取文件的路径，去除前缀的路径
        String path = FilenameUtils.getPath(fileFullName);
        System.out.println("(8)" + path);

        //获取文件的路径，去除前缀和结尾的分隔符
        String pathNoEndSeparator = FilenameUtils.getPathNoEndSeparator(fileFullName);
        System.out.println("(9)" + pathNoEndSeparator);

        //获取路径前缀
        String prefix = FilenameUtils.getPrefix(fileFullName);
        System.out.println("(10)" + prefix);

        //获取前缀长度
        int prefixLength = FilenameUtils.getPrefixLength(fileFullName);
        System.out.println("(11)" + prefixLength);

        //获取最后一个 '.' 的位置
        int indexOfExtension = FilenameUtils.indexOfExtension(fileFullName);
        System.out.println("(12)" + indexOfExtension);

        //获取最后一个 '/' 的位置
        int indexOfLastSeparator = FilenameUtils.indexOfLastSeparator(fileFullName);
        System.out.println("(13)" + indexOfLastSeparator);

        //获取当前系统格式化路径
        String normalize = FilenameUtils.normalize(fileFullName);
        System.out.println("(14)" + normalize);

        //获取当前系统无结尾分隔符的路径
        String normalizeNoEndSeparator = FilenameUtils.normalizeNoEndSeparator(fileDirectory);
        System.out.println("(15)" + normalizeNoEndSeparator);

        //移除文件的扩展名
        String removeExtension = FilenameUtils.removeExtension(fileFullName);
        System.out.println("(16)" + removeExtension);

        //转换分隔符为当前系统分隔符
        String separatorsToSystem = FilenameUtils.separatorsToSystem(fileFullName);
        System.out.println("(17)" + separatorsToSystem);

        //转换分隔符为linux系统分隔符
        String separatorsToUnix = FilenameUtils.separatorsToUnix(fileFullName);
        System.out.println("(18)" + separatorsToUnix);

        //转换分隔符为windows系统分隔符
        String separatorsToWindows = FilenameUtils.separatorsToWindows(fileFullName);
        System.out.println("(19)" + separatorsToWindows);

        //判断目录下是否包含指定文件或目录
        boolean directoryContains = FilenameUtils.directoryContains(fileDirectory, fileFullName);
        System.out.println("(20)" + directoryContains);

        //判断文件路径是否相同
        boolean equals = FilenameUtils.equals(fileFullName, FilenameUtils.separatorsToWindows(fileFullName));
        System.out.println("(21)" + equals);

        //判断文件路径是否相同，格式化并大小写不敏感
        boolean equals1 = FilenameUtils.equals(fileFullName, FilenameUtils.separatorsToWindows(fileFullName), true, IOCase.INSENSITIVE);
        System.out.println("(22)" + equals1);

        //判断文件路径是否相同，格式化并大小写敏感
        boolean equals2 = FilenameUtils.equals(fileFullName, FilenameUtils.separatorsToWindows(fileFullName), true, IOCase.SENSITIVE);
        System.out.println("(23)" + equals2);

        //判断文件路径是否相同，标准化比较
        boolean equalsNormalized = FilenameUtils.equalsNormalized(fileFullName, FilenameUtils.separatorsToUnix(fileFullName));
        System.out.println("(24)" + equalsNormalized);

        //判断文件路径是否相同，不格式化，大小写敏感根据系统规则：windows -> 敏感；linux -> 不敏感
        boolean equalsOnSystem = FilenameUtils.equalsOnSystem(fileFullName, FilenameUtils.normalize(fileFullName));
        System.out.println("(25)" + equalsOnSystem);

        //判断文件扩展名是否包含在指定集合中
        List<String> list = Lists.newArrayList("java", "jpg");
        boolean extension1 = FilenameUtils.isExtension(fileFullName, list);
        System.out.println("(26)" + extension1);

        //判断文件扩展名是否等于指定扩展名
        boolean extension2 = FilenameUtils.isExtension(fileFullName, "txt");
        System.out.println("(27)" + extension2);

        //判断文件扩展名是否包含在指定字符串数组中
        boolean extension3 = FilenameUtils.isExtension(fileFullName, "txt", "java");
        System.out.println("(28)" + extension3);

        //判断文件扩展名是否和指定规则匹配，大小写敏感
        boolean wildcardMatch = FilenameUtils.wildcardMatch(fileName, "*.???");
        System.out.println("(29)" + wildcardMatch);

        //判断文件扩展名是否和指定规则匹配，大小写不敏感
        boolean wildcardMatch1 = FilenameUtils.wildcardMatch(fileName, "*.???", IOCase.INSENSITIVE);
        System.out.println("(30)" + wildcardMatch1);

        //判断文件扩展名是否和指定规则匹配，根据系统判断敏感型：windows -> 不敏感；linux -> 敏感
        boolean wildcardMatchOnSystem = FilenameUtils.wildcardMatchOnSystem(fileName, "*.???");
        System.out.println("(31)" + wildcardMatchOnSystem);

    }

}












