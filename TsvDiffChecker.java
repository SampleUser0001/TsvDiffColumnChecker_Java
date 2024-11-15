import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TsvDiffChecker {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java TsvDiffChecker <file1> <file2>");
            return;
        }

        String file1 = args[0];
        String file2 = args[1];

        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2))) {

            String line1;
            String line2;
            Set<Integer> diffResults = new HashSet<>();

            while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null) {
                String[] columns1 = line1.split("\t");
                String[] columns2 = line2.split("\t");

                for (int i = 0; i < columns1.length; i++) {
                    if (!columns1[i].equals(columns2[i])) {
                        diffResults.add(i + 1); // Add 1 to make it human-readable (1-based index)
                    }
                }
            }

            diffResults.stream().forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }
}
