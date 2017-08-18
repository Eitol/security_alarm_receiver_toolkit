package instant.alarmreceptortoolkitapp.simulator.ui_adapters;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by hector on 17/08/17.
 */
public class UIProtocol_Ademco8000_AdapterTest {
    @Test
    public void getSystemCode() throws Exception {
        String codeAndDesc = "Local Program Begin - b (0x62)";
        byte[] code = UIProtocol_Ademco8000_Adapter.getSystemCode(codeAndDesc);
        assertTrue(code[0] == 0x62);
    }

}