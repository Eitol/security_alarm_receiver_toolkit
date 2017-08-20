package instant.alarmreceptortoolkitapp.data.protocols.entities.contactid;

import org.junit.Test;

import java.util.List;

import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;

import static org.junit.Assert.*;

/**
 * Created by hector on 30/07/17.
 */
public class ContactIDEventTest {
    @Test
    public void build() throws Exception {

    }

    @Test
    public void getDefaultCodes() throws Exception {
        ContactIDEvent c = new ContactIDEvent();
        List<EventCode> d = new ContactIDProtocol().getDefaulEventCodes();
        assertTrue(d.size() == ContactIDProtocol.codes.length);
        int count = 0;
        for (EventCode e : d) {
            String codeG = new String(e.getCode());
            assertTrue(codeG.equals(ContactIDProtocol.codes[count][0]));
            count++;
        }
    }

}