package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class JsonDataLoader implements DataLoader {

    private final ObjectMapper objectMapper;

    public JsonDataLoader() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public <T> List<T> loadData(String fileName, Class<T> targetClass) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Fichier introuvable : " + fileName);
            }
            return objectMapper.readValue(
                    inputStream,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, targetClass)
            );
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la lecture du fichier : " + fileName, e);
        }
    }
}