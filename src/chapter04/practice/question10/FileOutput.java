package chapter04.practice.question10;

import java.io.File;

public class FileOutput {
    public void printDetail(File file) {
        if (! file.isDirectory()) {
            System.out.println(file.getAbsolutePath() + " : " + file.getTotalSpace());
            return;
        }
        for (File f : file.listFiles()) {
            printDetail(f);
        }
    }
}
