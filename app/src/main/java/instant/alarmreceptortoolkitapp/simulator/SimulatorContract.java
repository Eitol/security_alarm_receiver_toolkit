package instant.alarmreceptortoolkitapp.simulator;

import instant.alarmreceptortoolkitapp.base.BaseModel;
import instant.alarmreceptortoolkitapp.base.BasePresenter;
import instant.alarmreceptortoolkitapp.base.BaseView;
import instant.alarmreceptortoolkitapp.data.protocols.entities.MsgType;

public interface SimulatorContract {
    interface View extends BaseView<Present> {
        void addToLog(String s);
        void clearLog();
        void showMessage(String msg);
        android.view.View[] getNonRequiredFieldForCurrentMsgType();
        void setReceptorValue(String value);
        void setLineValue(String value);
        void setAccountValue(String value);
        void setPartitionValue(String value);
        void setZoneOrUsrValue(String value);
        void setRefValue(String value);
        void showFrame(String value);
        void setCodeValuePosition(int pos);
        void onInputChange();

        String getFMT();
        String getReceptor();
        String getRef();
        String getLine();
        String getAccount();
        String getCode();
        String getZoneOrUsr();
        String getPartition();
        String getProtocolName();
        String getBadData();

        boolean insertBadData();
        boolean insertCallerInfo();
        boolean insetBadChecksum();

        void setCodesByMsgType();
        int getCodeListSize();
        MsgType getMsgType();
    }

    interface Present extends BasePresenter {
        // View -> Presenter
        void onSend();
        void onInfo();
        void onMsgTypeChanged();
        void onMakeRandomSignalRequest();

        // Presenter <- Model
        void onDataReceived(String data);

        // Presenter <- Model <- Remote
        void onServerConnextionFail();
        void onServerConnextionSuccess();
    }

    interface Model extends BaseModel<Present>{
        // Presenter -> Model
        void sendSignal(byte[] s);
        void onSignalReceived(byte []bites);

        // Model <- Interactor
        void onServerConnextionFail();
        void onServerConnextionSuccess();

        interface Interactor{
            void sendSignal(byte[] signal);
        }
    }
}