package instant.alarmreceptortoolkitapp.emulator.selectors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import instant.alarmreceptortoolkitapp.R;
import instant.alarmreceptortoolkitapp.emulator.SimulatorActivity;

public class ReceptorModelSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receptor_model_selector);
    }

    public void onReceptorMx8000Click(View view){
        openReceptorSimulationActivity("HoneywellMX8000");
    }


    private void openReceptorSimulationActivity(String receptorModel){
        Intent intent = new Intent(this, SimulatorActivity.class);
        intent.putExtra(SimulatorActivity.SIMULATION_TYPE, SimulatorActivity.RECEPTOR_MODEL);
        intent.putExtra(SimulatorActivity.RECEPTOR_MODEL, receptorModel);
        startActivity(intent);
    }
}
