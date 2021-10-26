import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSegregation {

    public static File createFile(String path) {

        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }

    public static void segregation(File fileHOME) throws IOException {
        if (fileHOME.exists() && fileHOME.isDirectory()) {
            List<File> listOfFiles = Arrays.stream(fileHOME.listFiles())
                    .filter(file -> file.getPath().endsWith(".jar") || file.getPath().endsWith(".xml"))
                    .collect(Collectors.toList());

            listOfFiles.forEach(System.out::println);
            FileSegregationApp.fileCount.write("Liczba przeniesionych elementów: " + listOfFiles.size() + "\n");
            listOfFiles.forEach(file -> System.out.println(file.getName()));
            listOfFiles.forEach(file -> {
                try {
                    FileSegregation.move(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            FileSegregationApp.fileCount.close();
        }
    }

    private static void move(File file) throws IOException {
        var sourcePath = Paths.get(String.format("c:\\HOME\\%s", file.getName()));
        var destinationPath = Paths.get(String.format("c:\\HOME\\DEV\\%s", file.getName()));

        if (!file.getPath().endsWith(".xml") && (getDate(file) % 2 != 0)) {
            destinationPath = Paths.get(String.format("c:\\HOME\\TEST\\%s", file.getName()));

        }
        Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }


    private static Integer getDate(File file) {
        Timestamp timestamp = new Timestamp(file.lastModified());
        var time = timestamp.toLocalDateTime().toLocalTime();
        return time.getHour();
    }

}
