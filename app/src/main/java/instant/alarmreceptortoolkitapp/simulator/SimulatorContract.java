package instant.alarmreceptortoolkitapp.simulator;

import instant.alarmreceptortoolkitapp.base.BaseModel;
import instant.alarmreceptortoolkitapp.base.BasePresenter;
import instant.alarmreceptortoolkitapp.base.BaseView;
import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;

public interface SimulatorContract {
    interface View extends BaseView<Present> {
        void addToLog(String s);
        void clearLog();
        void showMessage(String msg, double duration);
        Signal getSignal();
        android.view.View[] getNonRequiredFieldForCurrentMsgType();
        void setReceptorValue(String value);
        void setLineValue(String value);
        void setAccountValue(String value);
        void setCodeValuePosition(int pos);
        int getCodeListSize();
    }

    interface Present extends BasePresenter {

        // View -> Presenter
        void onSend(Signal signal);
        void onInfo();
        void onMsgTypeChanged();
        void onMakeRandomSignalRequest();

        // Presenter <- Model
        void onDataReceived(String data);

        // Presenter <- Model <- Remote
        void onServerConnextionFail();
    }

    interface Model extends BaseModel<Present>{
        // Presenter -> Model
        void sendSignal(Signal s);
        void onSignalReceived(byte []bites);

        // Model <- Interactor
        void onServerConnextionFail();

        interface Interactor{
            void sendSignal(String signal);
        }
    }
}