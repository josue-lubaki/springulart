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


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ReservationTimeDTO
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-04T16:51:53.605532400-04:00[America/New_York]")
public class ReservationTimeDTO {
  public static final String SERIALIZED_NAME_HOURS = "hours";
  @SerializedName(SERIALIZED_NAME_HOURS)
  private Integer hours;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_MINUTES = "minutes";
  @SerializedName(SERIALIZED_NAME_MINUTES)
  private Integer minutes;


  public ReservationTimeDTO hours(Integer hours) {
    
    this.hours = hours;
    return this;
  }

   /**
   * Get hours
   * @return hours
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getHours() {
    return hours;
  }


  public void setHours(Integer hours) {
    this.hours = hours;
  }


  public ReservationTimeDTO id(Long id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Long getId() {
    return id;
  }


  public void setId(Long id) {
    this.id = id;
  }


  public ReservationTimeDTO minutes(Integer minutes) {
    
    this.minutes = minutes;
    return this;
  }

   /**
   * Get minutes
   * @return minutes
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Integer getMinutes() {
    return minutes;
  }


  public void setMinutes(Integer minutes) {
    this.minutes = minutes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReservationTimeDTO reservationTimeDTO = (ReservationTimeDTO) o;
    return Objects.equals(this.hours, reservationTimeDTO.hours) &&
        Objects.equals(this.id, reservationTimeDTO.id) &&
        Objects.equals(this.minutes, reservationTimeDTO.minutes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hours, id, minutes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReservationTimeDTO {\n");
    sb.append("    hours: ").append(toIndentedString(hours)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    minutes: ").append(toIndentedString(minutes)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

