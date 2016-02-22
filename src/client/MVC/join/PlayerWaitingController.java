package client.MVC.join;

import client.MVC.base.*;
import client.facade.Facade;

import java.util.Observable;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

    public PlayerWaitingController(IPlayerWaitingView view) {

        super(view);
    }

    @Override
    public IPlayerWaitingView getView() {

        return (IPlayerWaitingView) super.getView();
    }

    @Override
    public void start() {

        getView().showModal();
    }

    @Override
    public void addAI() {

        // TEMPORARY
        getView().closeModal();
        Facade.getInstance().gamesCreate("New Game");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}

