package instant.alarmreceptortoolkitapp.emulator.ui_adapters;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.emulator.SimulatorContract;

/**
 * Created by hector on 13/08/17.
 */

public interface UIProtocolAdapter {
    Signal extractSignal(SimulatorContract.View view);
}
