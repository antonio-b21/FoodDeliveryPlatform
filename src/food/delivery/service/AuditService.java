package food.delivery.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

class AuditService {
    private static final String dataDir;

    static {
        dataDir = "data/";
    }

    static void log(String action) {
        File file = new File(dataDir + "Audit.csv");
        if (Files.notExists(file.toPath())) {
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(file))) {
                fout.write("action,date\n");
            } catch (IOException e) {
                System.out.println("Could not write to 'data' directory!");
                System.exit(1);
            }
        }

        try (BufferedWriter fout = new BufferedWriter(new FileWriter(file, true))) {
            fout.write(action + "," + new Date(System.currentTimeMillis()) + "\n");
        } catch (IOException e) {
            System.out.println("Could not write to 'data' directory!");
            System.exit(1);
        }
    }
}
