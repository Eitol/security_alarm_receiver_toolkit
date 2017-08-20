package instant.alarmreceptortoolkitapp.data.protocols.entities;

/**
 * Created by hector on 22/06/17.
 */

public class EventCode {
    private byte [] code;
    private String description;

    public EventCode(byte[] code, String description) {
        this.code = code;
        this.description = description;
    }

    public EventCode(byte[] code) {
        this.code = code;
        this.description = null;
    }

    public EventCode() {
        this.code = null;
        this.description = null;
    }

    public byte[] getCode() {
        return code;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
