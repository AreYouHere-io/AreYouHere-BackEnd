package utils;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.Path;

public class FileWriter {

    public static void writeImageFile(BitMatrix bitMatrix, String format, Path path) throws IOException {
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
