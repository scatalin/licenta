package input.utils;

import java.io.File;

/**
 * Implementation of the FileManager
 */
public class FileManager {

    public void moveFile(File fileToMove, String destinationDirectory) {
        moveFileToDirectory(fileToMove, new File(destinationDirectory));
    }

    private void moveFileToDirectory(File srcFile, File destDir) {
        moveFile(srcFile, new File(destDir, srcFile.getName()));
    }

    private void moveFile(File srcFile, File destFile) {
        srcFile.renameTo(destFile);
    }

}
