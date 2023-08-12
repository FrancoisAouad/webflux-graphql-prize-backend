#!/bin/bash
# /*******************************************************************************
#  *
#  * Copyright (c) {2022-2023} Francois J. Aouad.
#  * All rights reserved. This program and the accompanying materials
#  * are made available under the terms of the GNU General Public License v3.0
#  * which accompanies this distribution, and is available at
#  * https://www.gnu.org/licenses/gpl-3.0.en.html
#  *
#  *******************************************************************************/

# Set environment variables for MongoDB connection
export MONGO_INITDB_ROOT_USERNAME="admin"
export MONGO_INITDB_ROOT_PASSWORD="000000"

# Wait for MongoDB to start
until mongo --host localhost --eval "print(\"waited for connection\")"
do
    sleep 1
done

# Create a new database and user
mongo --host localhost -u "$MONGO_INITDB_ROOT_USERNAME" -p "$MONGO_INITDB_ROOT_PASSWORD" <<EOF
use your_database_name;
db.createUser({
    user: "admin",
    pwd: "000000",
    roles: [{ role: "readWrite", db: "your_database_name" }]
});
EOF

# Run the main container command
exec "$@"