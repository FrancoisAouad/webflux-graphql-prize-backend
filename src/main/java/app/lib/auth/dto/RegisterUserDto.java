/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.auth.dto;

// Lombok

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegisterUserDto {
  @NotBlank @Size(min = 2, max = 40) private String firstName;

  @NotBlank @Size(min = 2, max = 40) private String lastName;

  @NotBlank @Size(max = 16) private String username;

  @Email @NotBlank @Size(max = 40) private String email;

  @NotBlank @Size(min = 8, max = 120) private String password;

  @NotBlank @Size(min = 8, max = 120) private String confirmPassword;

  @AssertTrue(message = "Passwords do not match")
  private boolean isPasswordMatching() {
    return password.equals(confirmPassword);
  }
}