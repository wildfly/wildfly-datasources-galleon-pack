/*
 * Copyright 2024 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.datasources.test;

import java.io.File;
import java.io.FilenameFilter;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Verifies that overriding a driver version at Galleon provisioning time via the
 * {@code *_DRIVER_VERSION} environment variable (or the corresponding Java system
 * property) causes the provisioned server to contain the requested driver JAR
 * instead of the default one bundled with the feature pack.
 * <p>
 * This test does <em>not</em> start WildFly; it only inspects the filesystem of
 * the pre-provisioned server directory to check which JAR file is present in the
 * driver's JBoss Modules module directory.
 * <p>
 * The provisioned server is built by the {@code build-driver-version-override-server}
 * Maven execution in the {@code test-compile} phase. The system property
 * {@code org.wildfly.datasources.postgresql.driver.version} is set by
 * {@code properties-maven-plugin} before provisioning runs, causing Galleon to
 * resolve the driver artifact at the requested version.
 * The expected version string is passed in via the
 * {@code org.wildfly.datasources.postgresql.driver.version} system property.
 */
public class DriverVersionTestCase {

    private static final String TEST_SERVER_DIR_PROP = "test.server.dir";
    private static final String TEST_PG_VERSION_PROP = "org.wildfly.datasources.postgresql.driver.version";

    /** Relative path to the PostgreSQL JBoss Modules module directory. */
    private static final String POSTGRESQL_MODULE_DIR =
            "modules/org/postgresql/jdbc/main";

    @Test
    public void testPostgresqlDriverVersionOverride() {
        String serverDir = System.getProperty(TEST_SERVER_DIR_PROP);
        assertNotNull("System property '" + TEST_SERVER_DIR_PROP + "' must be set", serverDir);

        String expectedVersion = System.getProperty(TEST_PG_VERSION_PROP);
        assertNotNull("System property '" + TEST_PG_VERSION_PROP + "' must be set", expectedVersion);

        File moduleDir = new File(serverDir, POSTGRESQL_MODULE_DIR);
        assertTrue("PostgreSQL module directory does not exist: " + moduleDir.getAbsolutePath(),
                moduleDir.isDirectory());

        // The expected JAR produced by Galleon from org.postgresql:postgresql:<version>
        String expectedJar = "postgresql-" + expectedVersion + ".jar";

        File[] jars = moduleDir.listFiles((FilenameFilter) (dir, name) -> name.endsWith(".jar"));
        assertNotNull("Could not list JAR files in " + moduleDir.getAbsolutePath(), jars);

        if (jars.length == 0) {
            fail("No JAR file found in " + moduleDir.getAbsolutePath());
        }
        if (jars.length > 1) {
            StringBuilder found = new StringBuilder();
            for (File f : jars) {
                found.append(f.getName()).append(' ');
            }
            fail("Expected exactly one JAR in " + moduleDir.getAbsolutePath()
                    + " but found: " + found.toString().trim());
        }

        String actualJar = jars[0].getName();
        assertTrue("Expected driver JAR '" + expectedJar + "' but found '" + actualJar + "'",
                actualJar.equals(expectedJar));
    }
}
