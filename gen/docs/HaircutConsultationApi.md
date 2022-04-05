# HaircutConsultationApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHaircutUsingGET**](HaircutConsultationApi.md#getHaircutUsingGET) | **GET** /api/v1/haircuts/{id} | Get haircut by id
[**getHaircutsUsingGET**](HaircutConsultationApi.md#getHaircutsUsingGET) | **GET** /api/v1/haircuts | Get all haircuts


<a name="getHaircutUsingGET"></a>
# **getHaircutUsingGET**
> HaircutDTO getHaircutUsingGET(id)

Get haircut by id

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutConsultationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    HaircutConsultationApi apiInstance = new HaircutConsultationApi(defaultClient);
    String id = "id_example"; // String | Identifier of the hairstyle to consult
    try {
      HaircutDTO result = apiInstance.getHaircutUsingGET(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutConsultationApi#getHaircutUsingGET");
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
 **id** | **String**| Identifier of the hairstyle to consult |

### Return type

[**HaircutDTO**](HaircutDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved haircut |  -  |

<a name="getHaircutsUsingGET"></a>
# **getHaircutsUsingGET**
> List&lt;HaircutDTO&gt; getHaircutsUsingGET()

Get all haircuts

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.HaircutConsultationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080");

    HaircutConsultationApi apiInstance = new HaircutConsultationApi(defaultClient);
    try {
      List<HaircutDTO> result = apiInstance.getHaircutsUsingGET();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling HaircutConsultationApi#getHaircutsUsingGET");
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

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully retrieved all haircuts |  -  |

