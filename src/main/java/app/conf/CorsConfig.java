/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class CorsConfig implements WebMvcConfigurer {

  //    @Override
  //    public void addCorsMappings(CorsRegistry registry) {
  //        registry.addMapping("/graphql")
  //                .allowedOrigins("*")
  //                .allowedHeaders("*")
  //                .allowedMethods("GET")
  //                .allowCredentials(true);
  //    }
}
