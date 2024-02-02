package cz.interview.realpad.realpadinterview.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestUtils {

    public static String readResourceFileAsString(String filePath) {
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStreamReader reader;
        try {
            reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            return FileCopyUtils.copyToString(reader).replace("\n", "").trim();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
