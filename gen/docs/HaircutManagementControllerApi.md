# HaircutManagementControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createHaircutUsingPOST**](HaircutManagementControllerApi.md#createHaircutUsingPOST) | **POST** /management/api/v1/haircuts | Create haircut
[**deleteHaircutUsingDELETE**](HaircutManagementControllerApi.md#deleteHaircutUsingDELETE) | **DELETE** /management/api/v1/haircuts/{id} | Delete haircut
[**getHaircutUsingGET1**](HaircutManagementControllerApi.md#getHaircutUsingGET1) | **GET** /management/api/v1/haircuts/{id} | Get haircut by ID
[**getHaircutsUsingGET1**](HaircutManagementControllerApi.md#getHaircutsUsingGET1) | **GET** /management/api/v1/haircuts | Get all haircuts
[**updateHaircutUsingPUT**](HaircutManagementControllerApi.md#updateHaircutUsingPUT) | **PUT** /management/api/v1/haircuts/{id} | Update haircut


<a name="createHaircutUsingPOST"></a>
# **createHaircutUsingPOST**
> HaircutDTO createHaircutUsingPOST(haircutDTO)

Create haircut

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    HaircutManagementControllerApi apiInstance = new HaircutManagementControllerApi(defaultClient);
    HaircutDTO haircutDTO = new HaircutDTO(); // HaircutDTO | 
    try {
      HaircutDTO result = apiInstance.createHaircutUsingPOST(haircutDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutManagementControllerApi#createHaircutUsingPOST");
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
 **haircutDTO** | [**HaircutDTO**](HaircutDTO.md)|  | [optional]

### Return type

[**HaircutDTO**](HaircutDTO.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Created |  -  |
**400** | Bad Request |  -  |

<a name="deleteHaircutUsingDELETE"></a>
# **deleteHaircutUsingDELETE**
> deleteHaircutUsingDELETE(id)

Delete haircut

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    HaircutManagementControllerApi apiInstance = new HaircutManagementControllerApi(defaultClient);
    String id = "id_example"; // String | id
    try {
      apiInstance.deleteHaircutUsingDELETE(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutManagementControllerApi#deleteHaircutUsingDELETE");
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
 **id** | **String**| id |

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
**200** | Deleted hair |  -  |
**400** | Bad Request |  -  |

<a name="getHaircutUsingGET1"></a>
# **getHaircutUsingGET1**
> HaircutDTO getHaircutUsingGET1(id)

Get haircut by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    HaircutManagementControllerApi apiInstance = new HaircutManagementControllerApi(defaultClient);
    String id = "id_example"; // String | id
    try {
      HaircutDTO result = apiInstance.getHaircutUsingGET1(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutManagementControllerApi#getHaircutUsingGET1");
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
 **id** | **String**| id |

### Return type

[**HaircutDTO**](HaircutDTO.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |

<a name="getHaircutsUsingGET1"></a>
# **getHaircutsUsingGET1**
> List&lt;HaircutDTO&gt; getHaircutsUsingGET1()

Get all haircuts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    HaircutManagementControllerApi apiInstance = new HaircutManagementControllerApi(defaultClient);
    try {
      List<HaircutDTO> result = apiInstance.getHaircutsUsingGET1();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutManagementControllerApi#getHaircutsUsingGET1");
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

[**List&lt;HaircutDTO&gt;**](HaircutDTO.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |

<a name="updateHaircutUsingPUT"></a>
# **updateHaircutUsingPUT**
> HaircutDTO updateHaircutUsingPUT(id, haircutDTO)

Update haircut

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    HaircutManagementControllerApi apiInstance = new HaircutManagementControllerApi(defaultClient);
    String id = "id_example"; // String | id
    HaircutDTO haircutDTO = new HaircutDTO(); // HaircutDTO | 
    try {
      HaircutDTO result = apiInstance.updateHaircutUsingPUT(id, haircutDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutManagementControllerApi#updateHaircutUsingPUT");
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
 **id** | **String**| id |
 **haircutDTO** | [**HaircutDTO**](HaircutDTO.md)|  | [optional]

### Return type

[**HaircutDTO**](HaircutDTO.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Updated hair |  -  |
**400** | Bad Request |  -  |

