package strategy;

import java.nio.file.Path;
import java.util.List;

@FunctionalInterface
public interface SearchStrategy {
    List<Path> search(String fileName);
}
