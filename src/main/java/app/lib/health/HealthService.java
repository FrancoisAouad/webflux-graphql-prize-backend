/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.health;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class HealthService {
  private final ReactiveMongoTemplate reactiveMongoTemplate;

  public HealthService(ReactiveMongoTemplate reactiveMongoTemplate) {
    this.reactiveMongoTemplate = reactiveMongoTemplate;
  }

  public Mono<String> getHealth() {
    try {
      reactiveMongoTemplate.getMongoDatabase();
      return Mono.just("up");
    } catch (Exception e) {
      return Mono.just("down");
    }
  }
}
