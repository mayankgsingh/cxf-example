package ms.springex.server.dao;

import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope(scopeName=BeanDefinition.SCOPE_PROTOTYPE)
public class GcdDao {

  private static final Logger LOG = LoggerFactory.getLogger(GcdDao.class);
  
  private static final String NUMBER_INSERT = "insert into number_inp(num) values(?)";
  private static final String NUMBER_SELECT = "SELECT num FROM number_inp ORDER BY id";
  private static final String GCD_INSERT = "insert into number_gcd(x, y, num) values(?, ?, ?)";
  private static final String GCD_LIST = "SELECT num FROM number_gcd ORDER BY ID";
  private static final String GCD_SUM = "SELECT sum(num) FROM number_gcd";
  
  @Autowired
  private DataSource dataSource;
  
  private JdbcTemplate jdbcTemplate;
  
  /**
   * Save posted numbers.
   * 
   * @param x
   * @param y
   */
  public void saveNumber(int x, int y) {
    LOG.info("Inserting {} & {}", x, y);
    List<Object[]> batchArgs = new LinkedList<Object[]>();
    batchArgs.add(new Object[] {x});
    batchArgs.add(new Object[] {y});
    
    jdbcTemplate.batchUpdate(NUMBER_INSERT, batchArgs, new int[] {Types.INTEGER});
    LOG.info("Inserted {} & {} successfully.", x, y);
  }
  
  /**
   * Save GCD information.
   * 
   * @param x
   * @param y
   * @param gcd
   */
  public void saveGcd(int x, int y, int gcd) {
    LOG.info("Inserting {}, {} & {}", x, y, gcd);
    jdbcTemplate.update(GCD_INSERT, x, y, gcd);
    LOG.info("Inserted {}, {} & {} successfully.", new Object[] {x, y, gcd}, new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER});
  }
  
  /**
   * Retrieves List of posted numbers.
   * 
   * @return List<Integer>
   */
  public List<Integer> retreiveNumbers() {
    List<Integer> result = null;
    
    try {
      result = jdbcTemplate.queryForList(NUMBER_SELECT, Integer.class);
      LOG.info("Result size: {}", result.size());
    } catch(EmptyResultDataAccessException e) {
      LOG.warn("No records in this Table.");
      result = new LinkedList<Integer>();
    }
    
    return result;
  }
  
  /**
   * Retrieves sum of calculated GCDs.
   * 
   * @return Integer
   */
  public Integer retreiveGcdSum() {
    Integer result = null;
    try {
    result = jdbcTemplate.queryForObject(GCD_SUM, Integer.class);
    LOG.info("Retreive GCD Sum: {}", result);
    } catch(EmptyResultDataAccessException e) {
      LOG.warn("No records in this Table.");
    }
    return (result==null)?0:result;
  }
  
  /**
   * Retrieves sum of calculated GCDs.
   * 
   * @return Integer
   */
  public List<Integer> retreiveGcds() {
    List<Integer> result = null;
    try {
    result = jdbcTemplate.queryForList(GCD_LIST, Integer.class);
    LOG.info("Retreive GCD Sum: {}", result);
    } catch(EmptyResultDataAccessException e) {
      LOG.warn("No records in this Table.");
      result = new LinkedList<Integer>();
    }
    return result;
  }
  
  @PostConstruct
  public void init() {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }
}
