package FeedbackAnalyzer.service.impl;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonReaderServiceImpl {

    static public List<Object> readObjectFromFile(String filepath) {
        String content = null;
        Gson gson = new Gson();

        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(content, List.class);
    }
}
