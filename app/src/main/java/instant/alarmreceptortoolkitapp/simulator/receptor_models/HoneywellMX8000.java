package instant.alarmreceptortoolkitapp.simulator.receptor_models;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import instant.alarmreceptortoolkitapp.R;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Component;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.data.protocols.entities.ademco8000.Ademco8000Signal;
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
        return view;
    }

    @Override
    public void setPresenter(SimulatorContract.Present presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void addToLog(String s) {
        this.log.setText(this.log.getText() + "\n" + s);
    }

    @Override
    public void clearLog() {
        this.log.setText("");
    }

    @Override
    public void showMessage(String msg, double duration) {

    }

    @Override
    public Signal getSignal() {
        Signal signal = new Ademco8000Signal(getMsgType());
        signal.addProperty(Component.FMT, fmt.getSelectedItem().toString());
        signal.addProperty(Component.CODE, code.getSelectedItem().toString());
        signal.addProperty(Component.RECEIVER, receptor.getText().toString());
        signal.addProperty(Component.LINE, line.getText().toString());
        signal.addProperty(Component.ACCOUNT, account.getText().toString());
        return signal;
    }

    @Override
    public View[] getNonRequiredFieldForCurrentMsgType() {
        if (rbHeartBeat.isChecked()){
            return new View[]{fmt, code, buttonRandom,account};
        }
        if (rbSystem.isChecked()){
            return new View[]{fmt};
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
    public void setCodeValuePosition(int pos) {
        code.setSelection(pos);
    }

    @Override
    public int getCodeListSize() {
        return code.getAdapter().getCount();
    }



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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.rb_call, R.id.rb_system, R.id.rb_heartBeat, R.id.buttonRandom,
            R.id.buttonSend, R.id.buttonInfo, R.id.buttonClear})
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
                mPresenter.onSend(getSignal());
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
    EditText log;


}

