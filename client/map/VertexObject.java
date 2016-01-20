package clientmodel.map;

public class VertexObject{
  int owner;
  EdgeLocation location;
  
  VertexObject(int x, int y, int id)
  {
    location = new EdgeLocation(x,y);
    owner = id;
  }
}