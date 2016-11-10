package util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Clean {

    public void clearDerictory(File folder) throws IOException {
        FileUtils.cleanDirectory(folder);
    }
}
