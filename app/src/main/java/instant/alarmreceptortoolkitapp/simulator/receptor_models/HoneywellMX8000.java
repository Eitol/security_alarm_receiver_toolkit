package instant.alarmreceptortoolkitapp.simulator.receptor_models;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import instant.alarmreceptortoolkitapp.R;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000Protocol;
import instant.alarmreceptortoolkitapp.global.activities.InfoActivity;
import instant.alarmreceptortoolkitapp.simulator.SimulatorContract;


public class HoneywellMX8000 extends Fragment implements SimulatorContract.View {


    private SimulatorContract.Present mPresenter;

    public HoneywellMX8000() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_honeywell_mx8000, container, false);
        ButterKnife.bind(this, view);
        log.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void setPresenter(SimulatorContract.Present presenter) {
        this.mPresenter = presenter;
    }



    @Override
    public void clearLog() {
        receptor.setText("");
        line.setText("");
        account.setText("");
        ref.setText("");
        zoneUsr.setText("");
        partition.setText("");
        badDataText.setText("");
        listenInDuration.setText("");
        this.log.setText("");
    }

    @Override
    public void addToLog(String s) {
        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        // Scroll to the end
                        if(log != null){
                            log.append(s + "\n");
                            final Layout layout = log.getLayout();
                            if(layout != null){
                                int scrollDelta = layout.getLineBottom(log.getLineCount() - 1)
                                        - log.getScrollY() - log.getHeight();
                                if(scrollDelta > 0)
                                    log.scrollBy(0, scrollDelta);
                            }
                        }
                    }
                });
    }

    @Override
    public void showMessage(String msg) {
        getActivity().runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(getActivity().findViewById(android.R.id.content),
                                msg, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View[] getNonRequiredFieldForCurrentMsgType() {
        if (rbHeartBeat.isChecked()) {
            return new View[]{
                    fmt, code, buttonRandom, account, insertBadData,
                    insertCallerDataInfo, insertBadData, badDataText, zoneUsr,
                    insertCallerDataInfo, partition, listenInDuration, insertListenIn
            };
        }
        if (rbSystem.isChecked()) {
            return new View[]{
                    fmt, account, insertBadData, insertCallerDataInfo,
                    insertBadData, badDataText, zoneUsr, partition, listenInDuration, insertListenIn};
        }
        // rbCall.isChecked()
        return new View[]{};
    }

    @Override
    public void setReceptorValue(String value) {
        receptor.setText(value);
    }

    @Override
    public void setLineValue(String value) {
        line.setText(value);
    }

    @Override
    public void setAccountValue(String value) {
        account.setText(value);
    }

    @Override
    public void setPartitionValue(String value) {
        partition.setText(value);
    }

    @Override
    public void setZoneOrUsrValue(String value) {
        zoneUsr.setText(value);
    }

    @Override
    public void setRefValue(String value) {
        ref.setText(value);
    }

    @Override
    public void showFrame(String value) {
        signalFrame.setText(value);
    }

    @Override
    public void setCodeValuePosition(int pos) {
        code.setSelection(pos);
    }

    @Override
    public void onInputChange() {

    }

    @Override
    public String getFMT() {
        return fmt.getSelectedItem().toString();
    }

    @Override
    public String getReceptor() {
        return receptor.getText().toString();
    }

    @Override
    public String getRef() {
        return ref.getText().toString();
    }

    @Override
    public String getLine() {
        return line.getText().toString();
    }

    @Override
    public String getAccount() {
        return account.getText().toString();
    }

    @Override
    public String getCode() {
        return code.getSelectedItem().toString();
    }

    @Override
    public String getZoneOrUsr() {
        return zoneUsr.getText().toString();
    }

    @Override
    public String getPartition() {
        return partition.getText().toString();
    }

    @Override
    public String getProtocolName() {
        return Ademco8000Protocol.getProtocolName();
    }

    @Override
    public String getBadData() {
        return badDataText.getText().toString();
    }

    @Override
    public String getListenInDuration() {
        return listenInDuration.getText().toString();
    }

    @Override
    public boolean insertBadData() {
        return insertBadData.isChecked();
    }

    @Override
    public boolean insertCallerInfo() {
        return insertCallerDataInfo.isChecked();
    }

    @Override
    public boolean insertBadChecksum() {
        return badCheksum.isChecked();
    }

    @Override
    public boolean insertListenIn() {
        return insertListenIn.isChecked();
    }

    void setCodeByResource(final int r) {
        String[] array = getResources().getStringArray(r);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        code.setAdapter(spinnerArrayAdapter);
        code.refreshDrawableState();
    }

    @Override
    public void setCodesByMsgType() {
        if (rbCall.isChecked()) {
            if (getFMT().split(" - ")[0].equals("050")) {
                setCodeByResource(R.array.codes_CONTACT_ID);
            }
        }
        if (rbSystem.isChecked()) {
            setCodeByResource(R.array.codes_SYSTEM_ADEMCO8000);
        }
    }

    @Override
    public int getCodeListSize() {
        return code.getAdapter().getCount();
    }


    @Override
    public MsgType getMsgType() {
        if (rbCall.isChecked()) {
            return MsgType.CALL;
        }
        if (rbHeartBeat.isChecked()) {
            return MsgType.HEART_BEAT;
        }
        if (rbSystem.isChecked()) {
            return MsgType.SYSTEM;
        }
        throw new Error("BAD Message Type");
    }

    @Override
    public void loadInfoView(String protocolOrReceiverName) {
        Intent intent = new Intent(this.getContext(), InfoActivity.class);
        intent.putExtra(InfoActivity.PROTOCOL_OR_RECEIVER_NAME, protocolOrReceiverName);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.rb_call, R.id.rb_system, R.id.rb_heartBeat, R.id.buttonRandom,
            R.id.buttonSend, R.id.buttonInfo, R.id.buttonClear, R.id.insertBadData,
            R.id.insertCallerDataInfo, R.id.insertListenIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_call:
            case R.id.rb_system:
            case R.id.rb_heartBeat: {
                mPresenter.onMsgTypeChanged();
                break;
            }
            case R.id.buttonRandom: {
                mPresenter.onMakeRandomSignalRequest();
                break;
            }
            case R.id.buttonSend: {
                mPresenter.onSend();
                break;
            }
            case R.id.buttonInfo: {
                mPresenter.onInfo();
                break;
            }
            case R.id.buttonClear: {

                this.clearLog();
                break;
            }
            case R.id.insertBadData: {
                this.badDataText.setEnabled(insertBadData());
                break;
            }

            case R.id.insertListenIn: {
                this.listenInDuration.setEnabled(insertListenIn.isChecked());
                break;
            }
            case R.id.insertCallerDataInfo: {
                this.fmt.setEnabled(insertBadData());
                this.code.setEnabled(insertBadData());
                this.account.setEnabled(insertBadData());
                this.partition.setEnabled(insertBadData());
                break;
            }
        }
    }

    /*** UI ***/
    @BindView(R.id.rb_call)
    RadioButton rbCall;
    @BindView(R.id.rb_system)
    RadioButton rbSystem;
    @BindView(R.id.rb_heartBeat)
    RadioButton rbHeartBeat;
    @BindView(R.id.fmt)
    Spinner fmt;
    @BindView(R.id.buttonRandom)
    Button buttonRandom;
    @BindView(R.id.code)
    Spinner code;
    @BindView(R.id.line)
    EditText line;
    @BindView(R.id.receptor)
    EditText receptor;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.signal_frame)
    TextView signalFrame;
    @BindView(R.id.buttonSend)
    Button buttonSend;
    @BindView(R.id.buttonClear)
    Button buttonClear;
    @BindView(R.id.buttonInfo)
    Button buttonInfo;
    @BindView(R.id.log)
    TextView log;
    @BindView(R.id.ref)
    EditText ref;
    @BindView(R.id.zone_usr)
    EditText zoneUsr;
    @BindView(R.id.partition)
    EditText partition;
    @BindView(R.id.insertBadData)
    CheckBox insertBadData;
    @BindView(R.id.insertCallerDataInfo)
    CheckBox insertCallerDataInfo;
    @BindView(R.id.badDataText)
    EditText badDataText;
    @BindView(R.id.badCheksum)
    CheckBox badCheksum;
    @BindView(R.id.listenInDuration)
    EditText listenInDuration;
    @BindView(R.id.insertListenIn)
    CheckBox insertListenIn;
}

