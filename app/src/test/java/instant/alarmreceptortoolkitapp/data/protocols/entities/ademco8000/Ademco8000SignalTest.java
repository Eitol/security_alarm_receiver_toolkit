package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by hector on 15/06/17.
 */
public class Ademco8000SignalTest {

    @Test
    public void test_buildFrame_HeartBeat() throws Exception {
        /* Based on the table 8-12 of Mx8000 manual */
        Signal signal = new Ademco8000Signal(MsgType.HEART_BEAT);
        assertThat(signal.validateProperties(), is(false));

        signal.addProperty(Component.RECEIVER, "01");
        assertThat(signal.validateProperties(), is(true));

        signal.addProperty(Component.DATE, "051997");
        signal.addProperty(Component.TIME, "074905");
        signal.addProperty(Component.REF, "00000");
        byte[] frame = signal.buildFrame();
        //                 051997"074905"01"0000"d
        byte[] expected = "\u0003051997\"074905\"01\"0000\"d\r".getBytes();
        assertTrue(Arrays.equals(frame, expected));
    }

    @Test
    public void test_buildFrame_System() throws Exception {
        /* Based on the table 8-10 of Mx8000 manual
          Based on: System Receiver #: 2 Reference #: 1917
           Pag 10 of the "Sample MX8000 Decimal Display" Document
         */
        Signal signal = new Ademco8000Signal(MsgType.SYSTEM);
        assertThat(signal.validateProperties(), is(false));

        byte[] code = {'q'};
        Ademco8000SystemEvent e = new Ademco8000SystemEvent(code);
        e.addComponent(Component.LINE, "01");
        assertThat(signal.validateProperties(), is(false));
        signal.addEvent(e);
        assertThat(signal.validateProperties(), is(false));
        signal.addProperty(Component.RECEIVER, "02");
        assertThat(signal.validateProperties(), is(true));
        signal.addProperty(Component.DATE, "012203");
        signal.addProperty(Component.TIME, "042401");
        signal.addProperty(Component.REF, "1917");
        byte[] frame = signal.buildFrame();
        byte[] expected = "\u0002012203\"042401\"02\"1917\"q01c\r".getBytes();
        assertTrue(Arrays.equals(frame, expected));
    }

    @Test
    public void test_buildFrame_Call() throws Exception {
        /*
         *  Signal:
         *  &070211"083645"22"9989"050211447"18140101002"18357001001w
         */
        Ademco8000Signal signal = new Ademco8000Signal(MsgType.CALL);
        assertThat(signal.validateProperties(), is(false));
        signal.addProperty(Component.DATE, "070211");
        signal.addProperty(Component.TIME, "083645");
        signal.addProperty(Component.RECEIVER, "22");
        signal.addProperty(Component.REF, "9989");
        String fmt = "050";
        signal.addProperty(Component.FMT, fmt);
        signal.addProperty(Component.LINE, "21");

        List<Event> events = new ArrayList<>();
        Ademco8000CallEvent e1 = new Ademco8000CallEvent(fmt);
        EventCode ec1 = new EventCode("401".getBytes());
        e1.setCode(ec1);
        e1.setQualifier(Event.Qualifier.NEW);
        e1.addComponent(Component.PARTITION, "01");
        e1.addComponent(Component.ZONE_OR_USER, "002");
        events.add(e1);

        Ademco8000CallEvent e2 = new Ademco8000CallEvent(fmt);
        EventCode ec2 = new EventCode("570".getBytes());
        e2.setCode(ec2);
        e2.setQualifier(Event.Qualifier.REST);
        e2.addComponent(Component.PARTITION, "01");
        e2.addComponent(Component.ZONE_OR_USER, "001");
        events.add(e2);

        Map<Component, String> data = new HashMap<>();
        data.put(Component.ACCOUNT, "1447");

        Ademco8000PanelData panelData = new Ademco8000PanelData(data, events);
        signal.addPanelData(panelData);
        byte[] frame = signal.buildFrame();
        byte[] expected = "&070211\"083645\"22\"9989\"05021\u00051447\"18140101002\"18357001001w\r".getBytes();
        assertTrue(Arrays.equals(frame, expected));
    }


    @Test
    public void test_getVByte() throws Exception {
        Signal signal = new Ademco8000Signal(MsgType.HEART_BEAT);
        // 5070211"083503"01"9956"r10 -> expected 'y'
        byte[] testBytes1 = {0x35, 0x02, 0x30, 0x37, 0x30, 0x32, 0x31, 0x31, 0x22, 0x30, 0x38, 0x33, 0x35, 0x30, 0x33, 0x22, 0x30, 0x31, 0x22, 0x39, 0x39, 0x35, 0x36, 0x22, 0x72, 0x31, 0x30};

        // 5&070211"083458"31"9955"050310564"18140101005 -> expected 'i'
        byte[] testBytes2 = {0x35, 0x26, 0x30, 0x37, 0x30, 0x32, 0x31, 0x31, 0x22, 0x30, 0x38, 0x33, 0x34, 0x35, 0x38, 0x22, 0x33, 0x31, 0x22,
                0x39, 0x39, 0x35, 0x35, 0x22, 0x30, 0x35, 0x30, 0x33, 0x31, 0x05, 0x30, 0x35, 0x36, 0x34, 0x22, 0x31, 0x38,
                0x31, 0x34, 0x30, 0x31, 0x30, 0x31, 0x30, 0x30, 0x35};

        byte result1 = signal.makeVByte(testBytes1, true);
        byte result2 = signal.makeVByte(testBytes2, true);

        assertTrue(result1 == 0x79); // char='y' | dec=121
        assertTrue(result2 == 0x69); // char='i' | dec=105
    }

    @Test
    public void test_systemEventIsReadyForBuild() throws Exception {
        byte[] code = {'A'};
        Ademco8000SystemEvent e = new Ademco8000SystemEvent(code);
        List<Component> r = e.getRequiredComponents();
        Assert.assertTrue(!e.isReadyForBuild());
        String receiver = "01";
        e.addComponent(Component.RECEIVER, receiver);
        Assert.assertTrue(!e.isReadyForBuild());
        String line = "01";
        e.addComponent(Component.LINE, line);
        Assert.assertTrue(e.isReadyForBuild());
    }

    @Test
    public void test_systemEventBuild() throws Exception {
        byte[] code = {'q'};
        Ademco8000SystemEvent e = new Ademco8000SystemEvent(code);
        String line = "01";
        e.addComponent(Component.LINE, line);
        byte[] expected = {'q', '0', '1'};
        Assert.assertTrue(Arrays.equals(e.build(), expected));
    }
}