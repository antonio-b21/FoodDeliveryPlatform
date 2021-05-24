package FoodDelivery;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class CsvWriter {
    private static final String dataDir;
    private static CsvWriter w;

    static {
        dataDir = "data/";
        w = null;
    }

    private CsvWriter() {
        if (Files.exists(Path.of(dataDir)) && Files.isDirectory(Path.of(dataDir))) {
            return;
        }

        try {
            Files.createDirectory(Path.of(dataDir));
        } catch (IOException e) {
            System.out.println("Could not write to 'data' directory!");
            System.exit(1);
        }
    }

    static CsvWriter getWriter() {
        if (w == null) {
            w = new CsvWriter();
        }
        return w;
    }

    <T extends CsvCompatible> void write(T obj) {
        File file = new File(dataDir + obj.getClass().getSimpleName() + ".csv");
        if (Files.notExists(file.toPath())) {
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(file))) {
                fout.write(obj.csvHeader() + "\n");
            } catch (IOException e) {
                System.out.println("Could not write to 'data' directory!");
                System.exit(1);
            }
        }

        try (BufferedWriter fout = new BufferedWriter(new FileWriter(file, true))) {
            fout.write(obj.csvString() + "\n");
        } catch (IOException e) {
            System.out.println("Could not write to 'data' directory!");
            System.exit(1);
        }
    }

}
