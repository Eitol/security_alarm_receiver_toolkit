package instant.alarmreceptortoolkitapp.data.protocols.entities;

import java.util.List;

/**
 * Created by hector on 30/07/17.
 */

public abstract class Protocol {
    /**
     * Return the list of codes for the protocol of the event
     *
     * @return
     */
    public abstract List<EventCode> getDefaulEventCodes();

    /**
     * Return the list of codes of the system event
     *
     * @return
     */
    public abstract List<EventCode> getDefaulSystemCodes();
}
