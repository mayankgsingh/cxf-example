package ms.springex.client;

import javax.jms.JMSException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ms.springex.server.service.GcdService;

/**
 * Class for quick testing once app is deployed on server.
 * 
 * @author Mayank
 *
 */
@Configuration
public class ClientConfig {
  private static final Logger LOG = LoggerFactory.getLogger(ClientConfig.class);
  
  @Bean
  public JaxWsProxyFactoryBean gcdProxyFactoryBean() {
      JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
      proxyFactory.setServiceClass(GcdService.class);
      proxyFactory.setAddress("http://localhost:8080/cxf-example/services/gcdservice");
      return proxyFactory;
  }
  
  @Bean(name = "gcdClient")
  public Object generateProxy() {
    return gcdProxyFactoryBean().create();
  }
  
  public static void main(String[] args) throws NumberFormatException, JMSException {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ClientConfig.class);
    
    GcdService gcdService = (GcdService) ctx.getBean("gcdClient");
    LOG.info("########Checking gcdList(): {}", gcdService.gcdList());
    LOG.info("########Checking gcdSum(): {}", gcdService.gcdSum());
    LOG.info("########Checking gcd(): {}", gcdService.gcd());
    LOG.info("########Checking gcdList(): {}", gcdService.gcdList());
    LOG.info("########Checking gcdSum(): {}", gcdService.gcdSum());
    
    ctx.close();
  }
}
