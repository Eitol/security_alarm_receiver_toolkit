package instant.alarmreceptortoolkitapp.global;

import junit.framework.Assert;

import org.junit.Test;

import java.lang.reflect.Method;

import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000EventFormater;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000PanelData;

/**
 * Created by hector on 26/07/17.
 */
public class ArrayAdvancedUtilsTest {

    private final static byte[][] codes = {
            {'A'}, {'B'}, {'C'}, {'D'}, {'E'}, {'F'}, {'a'}, {'b'}, {'c'}, {'d'}, {'e'},
            {'f'}, {'g'}, {'h'}, {'i'}, {'j'}, {'k'}, {'l'}, {'m'}, {'n'}, {'o'}, {'p'},
            {'q'}, {'r'}, {'s'}, {'t'}, {'w'}, {'x'}, {'y'}, {'z'}, {'{'}, {'}'}, {'~'}, {0x7F}
    };

    @Test
    public void deepIndexOf() throws Exception {
        byte[] codeA = {'A'};
        byte[] codeHome = {'~'};
        Assert.assertTrue(ArrayAdvancedUtils.deepIndexOf(codes, codeA) == 0);
        Assert.assertTrue(ArrayAdvancedUtils.deepIndexOf(codes, codeHome) == 32);
    }

    @Test
    public void someTest() throws Exception {
        System.out.println("ASDasdsad");
        Ademco8000EventFormater o = new Ademco8000EventFormater();
        Method method = Ademco8000EventFormater.class.getMethod("format050", String.class);
        method.invoke(o,"asdasd");
//            method.invoke("asdasd");

        // Dynamically do stuff with this class
        // List constructors, fields, methods, etc.

    }

}