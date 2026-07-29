Galleon Layers
=========

* `mssqlserver-default-datasource`: Provision the `MSSQLServerDS` non xa datasource and configure it as the default one. Depends on `mssqlserver-datasource` layer.
* `mssqlserver-datasource`: Provision the `MSSQLServerDS` non xa datasource. Depends on `mssqlserver-driver` layer.
* `mssqlserver-driver`: Provision the `mssqlserver` driver. This layer installs the JBoss Modules module `com.microsoft.sqlserver`.

Build time configuration
========================

The following variable can be set at Galleon provisioning time to override the
default driver version that is embedded in the feature pack.

Optional configuration
-----------------------

* `MSSQLSERVER_DRIVER_VERSION`

  * Description: The version of the `com.microsoft.sqlserver:mssql-jdbc` Maven artifact.
    When set, this version is used instead of the default version bundled with the
    feature pack.
  * Default value: current version bundled with the feature pack
  * Required: False
  * System Property: `org.wildfly.datasources.mssqlserver.driver.version`

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Required configuration
==============

* `MSSQLSERVER_DATABASE`

  * Description: Defines the database name to be used in the datasource’s `connection-url` property.
  * No default value.
  * Required: True if `MSSQLSERVER_URL` is not set.
  * System Property: `org.wildfly.datasources.mssqlserver.database`

* `MSSQLSERVER_PASSWORD`

  * Description: Defines the password for the datasource.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.mssqlserver.password`

* `MSSQLSERVER_URL`

  * Description: Defines the connection URL for the datasource. 
  * Default Value: `jdbc:sqlserver://${MSSQLSERVER_HOST}:${MSSQLSERVER_PORT};DatabaseName=${MSSQLSERVER_DATABASE}`
  * Required: True if `MSSQLSERVER_PORT/HOST/DATABASE` are not set.
  * System Property: `org.wildfly.datasources.mssqlserver.connection-url`

* `MSSQLSERVER_USER`

  * Description: Defines the username for the datasource. 
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.mssqlserver.user-name`

Optional configuration
==============

* `MSSQLSERVER_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.mssqlserver.background-validation`

* `MSSQLSERVER_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.mssqlserver.background-validation-millis`

* `MSSQLSERVER_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.mssql.MSSQLValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.mssql.MSSQLValidConnectionChecker`
  * System Property: `org.wildfly.datasources.mssqlserver.valid-connection-checker-class-name`

* `MSSQLSERVER_DATASOURCE`

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `MSSQLServerDS`
  * System Property: `org.wildfly.datasources.mssqlserver.datasource`

* `MSSQLSERVER_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mssqlserver.enabled`

* `MSSQLSERVER_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.mssql.MSSQLExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.mssql.MSSQLExceptionSorter`
  * System Property: `org.wildfly.datasources.mssqlserver.exception-sorter-class-name`

* `MSSQLSERVER_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.mssqlserver.flush-strategy`

* `MSSQLSERVER_HOST` (or `MSSQLSERVER_SERVICE_HOST`)

  * Description: Defines the database server’s host name or IP address to be used in the datasource’s `connection-url` property.
  * Default Value: `localhost`
  * Required: True if `MSSQLSERVER_URL` is not set.
  * System Property: `org.wildfly.datasources.mssqlserver.host`

* `MSSQLSERVER_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.mssqlserver.idle-timeout-minutes`

* `MSSQLSERVER_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value: `java:jboss/datasources/${MSSQLSERVER_DATASOURCE}`
  * System Property: `org.wildfly.datasources.mssqlserver.jndi-name`

* `MSSQLSERVER_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mssqlserver.jta`

* `MSSQLSERVER_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.mssqlserver.max-pool-size`

* `MSSQLSERVER_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.mssqlserver.min-pool-size`

* `MSSQLSERVER_PORT` (or `MSSQLSERVER_SERVICE_PORT`)

  * Description: Defines the database server’s port to be used in the datasource’s `connection-url` property. 
  * Default Value: `1433`
  * Required: True if `MSSQLSERVER_URL` is not set.
  * System Property: `org.wildfly.datasources.mssqlserver.port`

* `MSSQLSERVER_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid value: No checker provided by JBoss, only custom stale checker can be provided.
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.mssqlserver.stale-connection-checker-class-name`

* `MSSQLSERVER_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.mssqlserver.transaction-isolation`

* `MSSQLSERVER_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.mssqlserver.validate-on-match`

