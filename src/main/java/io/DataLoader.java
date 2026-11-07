package io;

import java.util.List;

public interface DataLoader {
    <T> List<T> loadData(String fileName, Class<T> targetClass);
}