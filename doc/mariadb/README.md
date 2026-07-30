Galleon Layers
=========

* `mariadb-default-datasource`: Provision the `MariaDBDS` non xa datasource and configure it as the default one. Depends on `mariadb-datasource` layer.
* `mariadb-datasource`: Provision the `MariaDBDS` non xa datasource. Depends on `mariadb-driver` layer.
* `mariadb-driver`: Provision the `mariadb` driver. This layer installs the JBoss Modules module `org.mariadb.jdbc`.

Build time configuration
========================

The following variable can be set at Galleon provisioning time to override the
default driver version that is embedded in the feature pack.

Optional configuration
-----------------------

* `MARIADB_DRIVER_VERSION`

  * Description: The version of the `org.mariadb.jdbc:mariadb-java-client` Maven artifact.
    When set, this version is used instead of the default version bundled with the
    feature pack.
  * Default value: current version bundled with the feature pack
  * Required: False
  * System Property: `org.wildfly.datasources.mariadb.driver.version`

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Required configuration
==============

* `MARIADB_DATABASE`

  * Description: Defines the database name to be used in the datasource’s `connection-url` property.
  * No default value.
  * Required: True if `MARIADB_URL` is not set.
  * System Property: `org.wildfly.datasources.mariadb.database`

* `MARIADB_PASSWORD`

  * Description: Defines the password for the datasource.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.mariadb.password`

* `MARIADB_URL`

  * Description: Defines the connection URL for the datasource. 
  * Default Value: `jdbc:mariadb://${MARIADB_HOST}:${MARIADB_PORT}/${MARIADB_DATABASE}`
  * Required: True if `MARIADB_PORT/HOST/DATABASE` are not set.
  * System Property: `org.wildfly.datasources.mariadb.connection-url`

* `MARIADB_USER`

  * Description: Defines the username for the datasource. 
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.mariadb.user-name`

Optional configuration
==============

* `MARIADB_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.mariadb.background-validation`

* `MARIADB_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.mariadb.background-validation-millis`

* `MARIADB_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker`
  * System Property: `org.wildfly.datasources.mariadb.valid-connection-checker-class-name`

* `MARIADB_DATASOURCE`

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `MariaDBDS`
  * System Property: `org.wildfly.datasources.mariadb.datasource`

* `MARIADB_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mariadb.enabled`

* `MARIADB_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter`
  * System Property: `org.wildfly.datasources.mariadb.exception-sorter-class-name`

* `MARIADB_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.mariadb.flush-strategy`

* `MARIADB_HOST` (or `MARIADB_SERVICE_HOST`)

  * Description: Defines the database server’s host name or IP address to be used in the datasource’s `connection-url` property.
  * Default Value: `localhost`
  * Required: True if `MARIADB_URL` is not set.
  * System Property: `org.wildfly.datasources.mariadb.host`

* `MARIADB_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.mariadb.idle-timeout-minutes`

* `MARIADB_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value: `java:jboss/datasources/${MARIADB_DATASOURCE}`
  * System Property: `org.wildfly.datasources.mariadb.jndi-name`

* `MARIADB_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mariadb.jta`

* `MARIADB_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.mariadb.max-pool-size`

* `MARIADB_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.mariadb.min-pool-size`

* `MARIADB_PORT` (or `MARIADB_SERVICE_PORT`)

  * Description: Defines the database server’s port to be used in the datasource’s `connection-url` property. 
  * Default Value: `3306`
  * Required: True if `MARIADB_URL` is not set.
  * System Property: `org.wildfly.datasources.mariadb.port`

* `MARIADB_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid value: No checker provided by JBoss, only custom stale checker can be provided.
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.mariadb.stale-connection-checker-class-name`

* `MARIADB_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.mariadb.transaction-isolation`

* `MARIADB_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mariadb.validate-on-match`

