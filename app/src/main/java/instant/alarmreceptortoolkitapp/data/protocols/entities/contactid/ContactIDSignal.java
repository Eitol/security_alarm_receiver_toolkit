package instant.alarmreceptortoolkitapp.data.protocols.entities.contactid;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;

/**
 * Created by hector on 28/05/17.
 */

public class ContactIDSignal extends Signal {

    @Override
    public String toString() {
        // TODO
        return "asdasd";
    }

    @Override
    public byte[] buildFrame() {
        return null;
    }

    @Override
    public Component[] getRequiredFields(MsgType msg_type) {
        return new Component[0];
    }

    @Override
    public Component[] getRequiredFields() {
        return new Component[0];
    }
}
