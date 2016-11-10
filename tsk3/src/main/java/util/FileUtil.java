package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private RandomUtil randomString = new RandomUtil();

    private String fileName;

    public void newFile() throws IOException {
        fileName = randomString.randomString();
        String relativePath = "./tsk3/src/main/resources/files/" + fileName + ".txt";
        File file = new File(relativePath);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(randomString.randomString());
        fileWriter.close();
    }

    public String getFileName() {
        return fileName + ".txt";
    }
}
