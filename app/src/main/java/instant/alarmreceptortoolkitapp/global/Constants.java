package instant.alarmreceptortoolkitapp.global;

/**
 * Created by hector on 28/05/17.
 */

public class Constants {
    public static String LOG_NAME = "APP__";

    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    public static final String RASPBERRY_IP = "http://192.168.1.142:8000";
}
