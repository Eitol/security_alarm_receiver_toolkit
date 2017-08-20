package instant.alarmreceptortoolkitapp.simulator;

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
    public void sendSignal(byte[] signal) {
        this.mInteractor.sendSignal(signal);
    }

    @Override
    public void onSignalReceived(byte[] bites) {

    }

    @Override
    public void onServerConnextionFail() {
        mPresenter.onServerConnextionFail();
    }

    @Override
    public void onServerConnextionSuccess() {
        mPresenter.onServerConnextionSuccess();
    }
}
