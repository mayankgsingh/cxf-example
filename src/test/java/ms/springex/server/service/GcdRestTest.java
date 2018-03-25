package ms.springex.server.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import ms.springex.server.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class GcdRestTest {
  
  @Autowired
  private ApplicationContext context;
  private GcdService gcdService;

  @Before
  public void setup() {
    gcdService = (GcdService)context.getBean("gcdClient");
  }
  
  @Test
  public void testNumbers() throws Exception {
    List<Integer> list = gcdService.gcdList();
    Assert.notNull(list, "Result should not be empty.");
  }
}
