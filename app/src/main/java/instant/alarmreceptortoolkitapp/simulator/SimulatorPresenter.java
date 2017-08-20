package instant.alarmreceptortoolkitapp.simulator;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000Protocol;
import instant.alarmreceptortoolkitapp.global.Constants;
import instant.alarmreceptortoolkitapp.simulator.ui_adapters.UIProtocolAdapter;
import instant.alarmreceptortoolkitapp.simulator.ui_adapters.UIProtocol_Ademco8000_Adapter;

public class SimulatorPresenter implements SimulatorContract.Present {

    private final SimulatorContract.View mView;
    private final SimulatorContract.Model mModel;

    private List<View>  __currentDisabledViews = null;

    public SimulatorPresenter(SimulatorContract.View view) {
        mView = view;
        mModel = new SimulatorModel(this);
    }


    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void onSend() {
        Log.d(Constants.LOG_NAME, "onSend: send->");
        UIProtocolAdapter a = getUIProtocolAdapter(mView.getProtocolName());
        Signal signal = a.extractSignal(mView);
        byte[] frame = null;
        if (signal != null) {
            frame = signal.buildFrame();
        }
        if (frame == null || frame.length == 0) {
            mView.showMessage("You must complete the required fields");
            return;
        }
        mModel.sendSignal(frame);
        String frameS = new String(frame);
        mView.showFrame(frameS);
        mView.addToLog("send -> "+frameS);
    }

    public UIProtocolAdapter getUIProtocolAdapter(String protocolName) {
        if (mView.getProtocolName().equals(Ademco8000Protocol.getProtocolName())) {
            return new UIProtocol_Ademco8000_Adapter();
        }
        throw new Error("Protocol Not supported");
    }

    public void getSignalFromView() {

    }

    @Override
    public void onInfo() {
        mView.loadInfoView(mView.getProtocolName());
    }

    void loadSystemCodes(){

    }

    @Override
    public void onMsgTypeChanged() {
        mView.setCodesByMsgType();
        if (__currentDisabledViews != null) {
            for (View view : __currentDisabledViews) {
                view.setEnabled(true);
            }
        }
        if (__currentDisabledViews == null){
            __currentDisabledViews = new ArrayList<>();
        }else{
            __currentDisabledViews.clear();
        }

        for (View view : mView.getNonRequiredFieldForCurrentMsgType()) {
            if (view.isEnabled()){
                __currentDisabledViews.add(view);
            }
        }
        for (View view : __currentDisabledViews) {
            view.setEnabled(false);
        }
    }

    @Override
    public void onMakeRandomSignalRequest() {
        Random rand = new Random(System.currentTimeMillis());
        int value = 0;
        String newReceptorValue = String.format(Locale.US, "%02d", rand.nextInt(99));
        String newLineValue = String.format(Locale.US, "%02d", rand.nextInt(99));
        String newAccountValue = String.format(Locale.US, "%04d", rand.nextInt(9999));
        String newZoneValue = String.format(Locale.US, "%03d", rand.nextInt(9999));
        String newPartitionValue = String.format(Locale.US, "%02d", rand.nextInt(9999));
        String newRefValue = String.format(Locale.US, "%04d", rand.nextInt(9999));
        mView.setCodeValuePosition(rand.nextInt(mView.getCodeListSize()));
        mView.setReceptorValue(newReceptorValue);
        mView.setLineValue(newLineValue);
        mView.setAccountValue(newAccountValue);
        mView.setZoneOrUsrValue(newZoneValue);
        mView.setPartitionValue(newPartitionValue);
        mView.setRefValue(newRefValue);
    }

    @Override
    public void onDataReceived(String data) {
        mView.addToLog(data);
    }

    @Override
    public void onServerConnextionFail() {
        String msg = "Server connection fail";

        mView.addToLog(msg);
        mView.showMessage(msg);
    }

    @Override
    public void onServerConnextionSuccess() {
        String msg = "Server connection success";
        mView.addToLog(msg);
        mView.showMessage(msg);
    }


}