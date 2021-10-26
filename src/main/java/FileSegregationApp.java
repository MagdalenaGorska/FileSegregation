import java.io.*;

public class FileSegregationApp {

    private static final String path_HOME = "c:\\HOME\\";
    private static final String path_DEV = "c:\\HOME\\DEV\\";
    private static final String path_TEST = "c:\\HOME\\TEST\\";
    private static final String path_count = "c:\\HOME\\count.txt";
    public static FileWriter fileCount;

    public static void main(String[] args) throws IOException {

        File fileHOME = FileSegregation.createFile(path_HOME);
        File fileDEV = FileSegregation.createFile(path_DEV);
        File fileTEST = FileSegregation.createFile(path_TEST);
        fileCount = new FileWriter(path_count);

        FileSegregation.segregation(fileHOME);
    }


}
