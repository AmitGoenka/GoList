package org.agoenka.golist;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: agoenka
 * Created At: 9/29/2016
 * Version: ${VERSION}
 */

public class LocalFilesHelper {

    static List<String> readItems(Context context, String fileName) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, fileName);
        List<String> items = null;
        try {
            items = new ArrayList<>(FileUtils.readLines(file));
        } catch (IOException e) {
            items = new ArrayList<>();
        }
        return items;
    }

    static void writeItems(Context context, String fileName, List<String> items) {
        File filesDir = context.getFilesDir();
        File todoFile = new File(filesDir, fileName);
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}