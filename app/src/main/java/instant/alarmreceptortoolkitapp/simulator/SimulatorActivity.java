package instant.alarmreceptortoolkitapp.simulator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import instant.alarmreceptortoolkitapp.R;
import instant.alarmreceptortoolkitapp.simulator.receptor_models.HoneywellMX8000;

public class SimulatorActivity extends AppCompatActivity {

    private SimulatorContract.Present mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor_simulator);
        String symType = getIntent().getStringExtra(SIMULATION_TYPE);
        if (symType.equals("RECEPTOR_MODEL")) {
            String receptor = getIntent().getStringExtra(RECEPTOR_MODEL);
            this.setupReceptorTypeSimulation(receptor);
        } else if (symType.equals("PROTOCOL")) {
            String protocol = getIntent().getStringExtra(PROTOCOL);
            this.setupProtocolTypeSimulation(protocol);
        }
    }

    private void setupReceptorTypeSimulation(String receptorModel) {
        Fragment f;
        switch (receptorModel) {
            case "HoneywellMX8000": {
                f = new HoneywellMX8000();
                break;
            }
            default: {
                throw new Error("Bad receptor name");
            }
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, f);
        transaction.commit();
        // to meet :)
        SimulatorContract.View view = (SimulatorContract.View)f;
        mPresenter = new SimulatorPresenter(view);
        ((SimulatorContract.View) f).setPresenter(mPresenter);
    }

    private void setupProtocolTypeSimulation(String receptorModel) {

    }

    /****** CONSTANTS ********/
    public static final String SIMULATION_TYPE = "SIMULATION_TYPE";
    public static final String RECEPTOR_MODEL = "RECEPTOR_MODEL";
    public static final String PROTOCOL = "PROTOCOL";
}
