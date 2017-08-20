package instant.alarmreceptortoolkitapp.data.protocols.entities;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by hector on 28/05/17.
 */


public abstract class Signal implements Frame {

    private MsgType mMsgType = null;
    private Map<Component, String> mProperties = new HashMap<Component, String>();
    private List<Event> mEvents = new ArrayList<>();
    public static int MSG_REF_COUNTER = 0;
    public static int MSG_ID_COUNTER = 0;
    private boolean _isBuildWithBadChecksum = false;

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
            // TODO: Print bad size
        }
        if (name == Component.FMT) {
            value = standarizeFMT(value);
        }
        mProperties.put(name, value);
    }

    public String standarizeFMT(String fmt) {
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
     * Add properties from a map
     *
     * @param map e.g "{ "code": "E101", "protocol": "CONTACT_ID" }
     */
    public void fromMap(Map<Component, String> map) {
        this.mProperties.putAll(map);
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
     * Get the validation byte (V-Byte or Error-check byte).
     *
     * If the makeBadVByte is setted then make bad VByte.
     */
    public byte makeVByte(byte[] msg, boolean hasBOM) {
        if (this.isBuildWithBadChecksum()){
            return makeBadVByte(msg, hasBOM);
        }
        return makeGoodVByte(msg, hasBOM);
    }

    /**
     * Make good V-Byte
     */
    public abstract byte makeGoodVByte(byte[] msg, boolean hasBOM);

    public byte makeBadVByte(byte[] msg, boolean hasBOM) {
        byte _byte = makeGoodVByte(msg, hasBOM);
        if (_byte == 0x58){
            return (byte)0x78;
        }
        return (byte)0x58;
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

    public boolean isBuildWithBadChecksum() {
        return this._isBuildWithBadChecksum;
    }

    /**
     * Force to build signal with wrong checksum
     * <p>
     * In the moment of build, the generator select some alatory byte,
     * different from the true checksum.
     *
     * @param value If value is True then this option is enabled
     */
    public void setBuildWithBadChecksum(boolean value) {
        this._isBuildWithBadChecksum = value;
    }

}
