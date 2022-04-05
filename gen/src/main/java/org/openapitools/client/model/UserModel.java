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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.openapitools.client.model.AddressModel;
import org.openapitools.client.model.CredentialModel;
import org.openapitools.client.model.ReservationModel;
import org.threeten.bp.LocalDate;

/**
 * UserModel
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-04-04T16:51:53.605532400-04:00[America/New_York]")
public class UserModel {
  public static final String SERIALIZED_NAME_ADDRESS = "address";
  @SerializedName(SERIALIZED_NAME_ADDRESS)
  private AddressModel address;

  public static final String SERIALIZED_NAME_CREATED = "created";
  @SerializedName(SERIALIZED_NAME_CREATED)
  private LocalDate created;

  public static final String SERIALIZED_NAME_CREDENTIAL = "credential";
  @SerializedName(SERIALIZED_NAME_CREDENTIAL)
  private CredentialModel credential;

  public static final String SERIALIZED_NAME_DELETED = "deleted";
  @SerializedName(SERIALIZED_NAME_DELETED)
  private Boolean deleted;

  public static final String SERIALIZED_NAME_DOB = "dob";
  @SerializedName(SERIALIZED_NAME_DOB)
  private LocalDate dob;

  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_FNAME = "fname";
  @SerializedName(SERIALIZED_NAME_FNAME)
  private String fname;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_IMAGE_U_R_L = "imageURL";
  @SerializedName(SERIALIZED_NAME_IMAGE_U_R_L)
  private String imageURL;

  public static final String SERIALIZED_NAME_LNAME = "lname";
  @SerializedName(SERIALIZED_NAME_LNAME)
  private String lname;

  public static final String SERIALIZED_NAME_PASSWORD = "password";
  @SerializedName(SERIALIZED_NAME_PASSWORD)
  private String password;

  public static final String SERIALIZED_NAME_PHONE = "phone";
  @SerializedName(SERIALIZED_NAME_PHONE)
  private String phone;

  public static final String SERIALIZED_NAME_RESERVATION_MODEL_BARBER = "reservationModelBarber";
  @SerializedName(SERIALIZED_NAME_RESERVATION_MODEL_BARBER)
  private Set<ReservationModel> reservationModelBarber = null;

  public static final String SERIALIZED_NAME_RESERVATION_MODEL_CLIENT = "reservationModelClient";
  @SerializedName(SERIALIZED_NAME_RESERVATION_MODEL_CLIENT)
  private Set<ReservationModel> reservationModelClient = null;

  public static final String SERIALIZED_NAME_ROLE = "role";
  @SerializedName(SERIALIZED_NAME_ROLE)
  private String role;

  public static final String SERIALIZED_NAME_UPDATED = "updated";
  @SerializedName(SERIALIZED_NAME_UPDATED)
  private LocalDate updated;


  public UserModel address(AddressModel address) {
    
    this.address = address;
    return this;
  }

   /**
   * Get address
   * @return address
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public AddressModel getAddress() {
    return address;
  }


  public void setAddress(AddressModel address) {
    this.address = address;
  }


  public UserModel created(LocalDate created) {
    
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public LocalDate getCreated() {
    return created;
  }


  public void setCreated(LocalDate created) {
    this.created = created;
  }


  public UserModel credential(CredentialModel credential) {
    
    this.credential = credential;
    return this;
  }

   /**
   * Get credential
   * @return credential
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public CredentialModel getCredential() {
    return credential;
  }


  public void setCredential(CredentialModel credential) {
    this.credential = credential;
  }


  public UserModel deleted(Boolean deleted) {
    
    this.deleted = deleted;
    return this;
  }

   /**
   * Get deleted
   * @return deleted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getDeleted() {
    return deleted;
  }


  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }


  public UserModel dob(LocalDate dob) {
    
    this.dob = dob;
    return this;
  }

   /**
   * Get dob
   * @return dob
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public LocalDate getDob() {
    return dob;
  }


  public void setDob(LocalDate dob) {
    this.dob = dob;
  }


  public UserModel email(String email) {
    
    this.email = email;
    return this;
  }

   /**
   * Get email
   * @return email
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public UserModel fname(String fname) {
    
    this.fname = fname;
    return this;
  }

   /**
   * Get fname
   * @return fname
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getFname() {
    return fname;
  }


  public void setFname(String fname) {
    this.fname = fname;
  }


  public UserModel id(Long id) {
    
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


  public UserModel imageURL(String imageURL) {
    
    this.imageURL = imageURL;
    return this;
  }

   /**
   * Get imageURL
   * @return imageURL
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getImageURL() {
    return imageURL;
  }


  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }


  public UserModel lname(String lname) {
    
    this.lname = lname;
    return this;
  }

   /**
   * Get lname
   * @return lname
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getLname() {
    return lname;
  }


  public void setLname(String lname) {
    this.lname = lname;
  }


  public UserModel password(String password) {
    
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPassword() {
    return password;
  }


  public void setPassword(String password) {
    this.password = password;
  }


  public UserModel phone(String phone) {
    
    this.phone = phone;
    return this;
  }

   /**
   * Get phone
   * @return phone
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPhone() {
    return phone;
  }


  public void setPhone(String phone) {
    this.phone = phone;
  }


  public UserModel reservationModelBarber(Set<ReservationModel> reservationModelBarber) {
    
    this.reservationModelBarber = reservationModelBarber;
    return this;
  }

  public UserModel addReservationModelBarberItem(ReservationModel reservationModelBarberItem) {
    if (this.reservationModelBarber == null) {
      this.reservationModelBarber = new LinkedHashSet<ReservationModel>();
    }
    this.reservationModelBarber.add(reservationModelBarberItem);
    return this;
  }

   /**
   * Get reservationModelBarber
   * @return reservationModelBarber
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Set<ReservationModel> getReservationModelBarber() {
    return reservationModelBarber;
  }


  public void setReservationModelBarber(Set<ReservationModel> reservationModelBarber) {
    this.reservationModelBarber = reservationModelBarber;
  }


  public UserModel reservationModelClient(Set<ReservationModel> reservationModelClient) {
    
    this.reservationModelClient = reservationModelClient;
    return this;
  }

  public UserModel addReservationModelClientItem(ReservationModel reservationModelClientItem) {
    if (this.reservationModelClient == null) {
      this.reservationModelClient = new LinkedHashSet<ReservationModel>();
    }
    this.reservationModelClient.add(reservationModelClientItem);
    return this;
  }

   /**
   * Get reservationModelClient
   * @return reservationModelClient
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Set<ReservationModel> getReservationModelClient() {
    return reservationModelClient;
  }


  public void setReservationModelClient(Set<ReservationModel> reservationModelClient) {
    this.reservationModelClient = reservationModelClient;
  }


  public UserModel role(String role) {
    
    this.role = role;
    return this;
  }

   /**
   * Get role
   * @return role
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getRole() {
    return role;
  }


  public void setRole(String role) {
    this.role = role;
  }


  public UserModel updated(LocalDate updated) {
    
    this.updated = updated;
    return this;
  }

   /**
   * Get updated
   * @return updated
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public LocalDate getUpdated() {
    return updated;
  }


  public void setUpdated(LocalDate updated) {
    this.updated = updated;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserModel userModel = (UserModel) o;
    return Objects.equals(this.address, userModel.address) &&
        Objects.equals(this.created, userModel.created) &&
        Objects.equals(this.credential, userModel.credential) &&
        Objects.equals(this.deleted, userModel.deleted) &&
        Objects.equals(this.dob, userModel.dob) &&
        Objects.equals(this.email, userModel.email) &&
        Objects.equals(this.fname, userModel.fname) &&
        Objects.equals(this.id, userModel.id) &&
        Objects.equals(this.imageURL, userModel.imageURL) &&
        Objects.equals(this.lname, userModel.lname) &&
        Objects.equals(this.password, userModel.password) &&
        Objects.equals(this.phone, userModel.phone) &&
        Objects.equals(this.reservationModelBarber, userModel.reservationModelBarber) &&
        Objects.equals(this.reservationModelClient, userModel.reservationModelClient) &&
        Objects.equals(this.role, userModel.role) &&
        Objects.equals(this.updated, userModel.updated);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, created, credential, deleted, dob, email, fname, id, imageURL, lname, password, phone, reservationModelBarber, reservationModelClient, role, updated);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserModel {\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    credential: ").append(toIndentedString(credential)).append("\n");
    sb.append("    deleted: ").append(toIndentedString(deleted)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    fname: ").append(toIndentedString(fname)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imageURL: ").append(toIndentedString(imageURL)).append("\n");
    sb.append("    lname: ").append(toIndentedString(lname)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    reservationModelBarber: ").append(toIndentedString(reservationModelBarber)).append("\n");
    sb.append("    reservationModelClient: ").append(toIndentedString(reservationModelClient)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
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
