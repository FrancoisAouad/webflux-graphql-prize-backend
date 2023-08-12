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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Builder
@Data
@AllArgsConstructor
public class HttpException implements HttpExceptionInterface {
  private int statusCode;
  private String message;

  public static ResponseEntity<?> handleResponse(int statusCodeValue,
                                                 Object message) {
    return switch (statusCodeValue) {
            case 200 -> ResponseEntity.ok().body(message);
            case 201 -> ResponseEntity.status(HttpStatus.CREATED).body(message);
            case 204 -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
            case 400 -> ResponseEntity.badRequest().body(message);
            case 401 -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            case 403 -> ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
            case 404 -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
            case 409 -> ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        };

    }
}
