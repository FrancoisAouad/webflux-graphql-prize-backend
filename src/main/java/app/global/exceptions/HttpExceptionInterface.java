/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.global.exceptions;

import org.springframework.http.ResponseEntity;

public interface HttpExceptionInterface {
  int statusCode = 500;
  String message = "Internal Server Error";

  public int getStatusCode();

  public String getMessage();

  public static ResponseEntity<?>
  handleResponse(HttpExceptionInterface exception) {
    return ResponseEntity.status(exception.getStatusCode())
        .body(exception.getMessage());
  }
}
