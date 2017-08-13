package instant.alarmreceptortoolkitapp.simulator.selectors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import instant.alarmreceptortoolkitapp.R;

public class SimulationTypeSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_type_selector);
    }

    public void choiceSimulationTypeByReceptorModel(View v){
        Intent intent = new Intent(this, ReceptorModelSelectorActivity.class);
        startActivity(intent);
    }
}
