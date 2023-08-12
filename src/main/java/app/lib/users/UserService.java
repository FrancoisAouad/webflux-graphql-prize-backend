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

import app.lib.auth.dto.RegisterUserDto;
import app.roles.Roles;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@Log4j2
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Mono<ResponseEntity<Object>> register(RegisterUserDto user) {
    UserModel newUser = UserModel.builder()
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .email(user.getEmail())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .isVerified(false)
                            .createdAt(new Date())
                            .updatedAt(new Date())
                            .role(String.valueOf(Roles.USER))
                            .build();
    log.info("USER: " + newUser);
    return userRepository.save(newUser)
        .map(savedUser -> ResponseEntity.ok().build())
        .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
  }
}