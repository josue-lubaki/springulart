# UserManagementControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteUserUsingDELETE**](UserManagementControllerApi.md#deleteUserUsingDELETE) | **DELETE** /management/api/v1/users/{id} | Delete a user by ID
[**getUserUsingGET1**](UserManagementControllerApi.md#getUserUsingGET1) | **GET** /management/api/v1/users/{id} | Get a user by ID
[**getUsersUsingGET**](UserManagementControllerApi.md#getUsersUsingGET) | **GET** /management/api/v1/users | Get all users
[**registerNewUserUsingPOST**](UserManagementControllerApi.md#registerNewUserUsingPOST) | **POST** /management/api/v1/users | Create a new user
[**updateUserUsingPUT1**](UserManagementControllerApi.md#updateUserUsingPUT1) | **PUT** /management/api/v1/users/{id} | Update a user by ID


<a name="deleteUserUsingDELETE"></a>
# **deleteUserUsingDELETE**
> deleteUserUsingDELETE(id)

Delete a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserManagementControllerApi apiInstance = new UserManagementControllerApi(defaultClient);
    Long id = 56L; // Long | user ID to delete
    try {
      apiInstance.deleteUserUsingDELETE(id);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserManagementControllerApi#deleteUserUsingDELETE");
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
 **id** | **Long**| user ID to delete |

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

<a name="getUserUsingGET1"></a>
# **getUserUsingGET1**
> UserDTORes getUserUsingGET1(id)

Get a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserManagementControllerApi apiInstance = new UserManagementControllerApi(defaultClient);
    Long id = 56L; // Long | user ID to consult
    try {
      UserDTORes result = apiInstance.getUserUsingGET1(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserManagementControllerApi#getUserUsingGET1");
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
 **id** | **Long**| user ID to consult |

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

<a name="getUsersUsingGET"></a>
# **getUsersUsingGET**
> List&lt;UserDTORes&gt; getUsersUsingGET()

Get all users

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserManagementControllerApi apiInstance = new UserManagementControllerApi(defaultClient);
    try {
      List<UserDTORes> result = apiInstance.getUsersUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserManagementControllerApi#getUsersUsingGET");
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

[**List&lt;UserDTORes&gt;**](UserDTORes.md)

### Authorization

[JWT](../README.md#JWT)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved all users |  -  |

<a name="registerNewUserUsingPOST"></a>
# **registerNewUserUsingPOST**
> UserDTORes registerNewUserUsingPOST(signupDTO)

Create a new user

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserManagementControllerApi apiInstance = new UserManagementControllerApi(defaultClient);
    SignupDTO signupDTO = new SignupDTO(); // SignupDTO | 
    try {
      UserDTORes result = apiInstance.registerNewUserUsingPOST(signupDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserManagementControllerApi#registerNewUserUsingPOST");
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
 **signupDTO** | [**SignupDTO**](SignupDTO.md)|  | [optional]

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
**200** | OK |  -  |
**400** | Bad Request |  -  |

<a name="updateUserUsingPUT1"></a>
# **updateUserUsingPUT1**
> UserDTORes updateUserUsingPUT1(id, userDTOReq)

Update a user by ID

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserManagementControllerApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");
    
    // Configure API key authorization: JWT
    ApiKeyAuth JWT = (ApiKeyAuth) defaultClient.getAuthentication("JWT");
    JWT.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //JWT.setApiKeyPrefix("Token");

    UserManagementControllerApi apiInstance = new UserManagementControllerApi(defaultClient);
    Long id = 56L; // Long | user ID to update
    UserDTOReq userDTOReq = new UserDTOReq(); // UserDTOReq | 
    try {
      UserDTORes result = apiInstance.updateUserUsingPUT1(id, userDTOReq);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserManagementControllerApi#updateUserUsingPUT1");
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
 **id** | **Long**| user ID to update |
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

