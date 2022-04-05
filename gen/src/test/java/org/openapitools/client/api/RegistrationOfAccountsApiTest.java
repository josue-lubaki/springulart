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
import org.openapitools.client.model.AuthDTO;
import java.io.File;
import org.threeten.bp.LocalDate;
import org.openapitools.client.model.LoginDTO;
import org.openapitools.client.model.UserDTO;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for RegistrationOfAccountsApi
 */
@Ignore
public class RegistrationOfAccountsApiTest {

    private final RegistrationOfAccountsApi api = new RegistrationOfAccountsApi();

    
    /**
     * Login
     *
     * Login to the application
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void authenticateUserUsingPOSTTest() throws ApiException {
        AuthDTO authDTO = null;
        LoginDTO response = api.authenticateUserUsingPOST(authDTO);

        // TODO: test validations
    }
    
    /**
     * Register
     *
     * Register to the application
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void registerUserUsingPOSTTest() throws ApiException {
        String apartment = null;
        String city = null;
        LocalDate dob = null;
        String email = null;
        String fname = null;
        File imageURL = null;
        String lname = null;
        String password = null;
        String phone = null;
        String role = null;
        String state = null;
        String street = null;
        String zip = null;
        UserDTO response = api.registerUserUsingPOST(apartment, city, dob, email, fname, imageURL, lname, password, phone, role, state, street, zip);

        // TODO: test validations
    }
    
    /**
     * Reset password
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void resetPasswordUsingPOSTTest() throws ApiException {
        AuthDTO authDTO = null;
        Map<String, String> response = api.resetPasswordUsingPOST(authDTO);

        // TODO: test validations
    }
    
}
