/*******************************************************************************
 *
 * Copyright (c) {2022-2023} Francois J. Aouad.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3.0
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *******************************************************************************/

package app.lib.auth.jwt.accessTokens;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository
    extends ReactiveMongoRepository<AccessTokenModel, String> {}
