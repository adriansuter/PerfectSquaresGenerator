package as;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Adrian Suter, https://github.com/adriansuter/
 */
public class PerfectSquaresGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            printUsage();
            System.exit(0);
        }

        int rounds = 0;
        try {
            rounds = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("<rounds> is not an integer.");
            printUsage();
            System.exit(0);
        }

        int nrSquaresPerRound = 0;
        try {
            nrSquaresPerRound = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("<nrSquaresPerRound> is not an integer.");
            printUsage();
            System.exit(0);
        }

        try {
            String newLine = System.getProperty("line.separator");
            BigInteger two = new BigInteger("2");
            BigInteger s = new BigInteger("1");
            BigInteger a = new BigInteger("3");
            for (int i = 1; i <= rounds; i++) {
                File f = new File(i + ".zip");
                try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f))) {
                    ZipEntry e = new ZipEntry("squares.txt");
                    out.putNextEntry(e);
                    for (long j = 0; j < nrSquaresPerRound; j++) {
                        out.write(s.toString().getBytes());
                        out.write(newLine.getBytes());

                        s = s.add(a);
                        a = a.add(two);
                    }

                    out.closeEntry();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PerfectSquaresGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void printUsage() {
        System.out.println("Usage:");
        System.out.println("java -jar PerfectSquaresGenerator.jar <rounds> <nrSquaresPerRound>");
    }

}
