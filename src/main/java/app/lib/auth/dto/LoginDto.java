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

// import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginDto {
  @NotEmpty private String username;
  @NotEmpty @Size(min = 8, max = 32) private String password;
}
