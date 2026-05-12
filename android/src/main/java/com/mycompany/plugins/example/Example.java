package com.mycompany.plugins.example;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Example {

    private static final String TAG = "MyCachePlugin";
    private Context context;

    public Example(Context context) {
        this.context = context;
    }

    private File getCacheFile() {
        File file = new File(context.getFilesDir(), "my_cache.json");
        Log.d(TAG, "Dosya yolu: " + file.getAbsolutePath());
        return file;
    }

    private JSONObject readAll() {
        File file = getCacheFile();
        if (!file.exists()) {
            Log.d(TAG, "Cache dosyası oluşturulamadı");
            return new JSONObject();
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            reader.close();
            Log.d(TAG, "Dosyadan okunan " + sb.toString());
            return new JSONObject(sb.toString());
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Okuma hatası: " + e.getMessage());
            return new JSONObject();
        }
    }

    private void writeAll(JSONObject json) {
        try {
            FileWriter writer = new FileWriter(getCacheFile());
            writer.write(json.toString());
            writer.close();
            Log.d(TAG, "Dosyaya yazılan: " + json.toString());
        } catch (IOException e) {
            Log.e(TAG, "Yazma hatası: " + e.getMessage());
        }
    }

    public String echo(String value) {
        return value;
    }

    public void set(String key, String value) {
        JSONObject json = readAll();
        try {
            json.put(key, value);
            writeAll(json);
        } catch (JSONException e) {
            Log.e(TAG, "Set hatası: " + e.getMessage());
        }
    }

    public String get(String key) {
        JSONObject json = readAll();
        String value = json.optString(key, "");
        Log.d(TAG, "Gelen key: " + key + " value: " + value);
        return value;
    }

    public void remove(String key) {
        JSONObject json = readAll();
        json.remove(key);
        writeAll(json);
        Log.d(TAG, "Silinen key: " + key);
    }
}
