package client.MVC.map;

import client.MVC.base.*;
import client.MVC.data.*;
import client.MVC.data.RobPlayerInfo;
import client.facade.Facade;
import shared.definitions.CatanColor;
import shared.definitions.PieceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

public class StateSetup extends StateAbstract
{
    private IMapView view;
    private IRobView robView;
    CatanColor color;
    Facade facade;
    boolean setRoad = false, setSettlement = false;

    public StateSetup(IMapView v, IRobView rv)
    {
        view = v;
        robView = rv;
        facade = Facade.getInstance();
        color = facade.getCatanColor();
    }

    public boolean canPlaceRoad(EdgeLocation edgeLoc)
    {
        if(setRoad)
            return false;
        return facade.canPlaceRoad(edgeLoc);
    }

    public boolean canPlaceSettlement(VertexLocation vertLoc)
    {
        if(setSettlement)
            return false;
      return facade.canPlaceSettlement(vertLoc);
    }

    public void placeRoad(EdgeLocation edgeLoc)
    {
        view.placeRoad(edgeLoc, color);
        setRoad = true;
    }

    public void placeSettlement(VertexLocation vertLoc)
    {
        view.placeSettlement(vertLoc, color);
        setSettlement = true;
    }

    public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected)
    {
        view.startDrop(pieceType, color, true);
    }

    public boolean finishedSetup()
    {
        return (setRoad && setSettlement);
    }
//    public void cancelMove()
//    {
//        view.cancelDrop();
//    }

}
