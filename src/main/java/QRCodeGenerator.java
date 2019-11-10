import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import config.Configuration;
import utils.FileWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGenerator {

    private static void generateQRCodeImage(String text, String filePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, Configuration.QRImageWidth, Configuration.QRImageHeight);
        Path path = FileSystems.getDefault().getPath(filePath);
        FileWriter.writeImageFile(bitMatrix, Configuration.PNG, path);
    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("Trung Hieu Tran", Configuration.QR_CODE_IMAGE_PATH);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }
}
