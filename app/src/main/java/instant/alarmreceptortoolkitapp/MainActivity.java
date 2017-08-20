package instant.alarmreceptortoolkitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import instant.alarmreceptortoolkitapp.simulator.selectors.SimulationTypeSelectorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSimulationImageClick(View view){
        Intent intent = new Intent(this, SimulationTypeSelectorActivity.class);
        startActivity(intent);
    }
}
