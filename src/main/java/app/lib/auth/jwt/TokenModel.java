/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.auth.jwt;

/* Lombok */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jwt_tokens")
public class TokenModel {
  @Id private String id;
  @Field("access_token_id") private String accessToken;

  @Field("refresh_token_id") private String refreshToken;

  @Field("user_id") private String userId;
}
