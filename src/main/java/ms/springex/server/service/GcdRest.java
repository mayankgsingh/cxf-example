package ms.springex.server.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ms.springex.server.service.util.GcdUtil;
import ms.springex.server.vo.RestResponse;

/**
 * REST Interface class to publish "push" and "getNumbers" interface.
 * 
 * @author Mayank
 *
 */
@Path("/gcd/")
@Produces("application/json")
@Component(value="gcdrestservice")
@Scope(scopeName=BeanDefinition.SCOPE_PROTOTYPE)
public class GcdRest {

  @Autowired
  private GcdUtil util;
  
  /**
   * Pushes mentioned integers to Message Queue.
   * 
   * @param i1
   * @param i2
   * @return RestResponse<String>
   */
  @PUT
  @Path("/push/{i1}/{i2}")
  @Produces("application/json")
  public RestResponse<String> push(@PathParam("i1") int i1, @PathParam("i2") int i2) {
    String status = util.push(i1, i2);
    RestResponse<String> response = new RestResponse<String>(status, null);
    
    return response;
  }
  
  /**
   * Return list of numbers that has been posted till in order.
   * 
   * @return RestResponse<List<Integer>>
   */
  @GET
  @Path("/numbers/")
  @Produces("application/json")
  public RestResponse<List<Integer>> getNumbers() {
    List<Integer> list = util.list();
    RestResponse<List<Integer>> response = new RestResponse<List<Integer>>("SUCCESS", list);
    return response;
  }
}
