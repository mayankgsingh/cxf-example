package ms.springex.server.service;

import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import ms.springex.server.service.util.GcdUtil;

@WebService(endpointInterface = "ms.springex.server.service.GcdService")
@Service
@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST)
public class GcdServiceImpl implements GcdService {

  @Autowired
  private GcdUtil gcdUtil;
  
  public int gcd() throws NumberFormatException, JMSException {
    return gcdUtil.gcd();
  }

  public List<Integer> gcdList() {
    return gcdUtil.gcdList();
  }

  public int gcdSum() {
    return gcdUtil.gcdSum();
  }

}