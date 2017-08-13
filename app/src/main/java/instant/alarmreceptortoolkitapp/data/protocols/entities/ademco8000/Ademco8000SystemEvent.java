package instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000;


import junit.framework.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Event;
import instant.alarmreceptortoolkitapp.data.protocols.entities.EventCode;
import instant.alarmreceptortoolkitapp.global.ArrayAdvancedUtils;


/**
 * System messages originate from the receiver and are sent to the automation computer.
 * The length of the message is dependent on its function of the message.
 * All system messages are sent separately from one another and from other types of messages.
 * A typical system message looks like this:
 * <AE header><System><V-byte><$0D>
 * <p>
 * Created by hector on 19/06/17.
 */

public class Ademco8000SystemEvent extends Event {

    public Ademco8000SystemEvent(byte[] code) {
        Assert.assertTrue(code.length <= 1);
        super.setRequiredComponents(Ademco8000Protocol.getRequiredComponentsByCode(code));
        int idx = ArrayAdvancedUtils.deepIndexOf(Ademco8000Protocol.codes, code);
        EventCode e = new EventCode(Ademco8000Protocol.codes[idx], Ademco8000Protocol.descriptions[idx]);
        super.setCode(e);
    }

    /**
     *
     * @return
     */
    @Override
    public byte[] build() {
        if (!isReadyForBuild()){
            return null;
        }
        List<Component> reqComp = getRequiredComponents();
        Assert.assertTrue(reqComp.size()<=1);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        byte[] code_ = getCode().getCode();
        byte[] c = getComponent(getRequiredComponents().get(0));
        try {
            outputStream.write(code_);
            outputStream.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (int i = 0; i < reqComp.size(); i++) {
//            byte[] c = getComponent(reqComp.get(i));
//            try {
//                outputStream.write(c);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return outputStream.toByteArray();
    }

    @Override
    public Qualifier.Type getQualifierType() {
        return Qualifier.Type.NA;
    }
}

