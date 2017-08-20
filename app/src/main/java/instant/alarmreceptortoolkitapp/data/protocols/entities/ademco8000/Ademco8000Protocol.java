package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Codification;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Protocol;
import instant.alarmreceptortoolkitapp.global.ArrayAdvancedUtils;

/**
 * Created by hector on 30/07/17.
 */

public class Ademco8000Protocol implements Protocol {

    @Override
    public List<EventCode> getDefaulEventCodes() {
        return null;
    }

    @Override
    public List<EventCode> getDefaulSystemCodes() {
        List<EventCode> ev = new ArrayList<>();
        List<byte[]> codes = getCodeList();
        List<String> descr = getCodeDescriptonList();
        for (int i = 0; i < codes.size(); i++) {
            EventCode e = new EventCode(codes.get(i), descr.get(i));
            ev.add(e);
        }
        return ev;
    }


    public static String getProtocolName() {
        return "Ademco 8000";
    }

    private static List<byte[]> getCodeList() {
        return Arrays.asList(Ademco8000Protocol.codes);
    }

    private static List<String> getCodeDescriptonList() {
        return Arrays.asList(Ademco8000Protocol.descriptions);
    }

    /**
     * Return the required component by code
     *
     * @param code: e.g: 'A'
     * @apiNote Based on Table 8–11: System Messages. Pag 126
     */
    public static List<Component> getRequiredComponentsBySystemCode(byte[] code) {
        final byte[][] reqLC = {{'A'}, {'B'}, {'C'}, {'D'}, {'E'}, {'F'}, {'n'},
                {'o'}, {'p'}, {'q'}, {'r'}, {'s'}, {'t'}};
        final byte[][] reqRCVR = {{'y'}, {'z'}};
        final byte[][] reqUSR = {{'b'}, {'c'}, {'d'}, {'e'}, {'f'}, {'~'}, {0x7F}};

        List<Component> r = new ArrayList<>();
        Component c = null;

        if (ArrayAdvancedUtils.deepIndexOf(reqLC, code) != -1) {
            c = Component.LINE;
        }
        if (ArrayAdvancedUtils.deepIndexOf(reqUSR, code) != -1) {
            c = Component.USER;
        }
        if (ArrayAdvancedUtils.deepIndexOf(reqRCVR, code) != -1) {
            c = Component.RECEIVER_MODEL;
        }
        if (c != null) {
            c.setCodification(Codification.ASCII);
            c.setLen(2);
            r.add(c);
        }
        return r;
    }

    public final static byte[][] codes = {
            {'A'}, {'B'}, {'C'}, {'D'}, {'E'}, {'F'}, {'a'}, {'b'}, {'c'}, {'d'}, {'e'},
            {'f'}, {'g'}, {'h'}, {'i'}, {'j'}, {'k'}, {'l'}, {'m'}, {'n'}, {'o'}, {'p'},
            {'q'}, {'r'}, {'s'}, {'t'}, {'w'}, {'x'}, {'y'}, {'z'}, {'{'}, {'}'}, {'~'}, {0x7F}
    };

    public final static String[] descriptions = {
            "Common Listen-in Begin",
            "Common Listen-in End",
            "Common Listen-in Extended",
            "PBX Listen-in Begin",
            "PBX Listen-in Busy",
            "Manually Aborted Call",
            "System Power Up",
            "Local Program Begin",
            "Local Program End",
            "Local Program Fail",
            "System Date Change",
            "System Time Change",
            "Message Queue Above Warning",
            "Message Queue Below Warning",
            "Message Queue Full",
            "Message Queue Full Restore",
            "Printer Off Line",
            "Printer Paper Out",
            "Printer Restore",
            "LinePort or Slave Added",
            "LinePort or Slave Deleted",
            "LinePort or Slave Trouble",
            "Phone Line Trouble",
            "LinePort or Slave Trouble Restore",
            "Phone Line Restore",
            "Line Card Modified",
            "AC Lost",
            "AC Restore",
            "Battery/DC Trouble",
            "Battery/DC Trouble Restore",
            "Computer Trouble",
            "Computer Restore",
            "Log Off Operator",
            "Log On Operator"
    };


    /**
     * Based on: "Table 8–5: Dialer Format Types By Code"
     */
    public final static Object[][] fmtList = {
            {"004", "SIA DCS"},
            {"009", "ITI"},
            {"010", "ITI SX-IVA"},
            {"011", "ITI PinPoint"},
            {"012", "ITI RF Commander"},
            {"013", "ITI Pro 1 or ITI Ultragard"},
            {"014", "ITI CareTaker +"},
            {"015", "ITI SX-V"},
            {"016", "ITI Commander 2000"},
            {"017", "ITI HarborGuard"},
            {"018", "ITI Reserved or ITI Simon"},
            {"019", "ITI Vector"},
            {"020", "ITI Hardwire Commander"},
            {"021", "ITI SX-V Special"},
            {"022", "ITI Marsden"},
            {"023", "ITI Network Sec"},
            {"024", "ITI Nutone"},
            {"025", "ITI SX-IVB"},
            {"030", "Pulse Tone 3/1"},
            {"031", "Sescoa Franklin 3/1"},
            {"032", "ADEMCO/Silent Knight 3/1"},
            {"033", "Extended 3/1"},
            {"035", "Pulse tone 4/1"},
            {"036", "Sescoa Franklin 4/1"},
            {"037", "ADEMCO/Silent Knight 4/1"},
            {"038", "Extended 4/1"},
            {"040", "Pulse Tone 4/2"},
            {"041", "ADEMCO/Silent Knight 4/2"},
            {"043", "Extended 4/2"},
            {"046", "Radionics 3/1"},
            {"047", "Radionics 3/1 with Checksum"},
            {"048", "Radionics 4/2 with Checksum"},
            {"049", "Sescoa Superspeed"},
            {"050", "Contact ID", new Component[]{Component.PARTITION, Component.ZONE_OR_USER}},
            {"051", "ADEMCO TouchTone"},
            {"052", "Acron TouchTone"},
            {"053", "Westec TouchTone"},
            {"054", "ADEMCO Express"},
            {"055", "ADEMCO High Speed (SIA D1) output as SIA events"},
            {"056", "ADEMCO High Speed with checksum (SIA D1) output as SIA events"},
            {"057", "ADEMCO DTMF 4/2 with checksum"},
            {"058", "ADEMCO DTMF 4/1 with checksum"},
            {"059", "Acron TouchTone 4 digit account."},
            {"060", "BFSK"},
            {"061", "Silent Knight FSK0/FSK80"},
            {"062", "Silent Knight FSK1/FSK81"},
            {"063", "Silent Knight FSK2/FSK86"},
            {"064", "FBII 4/3/1 with checksum or FBII 4/1/2/1 with checksum"},
            {"065", "FBII 4/3/1 or FBII 4/1/2/1"},
            {"070", "Westec 871"},
            {"071", "Westec W970"},
            {"072", "Westec W1000, W2000, W3000"},
            {"073", "Westec 45K"},
            {"074", "Modem II"},
            {"075", "Modem IIe"},
            {"080", "Pulse 32"},
            {"081", "Pulse 32 CS"},
            {"082", "Pulse 41 CS"},
            {"083", "ADEMCO Contact ID® 10-digit Account"},
            {"100", "ITI Simon U"},
            {"101", "Varitech FSK 4/1"},
            {"102", "Varitech FSK 4/2"},
            {"103", "Ademco High Speed output in high speed raw data (ADEMCO format) "},
            {"104", "Ademco High Speed with checksum output in high speed raw data (ADEMCO format)"}};
}
