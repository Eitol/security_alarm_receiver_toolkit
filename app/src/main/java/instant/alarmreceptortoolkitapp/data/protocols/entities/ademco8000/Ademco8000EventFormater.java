package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.global.Constants;

/**
 * Created by hector on 29/07/17.
 */

public class Ademco8000EventFormater {

    /**
     * Format some code event using the format specified in the protocol Ademco 8000
     *
     * @param event event code
     * @return
     */
    public static byte[] format(Event event, String fmt) {
        try {
            Ademco8000EventFormater o = new Ademco8000EventFormater();
            Method method = Ademco8000EventFormater.class.getMethod("format" + fmt, Event.class);
            return (byte[]) method.invoke(o, event);
        } catch (Exception error) {
            Log.d(Constants.LOG_NAME, "format: Error this fmt formater dont exist: " + fmt);
        }
        return null;
    }

    /**
     * Format with the Contact ID dialer format.
     * <p>
     * Frame example: 18111095341
     * Explanation:
     * 18: CID Identifier. Fixed
     * 1: New Qualifier (1 = new, 3 = restore, 6 = previous). Numeric format
     * 110: Contact ID Code (Fire)
     * 95: Group/Partition Number
     * 341: User or Zone.
     *
     * @param event event code
     */
    public byte[] format050(Event event) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write("18".getBytes());
            out.write(event.getQualifier().toNumeric().getBytes());
            out.write(event.getCode().getCode());
            out.write(event.getComponentValue(Component.PARTITION));
            out.write(event.getComponentValue(Component.ZONE_OR_USER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static List<Component> getRequiredComponentsByFMT(String fmt) {
        for (Object[] o: Ademco8000Protocol.fmtList) {
            if (o[0].equals(fmt)){
                if (o.length < 3){
                    break;
                }
                Component[] list = (Component[]) o[2];
                return Arrays.asList(list);
            }
        }
        return null;
    }


}
