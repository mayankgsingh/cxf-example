package ms.springex.server.service.util;

import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import ms.springex.server.dao.GcdDao;

/**
 * GCD Utility class
 * 
 * @author Mayank
 *
 */
@Service
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class GcdUtil {
  private Logger LOG = LoggerFactory.getLogger(GcdUtil.class);

  @Autowired
  private GcdDao dao;
  
  @Resource(name="destination")
  private Destination queue;
  
  @Autowired
  private JmsTemplate template;
  
  public int gcd() throws NumberFormatException, JMSException {
    int result = 0;
    TextMessage textMessage = (TextMessage)template.receive(queue);
    int x = Integer.parseInt(textMessage.getText());
    
    textMessage = (TextMessage)template.receive(queue);
    int y = Integer.parseInt(textMessage.getText());
    
    result = calculateGcd(x, y);
    dao.saveGcd(x, y, result);
    
    return result;
  }
  
  public List<Integer> gcdList() {
    List<Integer> list = dao.retreiveGcds();
    return list;
  }
  
  public int gcdSum() {
    int result = dao.retreiveGcdSum();
    return result;
  }
  
  public String push(final int i1, final int i2) {
    template.send(queue, new QueueMessageCreator(i1));
    template.send(queue, new QueueMessageCreator(i2));
    dao.saveNumber(i1, i2);
    return "SUCCESS";
  }
  
  public List<Integer> list() {
    return dao.retreiveNumbers();
  }
  
  /**
   * Gcd calculation logic.
   * 
   * @param x
   * @param y
   * @return
   */
  private int calculateGcd(int x, int y) {
    int gcd = 1;
    
    for(int i = 2; i<=x && i<=y; i++) {
      if(x%i == 0 && y%i==0) {
        gcd = i;
      }
    }

    LOG.info("GCD of {} & {} is {}.", x, y, gcd);
    return gcd;
  }
}
