package client.model.misc;

import client.model.bank.ResourceList;

public class TradeOffer {
  
  //The index of the person offering the trade
  int sender;
  //The index of the person the trade was offered to.
  int reciever;
  //Positive numbers are resources being offered. Negative are resources being asked for.
  ResourceList offer;
  //Resource List of whats being sent (negative)
  ResourceList sentList = new ResourceList(0);
  //Resource List of whats being recieved (positive)
  ResourceList recievedList = new ResourceList(0);
  
  public TradeOffer(ResourceList offr)
  {
    offer = offr;
  }
  /*
  * Separates the given offer into two resource lists, one that is filled with only the positive number
  * of resources being recieved, and the other a list filled with only the negative number of resources
  * being sent.
  */
  public void separateOffer()
  {
    int brick = offer.getBrick();
    int ore = offer.getOre(); 
    int sheep = offer.getSheep();
    int wheat = offer.getWheat();
    int wood = offer.getWood();
    if(brick >= 0){recievedList.setBrick(brick)} else sentList.setBrick(brick);
    if(ore >= 0){recievedList.setOre(ore)} else sentList.setOre(ore);
    if(sheep >= 0){recievedList.setSheep(sheep)} else sentList.setSheep(sheep);
    if(wheat >= 0){recievedList.setWheat(wheat)} else sentList.setWheat(wheat);
    if(wood >= 0){recievedList.setWood(wood)} else sentList.setWood(wood);
  }
  
  public int getSender()
  {
    return sender;
  }
  public void setSender(int send)
  {
    sender = send;
  }  
  public int getReciever()
  {
    return reciever;
  }
  public void setReciever(int recieve)
  {
    reciever = recieve;
  }  
  public ResourceList getOffer()
  {
    return offer;
  }
  public void setOffer(ResourceList off)
  {
    offer = off;
  }
}
