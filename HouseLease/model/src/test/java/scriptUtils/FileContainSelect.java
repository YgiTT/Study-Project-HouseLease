package scriptUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询指定路径下的包含了指定内容的file文件
 *
 * @Author Ryan Yan
 * @Since 2024/10/15 14:33
 */
public class FileContainSelect {


    public static void main(String[] args) {
        String directoryPath = "Y:\\0.上线\\MR_VDN";
        String searchText = "43599";

        File directoryFile = new File(directoryPath);
        if (!directoryFile.exists() || !directoryFile.isDirectory()) {
            System.out.println("The provided path is not a valid directory: " + directoryPath);
            return;
        }
        searchFiles(directoryFile, searchText);

    }

    private static void searchFiles(File directory, String searchText) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {     //递归子目录
                searchFiles(file, searchText);
            } else {
                try {
                    //查询内容
                    if (containText(file, searchText)) {
                        System.out.println("Found in: " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }

    private static boolean containText(File file, String searchText) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    return true;
                }
            }
        }
        return false;
    }

}
