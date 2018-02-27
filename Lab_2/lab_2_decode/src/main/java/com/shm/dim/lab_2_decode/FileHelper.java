package com.shm.dim.lab_2_decode;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

    public static String read(File file) {
        String text = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            text = br.readLine();
        }
        catch (IOException e) {
            Log.d("Log_02", "Не удалось прочитать данные из файла " + file.getName());
        }
        return text;
    }
}