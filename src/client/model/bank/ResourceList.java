package client.model.bank;

public class ResourceList 
{
  
  int numOfBrick;
  int numOfOre;
  int numOfSheep;
  int numOfWheat;
  int numOfWood;
  
  public ResourceList()
  {
    numOfBrick = 19;
    numOfOre = 19;
    numOfSheep = 19;
    numOfWheat = 19;
    numOfWood = 19;
  }
    public ResourceList(-1)
  {
    numOfBrick = 0;
    numOfOre = 0;
    numOfSheep = 0;
    numOfWheat = 0;
    numOfWood = 0;
  }
    public ResourceList(int br, int or, int sh, int wh, int wo)
  {
    numOfBrick = br;
    numOfOre = or;
    numOfSheep = sh;
    numOfWheat = wh;
    numOfWood = wo;
  }
  //overload the constructor so that RL can be used for trades, bank, map, players etc.

  public int getBrick()
  {
    return numOfBrick;
  }
  public int getOre()
  {
    return numOfOre;
  }
  public int getSheep()
  {
    return numOfSheep;
  }
  public int getWheat()
  {
    return numOfWheat;
  }
  public int getWood()
  {
    return numOfWood;
  }
  public void setBrick(int brick)
  {
    numOfBrick = brick;
  }
  public void setOre(int ore)
  {
    numOfOre = ore;
  }
  public void setSheep(int sheep)
  {
    numOfSheep = sheep;
  }
  public void setWheat(int wheat)
  {
    numOfWheat = wheat;
  }
  public void setWood(int wood)
  {
    numOfWood = wood;
  }
  public ResourceList merge(ResourceList first, ResourceList second)
  {
    int newbrick = first.getBrick() + second.getBrick();
    int newore = first.getOre() + second.getOre();
    int newsheep = first.getSheep() + second.getSheep();
    int newwheat = first.getWheat() + second.getWheat();
    int newwood = first.getWood() + second.getWood();
    
    ResourceList result = new ResourceList(newbrick, newore, newsheep, newwheat, newwood);
    return result;
  }
}
