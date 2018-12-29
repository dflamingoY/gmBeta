package org.xiaoxingqi.gmdoc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yzm on 2018/1/25.
 */

public class FileUtils {

    public static void copyFile(File resouseFile, File targetFile) {
        try {
            FileInputStream inputStream = new FileInputStream(resouseFile);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
