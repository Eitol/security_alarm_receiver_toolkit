package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;


import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;


/**
 * System messages originate from the receiver and are sent to the automation computer.
 * The length of the message is dependent on its function of the message.
 * All system messages are sent separately from one another and from other types of messages.
 * A typical system message looks like this:
 * <AE header><System><V-byte><$0D>
 * <p>
 * Created by hector on 19/06/17.
 */

public class Ademco8000CallEvent extends Event {
    private final String fmt;

    public Ademco8000CallEvent(String fmt) {
        this.fmt = fmt;
        super.setRequiredComponents(Ademco8000EventFormater.getRequiredComponentsByFMT(fmt));
    }

    @Override
    public byte[] build() {
        return Ademco8000EventFormater.format(this,fmt);
    }

    public byte[] buildFromParams() {
        return Ademco8000EventFormater.format(this,fmt);
    }

    @Override
    public Qualifier.Type getQualifierType() {
        return null;
    }
}

