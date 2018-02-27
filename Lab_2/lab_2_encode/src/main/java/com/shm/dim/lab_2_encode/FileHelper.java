package com.shm.dim.lab_2_encode;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    public static void write(File file, String text) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(text);
        }
        catch (IOException e) {
            Log.d("Log_02", "Не удалось записать данные в файла " + file.getName());
        }
    }
}