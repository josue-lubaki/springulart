# RegistrationOfAccountsApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authenticateUserUsingPOST**](RegistrationOfAccountsApi.md#authenticateUserUsingPOST) | **POST** /auth/login | Login
[**registerUserUsingPOST**](RegistrationOfAccountsApi.md#registerUserUsingPOST) | **POST** /auth/register | Register
[**resetPasswordUsingPOST**](RegistrationOfAccountsApi.md#resetPasswordUsingPOST) | **POST** /auth/reset-password | Reset password


<a name="authenticateUserUsingPOST"></a>
# **authenticateUserUsingPOST**
> LoginDTO authenticateUserUsingPOST(authDTO)

Login

Login to the application

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RegistrationOfAccountsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    RegistrationOfAccountsApi apiInstance = new RegistrationOfAccountsApi(defaultClient);
    AuthDTO authDTO = new AuthDTO(); // AuthDTO | 
    try {
      LoginDTO result = apiInstance.authenticateUserUsingPOST(authDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RegistrationOfAccountsApi#authenticateUserUsingPOST");
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
 **authDTO** | [**AuthDTO**](AuthDTO.md)|  | [optional]

### Return type

[**LoginDTO**](LoginDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**400** | Bad Request |  -  |

<a name="registerUserUsingPOST"></a>
# **registerUserUsingPOST**
> UserDTO registerUserUsingPOST(apartment, city, dob, email, fname, imageURL, lname, password, phone, role, state, street, zip)

Register

Register to the application

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RegistrationOfAccountsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    RegistrationOfAccountsApi apiInstance = new RegistrationOfAccountsApi(defaultClient);
    String apartment = "apartment_example"; // String | 
    String city = "city_example"; // String | 
    LocalDate dob = new LocalDate(); // LocalDate | 
    String email = "email_example"; // String | 
    String fname = "fname_example"; // String | 
    File imageURL = new File("/path/to/file"); // File | 
    String lname = "lname_example"; // String | 
    String password = "password_example"; // String | 
    String phone = "phone_example"; // String | 
    String role = "role_example"; // String | 
    String state = "state_example"; // String | 
    String street = "street_example"; // String | 
    String zip = "zip_example"; // String | 
    try {
      UserDTO result = apiInstance.registerUserUsingPOST(apartment, city, dob, email, fname, imageURL, lname, password, phone, role, state, street, zip);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RegistrationOfAccountsApi#registerUserUsingPOST");
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
 **apartment** | **String**|  | [optional]
 **city** | **String**|  | [optional]
 **dob** | **LocalDate**|  | [optional]
 **email** | **String**|  | [optional]
 **fname** | **String**|  | [optional]
 **imageURL** | **File**|  | [optional]
 **lname** | **String**|  | [optional]
 **password** | **String**|  | [optional]
 **phone** | **String**|  | [optional]
 **role** | **String**|  | [optional]
 **state** | **String**|  | [optional]
 **street** | **String**|  | [optional]
 **zip** | **String**|  | [optional]

### Return type

[**UserDTO**](UserDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |
**201** | Successfully created an account |  -  |
**400** | Bad Request |  -  |

<a name="resetPasswordUsingPOST"></a>
# **resetPasswordUsingPOST**
> Map&lt;String, String&gt; resetPasswordUsingPOST(authDTO)

Reset password

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RegistrationOfAccountsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    RegistrationOfAccountsApi apiInstance = new RegistrationOfAccountsApi(defaultClient);
    AuthDTO authDTO = new AuthDTO(); // AuthDTO | 
    try {
      Map<String, String> result = apiInstance.resetPasswordUsingPOST(authDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RegistrationOfAccountsApi#resetPasswordUsingPOST");
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
 **authDTO** | [**AuthDTO**](AuthDTO.md)|  | [optional]

### Return type

**Map&lt;String, String&gt;**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully reset password |  -  |

