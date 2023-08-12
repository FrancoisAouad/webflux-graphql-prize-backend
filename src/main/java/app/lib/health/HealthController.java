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

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class HealthController {
  HealthService healthService;

  public HealthController(HealthService healthService) {
    this.healthService = healthService;
  }

  @QueryMapping("health")
  public Mono<String> getHealth() {
    return healthService.getHealth();
  }
}
