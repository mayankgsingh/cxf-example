package ms.springex.server.service.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class QueueMessageCreator implements MessageCreator {

  private Integer i;
  
  public QueueMessageCreator(int i) {
    super();
    this.i = i;
  }
  
  public Message createMessage(Session session) throws JMSException {
    return session.createTextMessage(this.i.toString());
  }
}
