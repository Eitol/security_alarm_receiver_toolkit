package instant.alarmreceptortoolkitapp.simulator.ui_adapters;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.simulator.SimulatorContract;

/**
 * Created by hector on 13/08/17.
 */

public interface UIProtocolAdapter {
    Signal extractSignal(SimulatorContract.View view);
}
