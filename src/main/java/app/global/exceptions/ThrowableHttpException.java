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

// potentially make abstract class
public class ThrowableHttpException
    extends Throwable implements HttpExceptionInterface {
  private final int statusCode;
  private final String message;

  public ThrowableHttpException(int statusCode, String message) {
    super();
    this.statusCode = statusCode;
    this.message = message;
  }

  public static ResponseEntity<?>
  handleResponse(HttpExceptionInterface exception) {
    return ResponseEntity.status(exception.getStatusCode())
        .body(exception.getMessage());
  }

  @Override
  public int getStatusCode() {
    return statusCode;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
