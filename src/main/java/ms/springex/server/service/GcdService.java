package ms.springex.server.service;

import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

/**
 * SOAP Interface for Gcd Service
 * 
 * @author Mayank
 *
 */
@WebService
public interface GcdService {

  /**
   * Returns the greatest common divisor* of the two integers at the head of the
   * queue. These two elements will subsequently be discarded from the queue and
   * the head replaced by the next two in line.
   * 
   * @return Integer
   */
  public int gcd() throws NumberFormatException, JMSException;

  /**
   * Returns a list of all the computed greatest common divisors from a database
   * 
   * @return List<Integer>
   */
  public List<Integer> gcdList();

  /**
   * Returns the sum of all computed greatest common divisors from a database
   * 
   * @return Integer
   */
  public int gcdSum();
}
