package config;

public class Configuration {
    public static final String PNG = "PNG";

    public static final int QRImageWidth = 350;
    public static final int QRImageHeight = 350;

//    public static final String QR_CODE_IMAGE_PATH = "./resources/MyQRCode.png";
//    public static final String CLASS_FILE_PATH = "./resources/classes.txt";
//    public static final String STUDENT_FILE_PATH = "./resources/students.txt";
//    public static final String TEACHER_FILE_PATH = "./resources/teachers.txt";

    public static final String rootServer = "http://complete.se43jvv6ep.us-west-2.elasticbeanstalk.com";
    public static final String QR_CODE_IMAGE_PATH = rootServer + "/MyQRCode.png";
    public static final String CLASS_FILE_PATH = rootServer + "/classes.txt";
    public static final String STUDENT_FILE_PATH = rootServer + "/students.txt";
    public static final String TEACHER_FILE_PATH = rootServer + "/teachers.txt";
//
}
