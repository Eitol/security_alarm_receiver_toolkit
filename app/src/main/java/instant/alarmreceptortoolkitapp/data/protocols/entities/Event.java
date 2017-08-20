package instant.alarmreceptortoolkitapp.data.protocols.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hector on 18/06/17.
 */

public abstract class Event {
    private Map<Component, String> mComponents = new HashMap<Component, String>();
    private EventCode code = null;
    private Event.Qualifier qualifier = null;

    public List<Component> getRequiredComponents() {
        return requiredComponents;
    }

    public void setRequiredComponents(List<Component> requiredComponents) {
        this.requiredComponents = requiredComponents;
    }

    private List<Component> requiredComponents;

    /**
     * This method build the event and return the byte array representation of this
     *
     * @return
     */
    public abstract byte[] build();

    /***
     * Indicate in the event is ready for build
     * @return
     */
    public boolean isReadyForBuild() {
        List<Component> requiredComponents = getRequiredComponents();
        if (requiredComponents == null || requiredComponents.size() == 0){
            return true;
        }
        for (Component c: requiredComponents) {
            if (!mComponents.containsKey(c)){
                return false;
            }
        }
        return true;
    }



    /**
     * Return the type of qualifier to be used fot the event
     *
     * @return
     */
    public abstract Qualifier.Type getQualifierType();

    public Qualifier getQualifier() {
        return qualifier;
    }

    public void setQualifier(Qualifier qualifier) {
        this.qualifier = qualifier;
    }

    public EventCode getCode() {
        return this.code;
    }

    public void setCode(EventCode code) {
        this.code = code;
    }


    public byte[] getComponentValue(Component c) {
        return mComponents.get(c).getBytes();
    }

    public void addComponent(Component c, String value) {
        this.mComponents.put(c, value);
    }


    public enum Qualifier {
        NA(""),
        NEW("1"),
        REST("3"),
        PREVIOUS("3");
        private final String value;

        Qualifier(String q) {
            this.value = q;
        }

        public String toNumeric() {
            return value;
        }

        public String toAlphabetical() {
            switch (value) {
                case "1":
                    return "E";
                case "3":
                    return "R";
                case "6":
                    return "P";
            }
            throw new Error("Invalid qualifier " + value);
        }

        public enum Type {
            NUMERIC,
            ALPHABETIC,
            NA
        }
    }

}
