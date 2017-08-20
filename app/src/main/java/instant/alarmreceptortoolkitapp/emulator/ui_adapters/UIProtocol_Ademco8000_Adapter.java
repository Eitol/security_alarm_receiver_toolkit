package instant.alarmreceptortoolkitapp.emulator.ui_adapters;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000CallEvent;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000PanelData;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000Signal;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000SystemEvent;
import instant.alarmreceptortoolkitapp.emulator.SimulatorContract;

/**
 * Created by hector on 13/08/17.
 */
public class UIProtocol_Ademco8000_Adapter implements UIProtocolAdapter {
    @Override
    public Signal extractSignal(SimulatorContract.View view) {
        MsgType msgType = view.getMsgType();
        Ademco8000Signal signal = new Ademco8000Signal(msgType);
        String rcvr = view.getReceptor();
        String ref = view.getRef();
        ref = ref.length() > 0 ? ref : null;
        signal.setSignalHeader(rcvr, null, null, ref);

        if (view.insertBadChecksum()) {
            signal.setBuildWithBadChecksum(true);
        }

        if (msgType == MsgType.HEART_BEAT) {
            return signal;
        }

        String line = view.getLine();
        signal.addProperty(Component.LINE, line);
        String zoneOrUsr = view.getZoneOrUsr();

        if (msgType == MsgType.SYSTEM) {
            return extractSystemSignal(view, signal, zoneOrUsr, line);
        }

        if (msgType == MsgType.CALL) {
            return extractCallSignal(view, signal, zoneOrUsr);
        }
        throw new Error("ERROR: Bad event type");
    }

    public static byte[] getSystemCode(String codeAndDesc) {
        Pattern pattern = Pattern.compile("\\(0x(.*?)\\)");
        Matcher matcher = pattern.matcher(codeAndDesc);
        if (matcher.find()) {
            try {
                return Hex.decodeHex(matcher.group(1).toCharArray());
            } catch (DecoderException e) {
                e.printStackTrace();
            }
        }
        throw new Error("ERROR: BAD System code!");
    }

    private Ademco8000Signal extractSystemSignal(SimulatorContract.View view, Ademco8000Signal signal, String zoneOrUsr, String line) {
        /* Adding the code */
        String codeAndDesc = view.getCode();
        byte[] code = getSystemCode(codeAndDesc);
        Ademco8000SystemEvent e = new Ademco8000SystemEvent(code);
        e.addComponent(Component.LINE, line);
        // The Receiver Model Number output is 8000 indicating the MX8000–3EX Receiver.
        // See: Table 8–11: System Messages
        e.addComponent(Component.RECEIVER_MODEL, "8000");
        e.addComponent(Component.USER, zoneOrUsr);
        signal.addEvent(e);
        return signal;
    }

    private Ademco8000Signal extractCallSignal(SimulatorContract.View view, Ademco8000Signal signal, String zoneOrUsr) {
        String account = view.getAccount();
        String fmt = view.getFMT().split(" - ")[0];
        String partition = view.getPartition();

        if (account.isEmpty() || partition.isEmpty() || zoneOrUsr.isEmpty()) {
            return null;
        }

        signal.addProperty(Component.FMT, fmt);

        List<Event> events = new ArrayList<>();
        Ademco8000CallEvent event = new Ademco8000CallEvent(fmt);
        String code = view.getCode().split(" - ")[1];
        EventCode ec1 = new EventCode(code.getBytes());
        event.setCode(ec1);
        event.setQualifier(Event.Qualifier.NEW);
        event.addComponent(Component.PARTITION, partition);
        event.addComponent(Component.ZONE_OR_USER, zoneOrUsr);
        events.add(event);
        Map<Component, String> data = new HashMap<>();
        if (view.insertBadData()) {
            data.put(Component.BAD_DATA, view.getBadData());
        }
        // Based on the example of the pag 19 of "Sample MX8000 Decimal Display"
        // Caller ID: 08060630 104 MACDONALD MIKE
        // Real message: 5&070211"084156"06"0081"00006113408905193886407
        if (view.insertCallerInfo()) {
            data.put(Component.CALLER_ID_NAME, "MACDONALDsMIKEa");
            data.put(Component.CALLER_ID_PHONE, "104");
            data.put(Component.CALLER_ID_OTHER, "08060630");
        }
        if (view.insertListenIn()) {
            String listenInDuration = view.getListenInDuration();
            if (listenInDuration.length() > 0) {
                listenInDuration = String.format(Locale.US, "%03d", Integer.parseInt(listenInDuration));
            } else {
                // If the duration dont setted the default value es 255 (maximun value)
                // See 5.5.2.4 Pag 5-44.: "Values range from 0 to 255 in 1 second increments."
                listenInDuration = "255";
            }
            data.put(Component.LISTEN_IN_INDICATOR, listenInDuration);
        }
        data.put(Component.ACCOUNT, account);
        Ademco8000PanelData panelData = new Ademco8000PanelData(data, events);
        signal.addPanelData(panelData);
        return signal;
    }

}
