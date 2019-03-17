package cz.kozenky.moispayments.api;

import cz.kozenky.moispayments.invoker.ApiClient;
import cz.kozenky.moispayments.model.Payment;
import java.math.BigDecimal;
import java.util.List;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-17T19:50:24.264+01:00")
@Component("com.baeldung.petstore.client.api.PaymentApi")
public class PaymentApi {
    private ApiClient apiClient;

    public PaymentApi() {
        this(new ApiClient());
    }

    @Autowired
    public PaymentApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Add a new payment
     * 
     * <p><b>200</b> - successful operation
     * <p><b>405</b> - Invalid input
     * @param body Payment object that needs to be added
     * @return Payment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Payment addPayment(Payment body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling addPayment");
        }
        
        String path = UriComponentsBuilder.fromPath("/payment").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Payment> returnType = new ParameterizedTypeReference<Payment>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Finds payment by date
     * 
     * <p><b>200</b> - successful operation
     * <p><b>400</b> - Invalid value
     * @param dateFrom Datetime from which to load payments
     * @param dateTo Datetime to which to load payments
     * @param accountId accountId for transactions
     * @return List&lt;Payment&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<Payment> findPaymentByDate(DateTime dateFrom, DateTime dateTo, BigDecimal accountId) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'dateFrom' is set
        if (dateFrom == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateFrom' when calling findPaymentByDate");
        }
        
        // verify the required parameter 'dateTo' is set
        if (dateTo == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'dateTo' when calling findPaymentByDate");
        }
        
        // verify the required parameter 'accountId' is set
        if (accountId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'accountId' when calling findPaymentByDate");
        }
        
        String path = UriComponentsBuilder.fromPath("/payment/findByDate").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();
        
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateFrom", dateFrom));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "dateTo", dateTo));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "accountId", accountId));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<Payment>> returnType = new ParameterizedTypeReference<List<Payment>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Update payment
     * 
     * <p><b>200</b> - successful operation
     * <p><b>405</b> - Invalid input
     * @param body Payment object that needs to be updated
     * @return Payment
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public Payment updatePayment(Payment body) throws RestClientException {
        Object postBody = body;
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'body' when calling updatePayment");
        }
        
        String path = UriComponentsBuilder.fromPath("/payment").build().toUriString();
        
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Payment> returnType = new ParameterizedTypeReference<Payment>() {};
        return apiClient.invokeAPI(path, HttpMethod.PUT, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
