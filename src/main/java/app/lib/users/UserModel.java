/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.users;

// import org.springframework.data.annotation.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel {
  @Field(name = "firstName") private String firstName;

  @Field(name = "lastName") private String lastName;

  @Field(name = "username") private String username;

  @Field(name = "email") private String email;

  @Field(name = "password") private String password;

  @Field(name = "emailToken") private String emailToken;

  @Field(name = "isVerified") private Boolean isVerified;

  @Field(name = "role") private String role;

  @Field(name = "createdAt") private Date createdAt;

  @Field(name = "updatedAt") private Date updatedAt;
}