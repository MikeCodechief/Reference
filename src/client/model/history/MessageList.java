package client.model.history;

import java.util.ArrayList;

public class MessageList {

  ArrayList<MessageLine> messages;
  
  /**
  * Adds a message to the message list
  * 
  * @param line - a line of text
  */
  public void addMessage(MessageLine line)
  {
    messages.add(line);
  }
   /**
  * Gets the line from the chat list that matches the paramater line
  * If there is an exact match it will return it, otherwise it will return null
  * 
  * @param line - a line of text
  */
  public MessageLine getMessageLine(MessageLine line)
  {
  	for(int i = 0; i < chatMessages.getLength(); i++)
  	{
  	  if(chatMessages.get(i).getMessage.equals(line.getMessage) && chatMessages.get(i).getMessage.equals(line.getSource))
  	  {
  	    return chatMessages.get(i);
  	  }
  	}
  	return null;
  }

}
