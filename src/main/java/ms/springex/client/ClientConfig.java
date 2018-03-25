package ms.springex.client;

import javax.jms.JMSException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ms.springex.server.service.GcdService;

@Configuration
public class ClientConfig {

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
    /*HelloWorld helloWorld = (HelloWorld) ctx.getBean("client");
    String message = helloWorld.sayHi("Mayank");
    System.out.println("######### SOAP RESPONSE: " + message);*/
    
    GcdService gcdService = (GcdService) ctx.getBean("gcdClient");
    System.out.println("########Checking gcdList():" + gcdService.gcdList());
    System.out.println("########Checking gcdSum():" + gcdService.gcdSum());
    System.out.println("########Checking gcd():" + gcdService.gcd());
    System.out.println("########Checking gcdList():" + gcdService.gcdList());
    System.out.println("########Checking gcdSum():" + gcdService.gcdSum());
    
    ctx.close();
  }
}
