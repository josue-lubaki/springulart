# ReservationControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**acceptReservationUsingPATCH**](ReservationControllerApi.md#acceptReservationUsingPATCH) | **PATCH** /api/v1/reservations/accept/{id} | Accept a reservation
[**createReservationUsingPOST**](ReservationControllerApi.md#createReservationUsingPOST) | **POST** /api/v1/reservations | Save a reservation
[**deleteReservationUsingDELETE**](ReservationControllerApi.md#deleteReservationUsingDELETE) | **DELETE** /api/v1/reservations/{id} | Delete a reservation
[**getAllReservationsUsingGET**](ReservationControllerApi.md#getAllReservationsUsingGET) | **GET** /api/v1/reservations | Get all reservations
[**getReservationByIdUsingGET**](ReservationControllerApi.md#getReservationByIdUsingGET) | **GET** /api/v1/reservations/{id} | Get reservation by id
[**updateReservationUsingPUT**](ReservationControllerApi.md#updateReservationUsingPUT) | **PUT** /api/v1/reservations/{id} | Update a reservation


<a name="acceptReservationUsingPATCH"></a>
# **acceptReservationUsingPATCH**
> ReservationDTORes acceptReservationUsingPATCH(id, reservationDTOReq)

Accept a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to accept
    ReservationDTOReq reservationDTOReq = new ReservationDTOReq(); // ReservationDTOReq | 
    try {
      ReservationDTORes result = apiInstance.acceptReservationUsingPATCH(id, reservationDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#acceptReservationUsingPATCH");
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
 **id** | **Long**| Reservation ID to accept |
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
**200** | Successfully accepted a reservation |  -  |

<a name="createReservationUsingPOST"></a>
# **createReservationUsingPOST**
> ReservationDTORes createReservationUsingPOST(reservationDTOReq)

Save a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    ReservationDTOReq reservationDTOReq = new ReservationDTOReq(); // ReservationDTOReq | 
    try {
      ReservationDTORes result = apiInstance.createReservationUsingPOST(reservationDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#createReservationUsingPOST");
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

<a name="deleteReservationUsingDELETE"></a>
# **deleteReservationUsingDELETE**
> deleteReservationUsingDELETE(id)

Delete a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to delete
    try {
      apiInstance.deleteReservationUsingDELETE(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#deleteReservationUsingDELETE");
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

<a name="getAllReservationsUsingGET"></a>
# **getAllReservationsUsingGET**
> List&lt;ReservationDTORes&gt; getAllReservationsUsingGET()

Get all reservations

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    try {
      List<ReservationDTORes> result = apiInstance.getAllReservationsUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#getAllReservationsUsingGET");
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

<a name="getReservationByIdUsingGET"></a>
# **getReservationByIdUsingGET**
> ReservationDTORes getReservationByIdUsingGET(id)

Get reservation by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to consult
    try {
      ReservationDTORes result = apiInstance.getReservationByIdUsingGET(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#getReservationByIdUsingGET");
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

<a name="updateReservationUsingPUT"></a>
# **updateReservationUsingPUT**
> ReservationDTORes updateReservationUsingPUT(id, reservationDTOReq)

Update a reservation

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ReservationControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    ReservationControllerApi apiInstance = new ReservationControllerApi(defaultClient);
    Long id = 56L; // Long | Reservation ID to update
    ReservationDTOReq reservationDTOReq = new ReservationDTOReq(); // ReservationDTOReq | 
    try {
      ReservationDTORes result = apiInstance.updateReservationUsingPUT(id, reservationDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ReservationControllerApi#updateReservationUsingPUT");
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

