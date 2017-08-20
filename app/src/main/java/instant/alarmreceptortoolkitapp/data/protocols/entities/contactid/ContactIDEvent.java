package instant.alarmreceptortoolkitapp.data.protocols.entities.contactid;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;

/**
 * Created by hector on 19/06/17.
 */

public class ContactIDEvent extends Event {
    @Override
    public byte[] build() {
        return new byte[0]; // TODO
    }



    @Override
    public Qualifier.Type getQualifierType() {
        return null;
    }

};
