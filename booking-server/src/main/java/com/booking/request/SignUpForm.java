
  package com.booking.request;
  

  
  import javax.validation.constraints.*;

import lombok.Data;
  
  
  @Data
  public class SignUpForm {
  
  @NotBlank
  
  @Size(min = 3, max = 50) private String name;
  
  @NotBlank
  
  @Size(min = 3, max = 50) private String username;
  
  @NotBlank
  
  @Size(max = 60)
  
  @Email private String email;
  
  @NotBlank
  
 private String role;
  
  @NotBlank
  
  @Size(min = 6, max = 40) private String password;
  
  }
  
 