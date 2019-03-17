/*
 * Swagger Banking
 * Simple Banking API used for MOIS on UHK. Api is derived from AIR bank API: https://www.airbank.cz/novinky-z-airbank/2017/open-api/open-api-banking.html#top
 *
 * OpenAPI spec version: 1.0.0
 * Contact: michal.gregor@uhk.cz
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package cz.kozenky.moispayments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;
import org.joda.time.DateTime;

/**
 * party account number
 */
@ApiModel(description = "party account number")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-17T19:50:24.264+01:00")
public class PaymentRecuringPayment {
  @JsonProperty("firstPayment")
  private DateTime firstPayment = null;

  @JsonProperty("lastPayment")
  private DateTime lastPayment = null;

  /**
   * Gets or Sets interval
   */
  public enum IntervalEnum {
    WEEK("WEEK"),
    
    MONTH("MONTH"),
    
    QUARTER("QUARTER"),
    
    HALF_YEAR("HALF-YEAR"),
    
    YEAR("YEAR");

    private String value;

    IntervalEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IntervalEnum fromValue(String text) {
      for (IntervalEnum b : IntervalEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("interval")
  private IntervalEnum interval = null;

  public PaymentRecuringPayment firstPayment(DateTime firstPayment) {
    this.firstPayment = firstPayment;
    return this;
  }

   /**
   * Get firstPayment
   * @return firstPayment
  **/
  @ApiModelProperty(value = "")
  public DateTime getFirstPayment() {
    return firstPayment;
  }

  public void setFirstPayment(DateTime firstPayment) {
    this.firstPayment = firstPayment;
  }

  public PaymentRecuringPayment lastPayment(DateTime lastPayment) {
    this.lastPayment = lastPayment;
    return this;
  }

   /**
   * Get lastPayment
   * @return lastPayment
  **/
  @ApiModelProperty(value = "")
  public DateTime getLastPayment() {
    return lastPayment;
  }

  public void setLastPayment(DateTime lastPayment) {
    this.lastPayment = lastPayment;
  }

  public PaymentRecuringPayment interval(IntervalEnum interval) {
    this.interval = interval;
    return this;
  }

   /**
   * Get interval
   * @return interval
  **/
  @ApiModelProperty(value = "")
  public IntervalEnum getInterval() {
    return interval;
  }

  public void setInterval(IntervalEnum interval) {
    this.interval = interval;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentRecuringPayment paymentRecuringPayment = (PaymentRecuringPayment) o;
    return Objects.equals(this.firstPayment, paymentRecuringPayment.firstPayment) &&
        Objects.equals(this.lastPayment, paymentRecuringPayment.lastPayment) &&
        Objects.equals(this.interval, paymentRecuringPayment.interval);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstPayment, lastPayment, interval);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentRecuringPayment {\n");
    
    sb.append("    firstPayment: ").append(toIndentedString(firstPayment)).append("\n");
    sb.append("    lastPayment: ").append(toIndentedString(lastPayment)).append("\n");
    sb.append("    interval: ").append(toIndentedString(interval)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
}

