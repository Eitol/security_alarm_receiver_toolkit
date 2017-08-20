package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;

import org.junit.Test;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;

import static junit.framework.Assert.assertTrue;

/**
 * Created by hector on 02/08/17.
 */
public class Ademco8000PanelDataTest {
    @Test
    public void buildFrame() throws Exception {
        Ademco8000CallEvent e = new Ademco8000CallEvent("050");
        EventCode ec = new EventCode("110".getBytes());
        e.setCode(ec);
        e.setQualifier(Event.Qualifier.NEW);
        e.addComponent(Component.QUALIFIER,"1");
        e.addComponent(Component.PARTITION,"95");
        e.addComponent(Component.ZONE_OR_USER,"341");
        String r = new String(e.build());
        assertTrue(r.equals("18111095341"));
    }

}