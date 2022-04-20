import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       String fileName = scanner.next();

        try {
            String[] text = Files.readString(Path.of("src/"+fileName)).split(" ");
            LinkedHashMap<Object, Long> mapWords =  Arrays.stream(text).collect(Collectors.groupingBy(x -> x, Collectors.counting()))
                    .entrySet().stream().sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (w1, w2) -> w1, LinkedHashMap::new));

            for (Map.Entry<Object, Long> words : mapWords.entrySet()) {
                System.out.println(words.getKey() + " - " + words.getValue());
            }
            
        } catch (IOException e) {
            System.out.println("Файл не найден или не может быть открыт!");
        }
    }
}
