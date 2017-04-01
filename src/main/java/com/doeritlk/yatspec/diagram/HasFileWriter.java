package com.doeritlk.yatspec.diagram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public interface HasFileWriter {

    default Path write(byte[] data, String dir, String fileName) throws IOException {
        Path path = Paths.get(dir, fileName);
        Files.write(path, data, StandardOpenOption.CREATE);
        return path;
    }
}
