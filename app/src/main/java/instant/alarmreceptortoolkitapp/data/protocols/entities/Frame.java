package instant.alarmreceptortoolkitapp.data.protocols.entities;

/**
 * Created by hector on 29/07/17.
 */

public interface Frame {
    /**
     * Build a signal frame from current object
     *
     * @return Return the signal frame in  form of array bytes
     */
    byte[] buildFrame();
}
