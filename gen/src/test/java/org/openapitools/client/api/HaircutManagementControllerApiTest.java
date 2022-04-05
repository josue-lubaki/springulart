/*
 * Springular REST API
 * Springular REST API
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: josue.lubaki@uqtr.ca
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.HaircutDTO;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for HaircutManagementControllerApi
 */
@Ignore
public class HaircutManagementControllerApiTest {

    private final HaircutManagementControllerApi api = new HaircutManagementControllerApi();

    
    /**
     * Create haircut
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void createHaircutUsingPOSTTest() throws ApiException {
        HaircutDTO haircutDTO = null;
        HaircutDTO response = api.createHaircutUsingPOST(haircutDTO);

        // TODO: test validations
    }
    
    /**
     * Delete haircut
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void deleteHaircutUsingDELETETest() throws ApiException {
        String id = null;
        api.deleteHaircutUsingDELETE(id);

        // TODO: test validations
    }
    
    /**
     * Get haircut by ID
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getHaircutUsingGET1Test() throws ApiException {
        String id = null;
        HaircutDTO response = api.getHaircutUsingGET1(id);

        // TODO: test validations
    }
    
    /**
     * Get all haircuts
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getHaircutsUsingGET1Test() throws ApiException {
        List<HaircutDTO> response = api.getHaircutsUsingGET1();

        // TODO: test validations
    }
    
    /**
     * Update haircut
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void updateHaircutUsingPUTTest() throws ApiException {
        String id = null;
        HaircutDTO haircutDTO = null;
        HaircutDTO response = api.updateHaircutUsingPUT(id, haircutDTO);

        // TODO: test validations
    }
    
}