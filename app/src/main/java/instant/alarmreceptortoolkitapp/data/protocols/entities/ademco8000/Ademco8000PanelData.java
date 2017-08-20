package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Frame;


public class Ademco8000PanelData implements Frame {

    private final Map<Component, String> panelDataComponents;
    private final List<Event> mEvents;

    /**
     * Based on: "Table 8–6: Panel Data Identifiers and Descriptions"
     **/
    private Object[][] PANEL_DATA_FIELDS = {
            {Component.ACCOUNT, 0x05},
            {Component.CALLER_ID_NAME, 0x11},
            {Component.CALLER_ID_PHONE, 0x12},
            {Component.CALLER_ID_OTHER, 0x13},
            {Component.EVENT_FIELD_GOOD_DATA, 0x22},
            {Component.BAD_DATA, 0x23},
            {Component.LISTEN_IN_INDICATOR, 0x2A},
            {Component.LONG_CALL_INDICATOR, 0x2C}
    };

    public Ademco8000PanelData(Map<Component, String> p, List<Event> events) {
        mEvents = events;
        this.panelDataComponents = getPanelComponentsInMap(p);
    }

    private Map<Component, String> getPanelComponentsInMap(Map<Component, String> p) {
        Map<Component, String> panelDataComponents = new HashMap<>();
        for (Map.Entry<Component, String> entry : p.entrySet()) {
            for (Object[] c : PANEL_DATA_FIELDS) {
                if (c[0] == entry.getKey()) {
                    Component newC = entry.getKey();
                    newC.setMark((byte) ((Integer) c[1]).intValue());
                    panelDataComponents.put(newC, entry.getValue());
                }
            }
        }
        return panelDataComponents;
    }

    private ByteArrayOutputStream writeIfExist(ByteArrayOutputStream out, Component c) {
        try {
            if (this.panelDataComponents.containsKey(c)) {
                out.write(c.getMark());
                out.write(panelDataComponents.get(c).getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public byte[] buildFrame() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            // First write the caller ID components
            out = writeIfExist(out, Component.CALLER_ID_OTHER);
            out = writeIfExist(out, Component.CALLER_ID_PHONE);
            out = writeIfExist(out, Component.CALLER_ID_NAME);
            out = writeIfExist(out, Component.BAD_DATA);

            // Caller ID data sent to automation only if call has "No Data Received"
            boolean containsCallerID = out.size() > 0;
            if (!containsCallerID) {
                if (panelDataComponents.containsKey(Component.ACCOUNT)) {
                    out.write(Component.ACCOUNT.getMark());
                    out.write(panelDataComponents.get(Component.ACCOUNT).getBytes());
                }
                for (Event e : mEvents) {
                    out.write(0x22);
                    out.write(e.build());
                }
            }
            // At the end write the long call and the listen in components
            writeIfExist(out, Component.LONG_CALL_INDICATOR);
            writeIfExist(out, Component.LISTEN_IN_INDICATOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }
}

