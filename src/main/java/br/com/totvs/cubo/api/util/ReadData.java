package br.com.totvs.cubo.api.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadData {

    public static String readFile(String source) throws IOException {
        return new String(Files.readAllBytes(Paths.get(source)));
    }
}
