package instant.alarmreceptortoolkitapp.data.protocols.entities;

/**
 * Created by hector on 18/06/17.
 */

/**
 * Model a signal msg component
 */
public enum Component {
    RECEIVER("rcvr"),
    RECEIVER_MODEL("rcvr_model"),
    LINE("line"),
    MSG_TYPE("msg_type"),
    DATE("rcvr"),
    TIME("time"),
    FMT("fmt"),
    REF("ref"),
    ID("id"),
    BOM("bom"),


    /** PANEL DATA **/
    PANEL_DATA_BODY("panel_data"),
    ACCOUNT("account"),
    CODE("code"),
    ZONE("zone"),
    USER("user"),
    ZONE_OR_USER("zone_or_user"),
    QUALIFIER("qualifier"),
    PARTITION("partition"),
    DESCRIPTION("description"),
    EVENT_FIELD_BAD_DATA("efbd"),
    EVENT_FIELD_GOOD_DATA("efgd"),
    CALLER_ID_NAME("c_name"),
    CALLER_ID_PHONE("c_phone"),
    CALLER_ID_OTHER("c_other"),
    LISTEN_IN_INDICATOR("lii"),
    LONG_CALL_INDICATOR("longci");

    private final String comp;
    private Codification codification;
    private int len;

    private byte mark;

    public Codification getCodification() {
        return codification;
    }

    public void setCodification(Codification codification) {
        this.codification = codification;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String str() {
        return comp;
    }

    Component(final String text) {
        this.comp = text;
        codification = Codification.ASCII;
        this.len = 0;
    }

    public static Component build(Component c,int len, Codification cod){
        c.setLen(len);
        c.setCodification(cod);
        return c;
    }

    public static Component build(Component c, int len){
        c.setLen(len);
        return c;
    }

    public byte getMark() {
        return mark;
    }

    public void setMark(byte mark) {
        this.mark = mark;
    }
}
