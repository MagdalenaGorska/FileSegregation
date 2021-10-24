import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;

public class FileSegregation {

    public static File createFolder(String path) {

        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        return file;
    }

    public static void move(File file) throws IOException {
        if (file.getPath().endsWith(".xml")) {
            Files.move(Paths.get(String.format("c:\\HOME\\%s", file.getName())), Paths.get(String.format("c:\\HOME\\DEV\\%s", file.getName())), StandardCopyOption.REPLACE_EXISTING);
        } else {
            if (getDate(file) % 2 == 0) {
                Files.move(Paths.get(String.format("c:\\HOME\\%s", file.getName())), Paths.get(String.format("c:\\HOME\\DEV\\%s", file.getName())), StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.move(Paths.get(String.format("c:\\HOME\\%s", file.getName())), Paths.get(String.format("c:\\HOME\\TEST\\%s", file.getName())), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    public static Integer getDate(File file) {
        Timestamp timestamp = new Timestamp(file.lastModified());
        var time = timestamp.toLocalDateTime().toLocalTime();
        return time.getHour();
    }

}
