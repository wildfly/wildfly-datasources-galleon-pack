Galleon Layers
=========

* `postgresql-default-datasource`: Provision the `PostgreSQLDS` non xa datasource and configures it as the default one. Depends on `postgresql-datasource` layer.
* `postgresql-datasource`: Provision the `PostgreSQLDS` non xa datasource. Depends on `postgresql-driver` layer.
* `postgresql-driver`: Provision the `postgresql` driver. This layer installs the JBoss Modules module `org.postgresql.jdbc`.

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Required configuration
==============

* `POSTGRESQL_DATABASE` (or `OPENSHIFT_POSTGRESQL_DB_NAME`)

  * Description: Defines the database name to be used in the datasource’s `connection-url` property.
  * No default value.
  * Required: True if `POSTGRESQL_URL` is not set.
  * System Property: `org.wildfly.datasources.postgresql.database`

* `POSTGRESQL_PASSWORD` (or `OPENSHIFT_POSTGRESQL_DB_PASSWORD`)

  * Description: Defines the password for the datasource.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.postgresql.password`

* `POSTGRESQL_URL`

  * Description: Defines the connection URL for the datasource. 
  * Default Value: `jdbc:postgresql://${POSTGRESQL_HOST}:${POSTGRESQL_PORT}/${POSTGRESQL_DATABASE}`
  * Required: True if `POSTGRESQL_PORT/HOST/DATABASE` are not set.
  * System Property: `org.wildfly.datasources.postgresql.connection-url`

* `POSTGRESQL_USER` (or `OPENSHIFT_POSTGRESQL_DB_USERNAME`)

  * Description: Defines the username for the datasource. 
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.postgresql.user-name`

Optional configuration
==============

* `POSTGRESQL_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.postgresql.background-validation`

* `POSTGRESQL_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.postgresql.background-validation-millis`

* `POSTGRESQL_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker`
  * System Property: `org.wildfly.datasources.postgresql.valid-connection-checker-class-name`

* `POSTGRESQL_DATASOURCE` or `OPENSHIFT_POSTGRESQL_DATASOURCE`

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `PostgreSQLDS`
  * System Property: `org.wildfly.datasources.postgresql.datasource`

* `POSTGRESQL_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.postgresql.enabled`

* `POSTGRESQL_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter`
  * System Property: `org.wildfly.datasources.postgresql.exception-sorter-class-name`

* `POSTGRESQL_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.postgresql.flush-strategy`

* `POSTGRESQL_HOST` (or `POSTGRESQL_SERVICE_HOST` or `OPENSHIFT_POSTGRESQL_DB_HOST`)

  * Description: Defines the database server’s host name or IP address to be used in the datasource’s `connection-url` property.
  * Default Value: `localhost`
  * Required: True if `POSTGRESQL_URL` is not set.
  * System Property: `org.wildfly.datasources.postgresql.host`

* `POSTGRESQL_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.postgresql.idle-timeout-minutes`

* `POSTGRESQL_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value: `java:jboss/datasources/${POSTGRESQL_DATASOURCE}`
  * System Property: `org.wildfly.datasources.postgresql.jndi-name`

* `POSTGRESQL_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.postgresql.jta`

* `POSTGRESQL_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.postgresql.max-pool-size`

* `POSTGRESQL_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.postgresql.min-pool-size`

* `POSTGRESQL_PORT` (or `POSTGRESQL_SERVICE_PORT` or `OPENSHIFT_POSTGRESQL_DB_PORT`)

  * Description: Defines the database server’s port to be used in the datasource’s `connection-url` property. 
  * Default Value: `5432`
  * Required: True if `POSTGRESQL_URL` is not set.
  * System Property: `org.wildfly.datasources.postgresql.port`

* `POSTGRESQL_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.postgres.StaleConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.postgresql.stale-connection-checker-class-name`

* `POSTGRESQL_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.postgresql.transaction-isolation`

* `POSTGRESQL_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.postgresql.validate-on-match`

* `POSTGRESQL_BLOCKING_TIMEOUT_WAIT_MILLIS`

  * Description: Specifies the maximum time, in milliseconds, that a connection factory will block waiting for a connection to be available.
  * Default Value: `1000`
  * System Property: `org.wildfly.datasources.postgresql.blocking-timeout-wait-millis`

* `POSTGRESQL_ALLOCATION_RETRY`

  * Description: Specifies the number of times to retry allocation of a connection before failing.
  * Default Value: `5`
  * System Property: `org.wildfly.datasources.postgresql.allocation-retry`

* `POSTGRESQL_ALLOCATION_RETRY_WAIT_MILLIS`

  * Description: Specifies the time, in milliseconds, to wait between allocation retries.
  * Default Value: `200`
  * System Property: `org.wildfly.datasources.postgresql.allocation-retry-wait-millis`

* `POSTGRESQL_XA_RESOURCE_TIMEOUT`

  * Description: Specifies the timeout, in seconds, for the XA resource.
  * Default Value: `60`
  * System Property: `org.wildfly.datasources.postgresql.xa-resource-timeout`

* `POSTGRESQL_XA_DATASOURCE_CLASS`

    * Description: Specifies the XA datasource class name.
    * Default Value: `org.postgresql.xa.PGXADataSource`
    * System Property: `org.wildfly.datasources.postgresql.xa-datasource-class`