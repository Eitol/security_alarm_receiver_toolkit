package instant.alarmreceptortoolkitapp.simulator;

import instant.alarmreceptortoolkitapp.data.protocols.entities.Signal;
import instant.alarmreceptortoolkitapp.data.source.remote.SocketIOInteractor;

/**
 * Created by hector on 28/05/17.
 */

public class SimulatorModel implements SimulatorContract.Model {
    private final SimulatorContract.Model.Interactor mInteractor;
    private SimulatorContract.Present mPresenter;

    public SimulatorModel(SimulatorContract.Present presenter) {
        this.mPresenter = presenter;
        this.mInteractor = new SocketIOInteractor(this);
    }

    @Override
    public void sendSignal(Signal s) {
        this.mInteractor.sendSignal(s.toString());
    }

    @Override
    public void onSignalReceived(byte[] bites) {

    }

    @Override
    public void onServerConnextionFail() {
        mPresenter.onServerConnextionFail();
    }


//
//    private final Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case Constants.MESSAGE_STATE_CHANGE:
//                    switch (msg.arg1) {
//                        case BluetoothChatService.STATE_CONNECTED:
//                            // TODO
//                            break;
//                        case BluetoothChatService.STATE_CONNECTING:
//                            // TODO
//                            break;
//                        case BluetoothChatService.STATE_LISTEN:
//                        case BluetoothChatService.STATE_NONE:
//                            // TODO
//                            break;
//                    }
//                    break;
//                case Constants.MESSAGE_WRITE:
//                    // byte[] writeBuf = (byte[]) msg.obj;
//                    // String writeMessage = new String(writeBuf);
//                    // mConversationArrayAdapter.add("Me:  " + writeMessage);
//                    break;
//                case Constants.MESSAGE_READ:
//                    // byte[] readBuf = (byte[]) msg.obj;
//                    // String readMessage = new String(readBuf, 0, msg.arg1);
//                    // mConversationArrayAdapter.add(mConnectedDeviceName + ":  " + readMessage);
//                    break;
//                case Constants.MESSAGE_DEVICE_NAME:
//                    // save the connected device's name
//                    // mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
//                    //if (null != activity) {
//                    //Toast.makeText(activity, "Connected to "
//                    //+ mConnectedDeviceName, Toast.LENGTH_SHORT).show();
//                    //}
//                    break;
//                case Constants.MESSAGE_TOAST:
//                    //if (null != activity) {
//                    //Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
//                    //Toast.LENGTH_SHORT).show();
//                    //}
//                    break;
//            }
//        }
//    };
}
