package FoodDelivery;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class CsvReader {
    private static final String dataDir;
    private static CsvReader r;

    static {
        dataDir = "Proiect/data/";
        r = null;
    }

    private CsvReader() { }

    static CsvReader getReader() {
        if (r == null) {
            r = new CsvReader();
        }
        return r;
    }

    <T extends CsvCompatible> List<T> read(T mock) {
        File file = new File(dataDir + mock.getClass().getSimpleName() + ".csv");
        if (Files.notExists(file.toPath())) {
            return new ArrayList<>();
        }

        try (BufferedReader fin = new BufferedReader(new FileReader(file))) {
            List<T> result = new ArrayList<>();
            String csvString = fin.readLine();
            while((csvString = fin.readLine())!=null){
                result.add((T) mock.csvObject(csvString));
            }

            return result;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Could not read from 'data' directory!");
            System.exit(1);
            return new ArrayList<>();
        }
    }
}
