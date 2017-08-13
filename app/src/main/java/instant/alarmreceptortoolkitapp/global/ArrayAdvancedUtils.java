package instant.alarmreceptortoolkitapp.global;

import com.github.underscore.$;

/**
 * Created by hector on 26/07/17.
 */

public class ArrayAdvancedUtils {



    public static int deepIndexOf(final byte[][] list, byte[] e) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i].length != e.length) {
                    continue;
                }
                if (list[i][j] == e[0]) {
                    return i;
                }
            }
        }
        return -1;
    }
}
