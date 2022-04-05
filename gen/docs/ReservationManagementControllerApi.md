# ReservationManagementControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createReservationUsingPOST1**](ReservationManagementControllerApi.md#createReservationUsingPOST1) | **POST** /management/api/v1/reservations | Save a reservation
[**deleteReservationUsingDELETE1**](ReservationManagementControllerApi.md#deleteReservationUsingDELETE1) | **DELETE** /management/api/v1/reservations/{id} | Delete a reservation
[**getAllReservationsUsingGET1**](ReservationManagementControllerApi.md#getAllReservationsUsingGET1) | **GET** /management/api/v1/reservations | Get all reservations
[**getReservationByIdUsingGET1**](ReservationManagementControllerApi.md#getReservationByIdUsingGET1) | **GET** /management/api/v1/reservations/{id} | Get a reservation by id
[**updateReservationUsingPUT1**](ReservationManagementControllerApi.md#updateReservationUsingPUT1) | **PUT** /management/api/v1/reservations/{id} | Update a reservation


<a name="createReservationUsingPOST1"></a>
# **createReservationUsingPOST1**
> ReservationDTORes createReservationUsingPOST1(reservationDTOReq)

Save a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationManagementControllerApi apiInstance = new ReservationManagementControllerApi(defaultClient);
    ReservationDTOReq reservationDTOReq = new ReservationDTOReq(); // ReservationDTOReq | 
    try {
      ReservationDTORes result = apiInstance.createReservationUsingPOST1(reservationDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationManagementControllerApi#createReservationUsingPOST1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reservationDTOReq** | [**ReservationDTOReq**](ReservationDTOReq.md)|  | [optional]

### Return type

[**ReservationDTORes**](ReservationDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Successfully save a reservation |  -  |

<a name="deleteReservationUsingDELETE1"></a>
# **deleteReservationUsingDELETE1**
> deleteReservationUsingDELETE1(id)

Delete a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationManagementControllerApi apiInstance = new ReservationManagementControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to delete
    try {
      apiInstance.deleteReservationUsingDELETE1(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationManagementControllerApi#deleteReservationUsingDELETE1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| Reservation ID to delete |

### Return type

null (empty response body)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**204** | Successfully deleted a reservation |  -  |

<a name="getAllReservationsUsingGET1"></a>
# **getAllReservationsUsingGET1**
> List&lt;ReservationDTORes&gt; getAllReservationsUsingGET1()

Get all reservations

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationManagementControllerApi apiInstance = new ReservationManagementControllerApi(defaultClient);
    try {
      List<ReservationDTORes> result = apiInstance.getAllReservationsUsingGET1();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationManagementControllerApi#getAllReservationsUsingGET1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ReservationDTORes&gt;**](ReservationDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved all reservations of User |  -  |

<a name="getReservationByIdUsingGET1"></a>
# **getReservationByIdUsingGET1**
> ReservationDTORes getReservationByIdUsingGET1(id)

Get a reservation by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationManagementControllerApi apiInstance = new ReservationManagementControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to consult
    try {
      ReservationDTORes result = apiInstance.getReservationByIdUsingGET1(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationManagementControllerApi#getReservationByIdUsingGET1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| Reservation ID to consult |

### Return type

[**ReservationDTORes**](ReservationDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved a reservation by id |  -  |

<a name="updateReservationUsingPUT1"></a>
# **updateReservationUsingPUT1**
> ReservationDTORes updateReservationUsingPUT1(id, reservationDTOReq)

Update a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationManagementControllerApi apiInstance = new ReservationManagementControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to update
    ReservationDTOReq reservationDTOReq = new ReservationDTOReq(); // ReservationDTOReq | 
    try {
      ReservationDTORes result = apiInstance.updateReservationUsingPUT1(id, reservationDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationManagementControllerApi#updateReservationUsingPUT1");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| Reservation ID to update |
 **reservationDTOReq** | [**ReservationDTOReq**](ReservationDTOReq.md)|  | [optional]

### Return type

[**ReservationDTORes**](ReservationDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully updated a reservation |  -  |

