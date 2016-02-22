package client.MVC.communication;

import client.MVC.base.*;
import client.facade.Facade;
import client.model.history.MessageLine;
import client.model.player.Player;
import shared.definitions.CatanColor;

import java.util.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController {

    public GameHistoryController(IGameHistoryView view) {

        super(view);
        Facade.getInstance().addObserver(this);
    }

    @Override
    public IGameHistoryView getView() {

        return (IGameHistoryView) super.getView();
    }

    private void initFromModel()
    {
        ArrayList<MessageLine> ml = Facade.getInstance().getGM().getLog().getLogList().getMessages();
        ArrayList<Player> players = Facade.getInstance().getGM().getPlayers();
        Map <String, CatanColor> conversion = new TreeMap<String, CatanColor>();

        for(int i = 0; i < players.size(); i++)
        {
            String color = players.get(i).getColor();
            if(color == null || color.isEmpty()) //happens at start up
                return;
            conversion.put(players.get(i).getName(), CatanColor.convert(color));
        }
        List<LogEntry> logs = new ArrayList<LogEntry>();
        for(int i = 0;  i< ml.size(); i++)
        {
            CatanColor c = conversion.get(ml.get(i).getSource());
            logs.add(new LogEntry(c, ml.get(i).getMessage()));
        }

        getView().setEntries(logs);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        initFromModel();

    }
}

