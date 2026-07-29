Galleon Layers
=========

* `db2-default-datasource`: Provision the `DB2DS` non xa datasource and configures it as the default one. Depends on `db2-datasource` layer.
* `db2-datasource`: Provision the `DB2DS` non xa datasource. Depends on `db2-driver` layer.
* `db2-driver`: Provision the `db2` driver. This layer installs the JBoss Modules module `com.ibm.db2`.

Build time configuration
========================

The following variable can be set at Galleon provisioning time to override the
default driver version that is embedded in the feature pack.

Optional configuration
-----------------------

* `DB2_DRIVER_VERSION`

  * Description: The version of the `com.ibm.db2:jcc` Maven artifact.
    When set, this version is used instead of the default version bundled with the
    feature pack.
  * Default value: current version bundled with the feature pack
  * Required: False
  * System Property: `org.wildfly.datasources.db2.driver.version`

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Required configuration
==============

* `DB2_DATABASE` (or `OPENSHIFT_DB2_DB_NAME`)

  * Description: Defines the database name to be used in the datasource’s `connection-url` property.
  * No default value.
  * Required: True if `DB2_URL` is not set.
  * System Property: `org.wildfly.datasources.db2.database`

* `DB2_PASSWORD` (or `OPENSHIFT_DB2_DB_PASSWORD`)

  * Description: Defines the password for the datasource.
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.db2.password`

* `DB2_URL`

  * Description: Defines the connection URL for the datasource. 
  * Default Value: `jdbc:db2://${DB2_HOST}:${DB2_PORT}/${DB2_DATABASE}`
  * Required: True if `DB2_PORT/HOST/DATABASE` are not set.
  * System Property: `org.wildfly.datasources.db2.connection-url`

* `DB2_USER` (or `OPENSHIFT_DB2_DB_USERNAME`)

  * Description: Defines the username for the datasource. 
  * No default value.
  * Required: True
  * System Property: `org.wildfly.datasources.db2.user-name`

Optional configuration
==============

* `DB2_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.db2.background-validation`

* `DB2_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.db2.background-validation-millis`

* `DB2_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.db2.DB2ValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.db2.DB2ValidConnectionChecker`
  * System Property: `org.wildfly.datasources.db2.valid-connection-checker-class-name`

* `DB2_DATASOURCE` or `OPENSHIFT_DB2_DATASOURCE`

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `DB2DS`
  * System Property: `org.wildfly.datasources.db2.datasource`

* `DB2_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.db2.enabled`

* `DB2_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.db2.DB2ExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.db2.DB2ExceptionSorter`
  * System Property: `org.wildfly.datasources.db2.exception-sorter-class-name`

* `DB2_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.db2.flush-strategy`

* `DB2_HOST` (or `DB2_SERVICE_HOST` or `OPENSHIFT_DB2_DB_HOST`)

  * Description: Defines the database server’s host name or IP address to be used in the datasource’s `connection-url` property.
  * Default Value: `localhost`
  * Required: True if `DB2_URL` is not set.
  * System Property: `org.wildfly.datasources.db2.host`

* `DB2_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.db2.idle-timeout-minutes`

* `DB2_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value: `java:jboss/datasources/${DB2_DATASOURCE}`
  * System Property: `org.wildfly.datasources.db2.jndi-name`

* `DB2_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.db2.jta`

* `DB2_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.db2.max-pool-size`

* `DB2_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.db2.min-pool-size`

* `DB2_PORT` (or `DB2_SERVICE_PORT` or `OPENSHIFT_DB2_DB_PORT`)

  * Description: Defines the database server’s port to be used in the datasource’s `connection-url` property. 
  * Default Value: `50000`
  * Required: True if `DB2_URL` is not set.
  * System Property: `org.wildfly.datasources.db2.port`

* `DB2_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.db2.DB2StaleConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.db2.stale-connection-checker-class-name`

* `DB2_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.db2.transaction-isolation`

* `DB2_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.db2.validate-on-match`

