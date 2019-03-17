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

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Objects;

/**
 * domestic payments additional info (symbols for fomestic payments)
 */
@ApiModel(description = "domestic payments additional info (symbols for fomestic payments)")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-17T19:50:24.264+01:00")
public class TransactionAdditionalInfoDomestic {
  @JsonProperty("constantSymbol")
  private String constantSymbol = null;

  @JsonProperty("variableSymbol")
  private String variableSymbol = null;

  @JsonProperty("specificSymbol")
  private String specificSymbol = null;

  public TransactionAdditionalInfoDomestic constantSymbol(String constantSymbol) {
    this.constantSymbol = constantSymbol;
    return this;
  }

   /**
   * Get constantSymbol
   * @return constantSymbol
  **/
  @ApiModelProperty(value = "")
  public String getConstantSymbol() {
    return constantSymbol;
  }

  public void setConstantSymbol(String constantSymbol) {
    this.constantSymbol = constantSymbol;
  }

  public TransactionAdditionalInfoDomestic variableSymbol(String variableSymbol) {
    this.variableSymbol = variableSymbol;
    return this;
  }

   /**
   * Get variableSymbol
   * @return variableSymbol
  **/
  @ApiModelProperty(value = "")
  public String getVariableSymbol() {
    return variableSymbol;
  }

  public void setVariableSymbol(String variableSymbol) {
    this.variableSymbol = variableSymbol;
  }

  public TransactionAdditionalInfoDomestic specificSymbol(String specificSymbol) {
    this.specificSymbol = specificSymbol;
    return this;
  }

   /**
   * Get specificSymbol
   * @return specificSymbol
  **/
  @ApiModelProperty(value = "")
  public String getSpecificSymbol() {
    return specificSymbol;
  }

  public void setSpecificSymbol(String specificSymbol) {
    this.specificSymbol = specificSymbol;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransactionAdditionalInfoDomestic transactionAdditionalInfoDomestic = (TransactionAdditionalInfoDomestic) o;
    return Objects.equals(this.constantSymbol, transactionAdditionalInfoDomestic.constantSymbol) &&
        Objects.equals(this.variableSymbol, transactionAdditionalInfoDomestic.variableSymbol) &&
        Objects.equals(this.specificSymbol, transactionAdditionalInfoDomestic.specificSymbol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(constantSymbol, variableSymbol, specificSymbol);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransactionAdditionalInfoDomestic {\n");
    
    sb.append("    constantSymbol: ").append(toIndentedString(constantSymbol)).append("\n");
    sb.append("    variableSymbol: ").append(toIndentedString(variableSymbol)).append("\n");
    sb.append("    specificSymbol: ").append(toIndentedString(specificSymbol)).append("\n");
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

