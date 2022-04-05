# UserControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteMyAccountUsingDELETE**](UserControllerApi.md#deleteMyAccountUsingDELETE) | **DELETE** /api/v1/users/{id} | Delete a user by ID
[**getUserUsingGET**](UserControllerApi.md#getUserUsingGET) | **GET** /api/v1/users/{id} | Get a user by ID
[**updateUserUsingPUT**](UserControllerApi.md#updateUserUsingPUT) | **PUT** /api/v1/users/{id} | Update a user by ID


<a name="deleteMyAccountUsingDELETE"></a>
# **deleteMyAccountUsingDELETE**
> deleteMyAccountUsingDELETE(id)

Delete a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserControllerApi apiInstance = new UserControllerApi(defaultClient);
    Long id = 56L; // Long | User ID to delete
    try {
      apiInstance.deleteMyAccountUsingDELETE(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserControllerApi#deleteMyAccountUsingDELETE");
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
 **id** | **Long**| User ID to delete |

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
**204** | Successfully Deleted a user |  -  |

<a name="getUserUsingGET"></a>
# **getUserUsingGET**
> UserDTORes getUserUsingGET(id)

Get a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserControllerApi apiInstance = new UserControllerApi(defaultClient);
    Long id = 56L; // Long | User ID to consult
    try {
      UserDTORes result = apiInstance.getUserUsingGET(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserControllerApi#getUserUsingGET");
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
 **id** | **Long**| User ID to consult |

### Return type

[**UserDTORes**](UserDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved a user |  -  |

<a name="updateUserUsingPUT"></a>
# **updateUserUsingPUT**
> UserDTORes updateUserUsingPUT(id, userDTOReq)

Update a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserControllerApi apiInstance = new UserControllerApi(defaultClient);
    Long id = 56L; // Long | User ID to update
    UserDTOReq userDTOReq = new UserDTOReq(); // UserDTOReq | 
    try {
      UserDTORes result = apiInstance.updateUserUsingPUT(id, userDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserControllerApi#updateUserUsingPUT");
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
 **id** | **Long**| User ID to update |
 **userDTOReq** | [**UserDTOReq**](UserDTOReq.md)|  | [optional]

### Return type

[**UserDTORes**](UserDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully updated a user |  -  |

