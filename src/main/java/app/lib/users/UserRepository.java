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

import app.global.exceptions.ThrowableHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository
    extends ReactiveMongoRepository<UserModel, String> {
  Logger logger = LoggerFactory.getLogger(UserRepository.class);

  Mono<UserModel> findById(String id);

  Mono<UserModel> findByUsername(String username);

  Mono<Boolean> existsByUsername(String username);

  Mono<Boolean> existsByEmail(String email);

  default Mono<String> getUserHashedPassword(String id) {
    return findById(id)
        .map(user -> user.getPassword())
        .switchIfEmpty(
            Mono.error(new ThrowableHttpException(404, "User Not Found")));
  }
}
