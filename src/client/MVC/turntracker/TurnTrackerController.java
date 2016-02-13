package client.MVC.turntracker;

import client.MVC.base.*;
import shared.definitions.CatanColor;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController {

    public TurnTrackerController(ITurnTrackerView view) {

        super(view);

        initFromModel();
    }

    @Override
    public ITurnTrackerView getView() {

        return (ITurnTrackerView) super.getView();
    }

    @Override
    public void endTurn() {

    }

    private void initFromModel() {
        //<temp>
        getView().setLocalPlayerColor(CatanColor.RED);
        //</temp>
    }

}
