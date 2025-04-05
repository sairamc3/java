import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ReadingAFile {
    public static void main(String[] args) {

        List<DVDInfo> dvds = new ArrayList<>();

        Path path = Path.of("DVDInfo.txt");

        try (Stream<String> lines = Files.lines(path)) {

            lines.map(line -> {
                String[] movie = line.split("/");
                return new DVDInfo(movie[0], movie[1], movie[2]);
            }).forEach(dvds::add);

            dvds.forEach(System.out::println);

        } catch (IOException exception) {
            System.out.println("Error acessing the file");
        }

    }
}

class DVDInfo {
    private String name;
    private String genere;
    private String actor;

    public String toString() {
        return name + " / " + actor + " / " + genere;
    }

    DVDInfo(String name, String genere, String actor) {
        this.name = name;
        this.genere = genere;
        this.actor = actor;
    }

}