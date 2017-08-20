package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.global.Constants;

/**
 * This class model a Ademco MX8000 Receptor when use the ADEMCO 8000 Automation Protocol
 * Its maked based on the official MX8000 document
 * Created by hector on 28/05/17.
 */
public class Ademco8000Signal extends Signal {

    private Component[] mReqFields = null;
    private Ademco8000PanelData panelData;

    public Ademco8000Signal(MsgType msgType) {
        this.setMsgType(msgType);
    }

    @Override
    public boolean validateProperties() {
        if (!super.validateProperties()) {
            return false;
        }
        if (getMsgType() == MsgType.SYSTEM) {
            if (getEvents().size() <= 0) {
                return false;
            }
        }
        if (getMsgType() == MsgType.CALL) {
            if (panelData == null) {
                return false;
            }
        }
        for (Event e : getEvents()) {
            if (!e.isReadyForBuild()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Component[] getRequiredFields() {
        if (mReqFields == null) {
            mReqFields = getRequiredFields(getMsgType());
        }
        return mReqFields;
    }

    @Override
    public Component[] getRequiredFields(MsgType msg_type) {
        Component rcvr = Component.build(Component.RECEIVER, 2);
        if (msg_type == MsgType.CALL) {
            Component fmt = Component.build(Component.FMT, 3);
            Component line = Component.build(Component.LINE, 2);
            return new Component[]{rcvr, fmt, line};
        }
        if (msg_type == MsgType.HEART_BEAT || msg_type == MsgType.SYSTEM) {
            return new Component[]{rcvr};
        }
        return null;
    }

    @Override
    public byte[] buildFrame() {
        if (!this.validateProperties()) {
            return null;
        }
        MsgType msg_type = getMsgType();

        switch (msg_type) {
            case CALL: {
                return this.buildMsgTypeCall();
            }
            case HEART_BEAT: {
                return this.buildMsgTypeHeartBeat();
            }
            case SYSTEM: {
                return this.buildMsgTypeSystem();
            }
            default:
                Log.d(Constants.LOG_NAME, "ERROR");
                throw new Error("Unexpected value: The msg type'" + msg_type + "' dont are valid");
        }
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
    public byte makeGoodVByte(byte[] msg, boolean hasBOM) {
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


    /**
     * Build the header based on the current componetns
     *
     * @param refNumber Reference number
     * @return The bytes of the signal header
     */
    private byte[] buildMsgHeader(byte[] refNumber) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bom = getBOM();
        try {
            if (bom != null) {
                out.write(bom);
            }
            out.write(getIDByMsgType(getMsgType()));
            out.write(getDate().getBytes());
            out.write("\"".getBytes());
            out.write(getTime().getBytes());
            out.write("\"".getBytes());
            out.write(getProperty(Component.RECEIVER).getBytes());
            out.write("\"".getBytes());
            out.write(refNumber, 0, refNumber.length);
            out.write("\"".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    /**
     * Build a HeartBeat message.
     * A heartbeat can be identified by the reference  number used in the AE
     * header that will always be '0000'.
     */
    private byte[] buildMsgBody(byte[] innerData) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] heartBeatRef = null;

        if (getMsgType() == MsgType.HEART_BEAT){
            // "A heartbeat can be identified by the reference number used in the AE
            //  header that will always be 0000". See: Section 8.8.4, Pag 8-8
            heartBeatRef = "0000".getBytes();
        }else{
            heartBeatRef = getReferenceNumber().getBytes();
        }
        byte[] header = buildMsgHeader(heartBeatRef);
        try {
            out.write(header);
            // Insert the inner data like FMT, Line, Panel data, etc
            if (innerData != null) {
                out.write(innerData);
            }
            out.write(makeVByte(out.toByteArray(), getBOM() != null));
            out.write(getEndOfMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    /**
     * Build a HeartBeat message.
     * A heartbeat can be identified by the reference  number used in the AE
     * header that will always be '0000'.
     */
    private byte[] buildMsgTypeHeartBeat() {
        return buildMsgBody(null);
    }

    /**
     * Build a System message.
     */
    private byte[] buildMsgTypeSystem() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            for (Event e : getEvents()) {
                out.write(e.build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildMsgBody(out.toByteArray());
    }

    /**
     * Build a Call Message.
     */
    private byte[] buildMsgTypeCall() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            out.write(getProperty(Component.FMT).getBytes());
            out.write(getProperty(Component.LINE).getBytes());
            out.write(panelData.buildFrame());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buildMsgBody(out.toByteArray());
    }

    public void addPanelData(Ademco8000PanelData data) {
        panelData = data;
    }

    /**
     * Panel data contains all the data that pertains to the control panel that
     * dialed into the receiver, such as the account number, what kind of alarm,
     * the zone number, caller ID information, etc. Each record contains an identifier
     * byte followed by data.
     */
    private byte[] buildPanelDataFrame() {
        // TODO
//        Map<Component, String> p = getProperties();
        return new byte[0];
    }

    /**
     * Set the value of signal header     .
     *
     * @param rcvr receiver
     * @param date Date of the signal. If is null then the current date is set
     * @param time Time of the signal. If is null then the current time is set
     * @param ref  Reference number. If is null then the ref counter is set
     */
    public void setSignalHeader(String rcvr, String date, String time, String ref) {
        this.addProperty(Component.RECEIVER, rcvr);
        if (date != null && date.length() > 0) {
            this.addProperty(Component.DATE, date);
        }
        if (time != null && time.length() > 0) {
            this.addProperty(Component.TIME, time);
        }
        if (ref != null && ref.length() > 0) {
            this.addProperty(Component.REF, ref);
        }
    }

    private byte getIDByMsgType(MsgType msgType) {
        // Based on table 8–3
        switch (msgType) {
            case SYSTEM: {
                return 0x02;
            }
            case HEART_BEAT: {
                return 0x03;
            }
            case CALL: {
                return 0x26;
            }
        }
        throw new Error("Bad msg type");
    }
}
