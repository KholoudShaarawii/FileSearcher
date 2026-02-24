package com.kh.hardDiskSearch;

import strategy.SearchStrategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class HardDisk implements SearchStrategy {

    @Override
    public List<Path> search(String fileName) {
        List<Path> result = new ArrayList<>();
        String fileNameLower = fileName.toLowerCase();

        File[] roots = File.listRoots();
        for (File root : roots) {
            try {
                Files.walkFileTree(root.toPath(), new SimpleFileVisitor<Path>() { //anonymousClass

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        String currentFileName = file.getFileName().toString().toLowerCase();

                        if (currentFileName.contains(fileNameLower)) {
                            result.add(file);
                        }

                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) { }
        }

        return result;
    }
}