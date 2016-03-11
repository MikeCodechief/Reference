package server.commandfactories;

import server.commandobjects.ICommand;
import server.commandobjects.moves.AcceptTrade;
import server.servermain.JsonConstructionInfo;

/**
 * Created by Joshua on 3/10/2016.
 */
public class UserFactory {
    public UserFactory(){

    }
    /**
     * This is the method that you call when you want a Command object that is in the User category
     * @param info - This is the information that is passed to the server so that the factory can create the specified object
     * @return - The command Object that we are asking for.
     */
    public ICommand getCommand(JsonConstructionInfo info){
        ICommand command = new AcceptTrade(1, false);
        return command;
    }
}