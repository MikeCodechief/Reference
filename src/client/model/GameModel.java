/**
 * David - canAcceptTrade - ResourceList param
 * - canOfferTrade - ResourceList param
 * - canDiscardCards(resourceList)
 * - canBuildCity - why checking settlements < MAX_SETTLEMENTS (check placed settlements)
 * - getCities/Settlements
 * - maritimeTrade (ports)
 * <p>
 * CanTradeBank - make sure you have resources
 * Mike - need to line up our methods, too many red things on my end
 * <p>
 * maritime trade?!
 */


package client.model;

import client.model.bank.Bank;
import client.model.bank.ResourceList;
import client.model.map.*;
import client.model.misc.*;
import client.model.player.Player;
import com.sun.xml.internal.ws.api.ResourceLoader;
import org.omg.CORBA.DynAnyPackage.Invalid;
import client.model.history.*;
import shared.exceptions.*;
import shared.definitions.*;
import shared.locations.*;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private Map map;
    private Bank bank;
    private ArrayList<Player> players;
    private TurnTracker turnTracker;
    private TradeOffer to;
    private int version = 0;
    private int winner = -1;
    private Dice dice;
    private Chat chat;
    private Log log;


    public GameModel() {
        map = new Map();
        bank = new Bank();
        turnTracker = new TurnTracker();
        to = new TradeOffer();
        dice = new Dice();
        chat = new Chat();
        players = new ArrayList<>();
        players.add(new Player("",0));
        players.add(new Player("",1));
        players.add(new Player("",2));
        players.add(new Player("",3));

    }

    public GameModel(String[] names) {
        map = new Map();
        bank = new Bank();
        turnTracker = new TurnTracker();
        to = new TradeOffer();
        dice = new Dice();
        players.add(new Player(names[0],0));
        players.add(new Player(names[1],1));
        players.add(new Player(names[2],2));
        players.add(new Player(names[3],3));
    }

 /*   public void reinitialize(GameModel game) {
        map = game.getMap();
        tt = game.getTurnTracker();
        to = game.getTradeOffer();
        version = game.getVersion();
        winner = game.getWinner();
        players = game.getPlayers();
        bank = game.getBank();
//        dice = new Dice();
    }
*/

    public int getPlayerIndex(int playerId){
        for(int i = 0; i < 4; i++){
            if(players.get(i).getPlayerID() == playerId){
                return players.get(i).getPlayerIndex();
            }
        }
        return 0;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public TurnTracker getTt() {
        return turnTracker;
    }

    public void setTt(TurnTracker tt) {
        this.turnTracker = tt;
    }

    public TradeOffer getTo() {
        return to;
    }

    public void setTo(TradeOffer to) {
        this.to = to;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    /**
     * updates version of the game model
     */
    public void updateVersion() {
        version++;
    }

    /**
     * Checks to see if the player can win the game
     *
     * @return boolean whether or not the player can win (have 10 victory points)
     */
    public boolean canWin() {
        //need to check if it is finish turn?
        int cp = turnTracker.getCurrentPlayer();
        return players.get(cp).canWin();
    }

    /**
     * Checks to see if building a road is a legal move for the player
     *
     * @return boolean whether or not the player can build a road
     */
    public boolean canBuildRoad(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canBuildRoad();
    }

    /**
     * Checks to see if placing a road is a legal move for the player
     *
     * @return boolean whether or not the player can place a road
     */
    public boolean canPlaceRoad(EdgeLocation el) {
        return map.canAddRoad(el);
    }

    /**
     * Checks to see if building a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can build a settlement
     */
    public boolean canBuildSettlement(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canBuildSettlement();
    }

    /**
     * Checks to see if placing a settlement is a legal move for the player
     *
     * @return boolean whether or not the player can place a settlement
     */
    public boolean canPlaceSettlement(VertexLocation vl) {
        return map.canAddSettlement(vl);
    }


    /**
     * Checks to see if building a city is a legal move for the player
     *
     * @return boolean whether or not the player can build a city
     */
    public boolean canBuildCity(int pid) {
        if (turnTracker.getCurrentPlayer() != pid && turnTracker.getStatus().equals("Playing"))
            return false;
        return players.get(pid).canBuildCity();
    }

    /**
     * Checks to see if placing a city is a legal move for the player
     *
     * @return boolean whether or not the player can place a city
     */
    public boolean canPlaceCity(VertexLocation vl) {
        return map.canAddCity(vl);
    }

    /**
     * Checks to see if playing a Developement Card is a legal move for the player
     *
     * @return boolean whether or not the player can play a Developement card
     */
    public boolean canPlayDevcard() {
        int cp = turnTracker.getCurrentPlayer();
        return players.get(cp).canPlayDevcard();
    }

    /**
     * Checks to see if Montoply is a legal move for the player
     *
     * @return boolean whether or not the player can monopoly
     */
    public boolean canMonopoly(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canMonopoly();
    }

    /**
     * Checks to see if building a road in a specific place is a legal move for the player
     *
     * @return boolean whether or not the player can road building
     */
    public boolean canRoadBuilding(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canPlayRoadBuilding();
    }

    /**
     * Checks to see if placing a Monument Card(?) is a legal move for the player
     *
     * @return boolean whether or not the player can place a monument
     */
    public boolean canUseMonument(int pid) {
        if (pid != turnTracker.getCurrentPlayer())
            return false;
        return players.get(pid).canPlaceMonument();
    }

    /**
     * Checks to see if placing a Year Of Plenty card is a legal move for the player
     *
     * @return boolean whether or not the player can play the Year of Plenty card
     */
    public boolean canYearOfPlenty(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canYearOfPlenty();
    }

    /**
     * Checks to see if placing a Soldier card is a legal move for the player
     *
     * @return boolean whether or not the player can place the Soldier card
     */
    public boolean canPlaySoldier(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canPlaceSoldier();
    }

    public boolean canRob(int pid, int vid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(vid).canRob();
    }

    /**
     * Checks to see if moving the robber is a legal move for the player
     *
     * @return boolean whether or not the player can move the robber
     */
    public boolean canMoveRobber(HexLocation hl) {
        return map.canRelocateRobber(hl);
    }


    public boolean canFinishTurn(int pid) {
        return (turnTracker.getStatus().equals("SecondRound") && turnTracker.getCurrentPlayer() == pid);
    }

    public boolean canDiscardCards(int pid, ResourceList rl) {
        return players.get(pid).canDiscardCards(rl);
    }

    /**
     * Set up the TradeOffer
     */
    public boolean canTradePlayer(int pid, int rid, ResourceList rl) throws IllegalMoveException, InsufficientResourcesException {
        if (turnTracker.getStatus().equals("FirstRound") && pid != turnTracker.getCurrentPlayer())
            throw new IllegalMoveException("not the trading phase, or not the player's turn");

        if (pid == rid)
            throw new IllegalMoveException("No trading yourself!");

        to = new TradeOffer(pid, rid, rl);
        return players.get(pid).canOfferTrade(rl);


    }

    public boolean canTradeBank(int pid, ResourceList rl) {
        //pull out the ports and figure that out before commiting the trade
        //figure out the ports . . .

//        ArrayList<VertexObject> vo = players[pid].getBuildings();
//        ArrayList<Port> p = bank.isPorts(vo);
//        return players[pid].canMaritimeTrade(p, rl);
        return true;
    }

    /**
     * Checks to see if trading resources with the bank is a legal move for the player
     * based on the resources the player currently has.
     *
     * @param pid The id of the player making the trade
     * @return boolean whether or not the player can trade with the bank
     */
    public boolean canMaritimeTrade(int pid) throws IllegalMoveException {
        if (!turnTracker.getStatus().equals("Robbing") && pid != turnTracker.getCurrentPlayer())
            throw new IllegalMoveException("not the trading phase, or not the player's turn");

        ArrayList<Port> ports = (ArrayList) map.getPlayerPorts(pid);
        return players.get(pid).canMaritimeTrade(ports);
    }

    /**
     * Checks to see accepting a trade request is a legal move for the player
     *
     * @return boolean whether or not the player can accept a trade offer from another player
     */
    public boolean canAcceptTrade(int pid) {
        return players.get(pid).canAcceptTrade(to.getSentList());
    }

    /**
     * Checks to see if the player can roll the dice
     *
     * @return boolean whether or not the player can roll the dice
     */
    public boolean canRoll(int pid) {
        if (turnTracker.getStatus().equals("Rolling") && turnTracker.getCurrentPlayer() == pid)
            return true;
        return false;
    }

    /**
     * rolls the dice for a number 1-12
     *
     * @return boolean whether or not the player rolled the dice
     */
    public int roll(int pid) {
        return dice.rollDice();
    }

    /**
     * Checks to see if buying a Developement Card is a legal move for the player
     *
     * @return boolean whether or not the player can buy a Developement card
     */
    public boolean canBuyDevcard(int pid) {
        if (turnTracker.getCurrentPlayer() != pid)
            return false;
        return players.get(pid).canBuyDevcard();
    }

    /**
     * Robs a player of one resource card
     *
     * @return boolean whether or not the player chose to rob
     */
    public void rob(int pid, ResourceType rt) throws IllegalMoveException, InsufficientResourcesException {
        int cp = turnTracker.getCurrentPlayer();
        if (cp == pid)
            throw new IllegalMoveException("Can't rob yourself");
        players.get(pid).depleteResource(rt);
        players.get(cp).addResource(rt, 1);
    }

    public boolean canSendChat(String msg, int pid) {
        chat.addMessage(players.get(pid).getName(), msg);
        return true;
    }

}
