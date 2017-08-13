package instant.alarmreceptortoolkitapp.data.protocols.entities;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import instant.alarmreceptortoolkitapp.global.Constants;

/**
 * Created by hector on 28/05/17.
 */


public abstract class Signal implements Frame {

    private MsgType mMsgType = null;
    private Map<Component, String> mProperties = new HashMap<Component, String>();
    private List<Event> mEvents = new ArrayList<>();
    public static int MSG_REF_COUNTER = 0;
    public static int MSG_ID_COUNTER = 0;

    /**
     * Should return the required field for build the signal by the msg type
     *
     * @param msg_type
     * @return
     */
    public abstract Component[] getRequiredFields(MsgType msg_type);

    /**
     * Should return the required field for the build the signals
     */
    public abstract Component[] getRequiredFields();

    /**
     * Return the End of message identificator. (Is 0x0D so for most receptors).
     *
     * @return
     */
    public byte getEndOfMessage() {
        return 0x0D;
    }

    public boolean validatePropertyLen(Component name, String value) {
        return getPropertyLen(name) == value.length();
    }

    public int getPropertyLen(Component name) {
        Component[] r = getRequiredFields();
        for (Component c : getRequiredFields()) {
            if (c.toString().equals(name.toString())) {
                return c.getLen();
            }
        }
        return 0;
    }

    /**
     * Add property to signal.
     *
     * @param name  e.g "code"
     * @param value e.g "E101"
     */
    public void addProperty(Component name, String value) {
        int expectedLen = getPropertyLen(name);
        if (expectedLen != value.length()) {
            Log.d(Constants.LOG_NAME, "addProperty: Bad property size: "
                    + name.str() + " set " + value.length() +
                    ". Expected: " + getPropertyLen(name));
        }
        if (name == Component.FMT){
            value = standarizeFMT(value);
        }
        mProperties.put(name, value);
    }

    public String standarizeFMT(String fmt){
        return fmt.split(" - ")[0];
    }

    /**
     * Returns properties that have been added
     *
     * @return
     */
    protected Map<Component, String> getProperties() {
        return mProperties;
    }

    protected String getProperty(Component key) {
        return mProperties.get(key);
    }

    /**
     * Add properties from a map
     *
     * @param map e.g "{ "code": "E101", "protocol": "CONTACT_ID" }
     */

    public void fromMap(Map<Component, String> map) {
        this.mProperties.putAll(map);
    }

    /**
     * Validate if the signal is prepared for build.
     *
     * @return
     */
    public boolean validateProperties() {
        Map<Component, String> p = this.getProperties();
        MsgType msg_type = this.getMsgType();
        if (msg_type != null)
            for (Component field : this.getRequiredFields(msg_type)) {
                if (!p.containsKey(field)) {
                    return false;
                }
            }
        return true;
    }

    /**
     * Set the Message Type.
     *
     * @param msgType
     */
    public void setMsgType(MsgType msgType) {
        this.mMsgType = msgType;
    }

    /**
     * Get the Message Type.
     */
    public MsgType getMsgType() {
        return mMsgType;
    }

    /**
     * Get the reference number for the signal.
     * This have a counter will be increased every time when a signal is build. If
     * you dont assign this property the method use the counter value
     *
     * @return
     */
    public String getReferenceNumber() {
        if (getProperties().containsKey(Component.REF)) {
            return getProperties().get(Component.REF);
        }
        return String.valueOf(++MSG_REF_COUNTER);
    }

    public byte[] getBOM() {
        String bom = getProperty(Component.BOM);
        if (bom == null) {
            return null;
        }
        return bom.getBytes();
    }

    /**
     * Get the validation byte (V-Byte or Error-check byte)
     *
     * @param msg:    The msg up to and including the byte preceding the validation byte.
     * @param hasBOM: Indicate with the msg contains an Beginning of Message (BOM)
     * @return The byte V-Byte.
     * <p>
     * Note: If the msg contains the BOM this must be removed.
     * E.g: For ademco mx8000 usualy the BOM is '5', then some msg looks like this:
     * 5070211"083503"01"9956"r10y
     * But the first byte must be removed:
     * 070211"083503"01"9956"r10y
     * <p>
     * This algorithm is based on the official document of Silent Knight 9000 Protocol
     * and Ademco8000
     * Link: https://www.silentknight.com/documentation/Documents/151059.pdf (view 8.2.6)
     */
    public byte makeVByte(byte[] msg, boolean hasBOM) {
        if (hasBOM) {
            msg = Arrays.copyOfRange(msg, 1, msg.length);
        }

        // 1- Set the V-Byte comparison byte to zero
        byte vbyte = 0x00;

        for (int i = 0; i < msg.length; i++) {
            // 2- Add the first (or next) byte of the message to the V-Byte comparison byte
            vbyte += msg[i];

            // 3- Clear bit 7 of the V-Byte comparison byte.
            vbyte &= ~(1 << 7);

            // 4- Set bit 6 of the V-Byte comparison byte
            vbyte |= 1 << 6;
        }
        return vbyte;
    }


    public List<Event> getEvents() {
        return mEvents;
    }

    public void addEvent(Event e) {
        this.mEvents.add(e);
    }


    /********* TIME METHODS *************/
    public String getDate() {
        return getTimeProperty(Component.DATE, "ddMMyy");
    }

    public String getTime() {
        return getTimeProperty(Component.TIME, "hhmmss");
    }

    private String getTimeProperty(Component field, String format) {
        if (getProperties().containsKey(field)) {
            return getProperties().get(field);
        }
        return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
    }

}
