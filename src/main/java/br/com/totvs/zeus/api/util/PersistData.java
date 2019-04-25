package br.com.totvs.zeus.api.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistData {
    public static void persistFile(String data, String url) throws IOException {
        FileWriter arq = new FileWriter(url);
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.printf(data);
        arq.close();
    }
}
