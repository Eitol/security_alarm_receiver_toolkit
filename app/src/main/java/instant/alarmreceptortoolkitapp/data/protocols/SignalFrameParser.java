package instant.alarmreceptortoolkitapp.data.protocols;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;

/**
 * Created by hector on 28/05/17.
 */

public interface SignalFrameParser {
    Signal parseSignalFrame(String frame);
}
