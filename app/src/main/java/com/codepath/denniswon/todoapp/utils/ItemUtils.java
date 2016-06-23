package com.codepath.denniswon.todoapp.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by denniswon on 6/21/16.
 */
public class ItemUtils {

    public static ArrayList<String> readItems(File filesDir) {
        File todoFile = new File(filesDir, "todo.txt");
        try {
            return new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<String>();
    }

    public static void writeItems(File filesDir, ArrayList<String> items) {
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
