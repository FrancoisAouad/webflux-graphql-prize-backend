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

/* Spring */

/* Services */
import app.lib.auth.dto.RegisterUserDto;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
/* Jakarta */
import reactor.core.publisher.Mono;


@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @MutationMapping("register")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Void> register(@Arguments RegisterUserDto user) {
    return userService.register(user).then();
  }
}
