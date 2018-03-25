package ms.springex.server.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public final class RestResponse<T> implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String status;
  T payload;
  
  public RestResponse(String status, T payload) {
    this.status = status;
    this.payload = payload;
  }

  public String getStatus() {
    return status;
  }

  public T getPayload() {
    return payload;
  }
  
}
