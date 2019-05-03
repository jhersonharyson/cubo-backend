package br.com.totvs.cubo.api.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistData {
    public static void persistFile(String data, String url, String source) throws IOException {

        File file = new File(source);

        if (!file.exists()) {
            file.mkdirs();
        }

        FileWriter arq = new FileWriter(url);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(data);
        arq.close();
    }
}
