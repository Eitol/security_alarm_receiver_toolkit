package instant.alarmreceptortoolkitapp.data.source.remote;

import android.util.Log;

import org.json.JSONObject;

import java.net.URISyntaxException;

import instant.alarmreceptortoolkitapp.global.Constants;
import instant.alarmreceptortoolkitapp.simulator.SimulatorContract;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by hector on 12/06/17.
 */

public class SocketIOInteractor implements SimulatorContract.Model.Interactor {

    private final SimulatorContract.Model mModel;
    private boolean fail = false;
    private Socket socket = null;

    private static final String EVENT_SEND_SIGNAL = "send signal";

    public SocketIOInteractor(SimulatorContract.Model model) {
        mModel = model;
        this.fail = setupSIO();
        if (this.fail){
            model.onServerConnextionFail();
        }
    }

    public boolean isFail() {
        return fail;
    }

    private boolean setupSIO() {
        try {
            socket = IO.socket(Constants.RASPBERRY_IP);
        } catch (URISyntaxException e) {
            Log.d(Constants.LOG_NAME, "SocketIOInteractor: Failed connecting to server " + Constants.RASPBERRY_IP);
            return false;
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.emit("chat message", "adadad");
            }
        });

        socket.on(EVENT_SEND_SIGNAL, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
            }
        });

        socket.on(Socket.EVENT_DISCONNECT, args -> {
            Log.d(Constants.LOG_NAME, "setupSIO: Disconnect");
        });

        socket.on(Socket.EVENT_CONNECT_TIMEOUT, args -> mModel.onServerConnextionFail());
        socket.on(Socket.EVENT_CONNECT_ERROR, args -> mModel.onServerConnextionFail());
        socket.on(Socket.EVENT_CONNECT,args -> mModel.onServerConnextionSuccess());

        socket.connect();
        JSONObject obj = new JSONObject();
        return false;
    }

    @Override
    public void sendSignal(byte[] signal) {
        socket.emit(EVENT_SEND_SIGNAL, new String(signal));
    }
}
