import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileSegregationApp {

    private static String path_HOME = "c:\\HOME\\";
    private static String path_DEV = "c:\\HOME\\DEV\\";
    private static String path_TEST = "c:\\HOME\\TEST\\";


    public static void main(String[] args) throws IOException {

        File fileHOME = FileSegregation.createFolder(path_HOME);
        File fileDEV = FileSegregation.createFolder(path_DEV);
        File fileTEST = FileSegregation.createFolder(path_TEST);

        if (fileHOME.exists() && fileHOME.isDirectory()) {
            List<File> listOfFiles = Arrays.stream(fileHOME.listFiles())
                    .filter(file -> file.getPath().endsWith(".jar") || file.getPath().endsWith(".xml"))
                    .collect(Collectors.toList());

            listOfFiles.forEach(System.out::println);
            listOfFiles.forEach(file -> System.out.println(file.getName()));
            listOfFiles.forEach(file -> {
                try {
                    FileSegregation.move(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }


    }


}
