package instant.alarmreceptortoolkitapp.simulator;

import android.util.Log;
import android.view.View;

import java.util.Locale;
import java.util.Random;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.global.Constants;

public class SimulatorPresenter implements SimulatorContract.Present {

    private final SimulatorContract.View mView;
    private final SimulatorContract.Model mModel;

    private View[] __currentDisabledViews = null;

    public SimulatorPresenter(SimulatorContract.View view) {
        mView = view;
        mModel = new SimulatorModel(this);
    }


    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void onSend(Signal signal) {
        Log.d(Constants.LOG_NAME, "onSend: send->");
        mModel.sendSignal(signal);
    }

    @Override
    public void onInfo() {

    }

    @Override
    public void onMsgTypeChanged() {
        if (__currentDisabledViews != null){
            for (View view : __currentDisabledViews) {
                view.setEnabled(true);
            }
        }
        __currentDisabledViews = mView.getNonRequiredFieldForCurrentMsgType();
        for (View view : __currentDisabledViews) {
            view.setEnabled(false);
        }
    }

    @Override
    public void onMakeRandomSignalRequest() {
        Random rand = new Random(System.currentTimeMillis());
        int value = 0;
        String newReceptorValue = String.format(Locale.US,"%02d", rand.nextInt(99));
        String newLineValue = String.format(Locale.US,"%02d", rand.nextInt(99));
        String newAccountValue = String.format(Locale.US,"%04d", rand.nextInt(9999));
        mView.setCodeValuePosition(rand.nextInt(mView.getCodeListSize()));
        mView.setReceptorValue(newReceptorValue);
        mView.setLineValue(newLineValue);
        mView.setAccountValue(newAccountValue);
    }

    @Override
    public void onDataReceived(String data) {
        mView.addToLog(data);
    }

    @Override
    public void onServerConnextionFail() {
        mView.showMessage("Server connection fail", 4);
    }

}