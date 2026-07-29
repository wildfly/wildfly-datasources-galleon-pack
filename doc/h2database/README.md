Galleon Layers
=========

* `h2database-default-datasource`: Provision the `H2DatabaseDS` non xa datasource and configure it as the default one. Depends on `h2database-datasource` layer.
* `h2database-datasource`: Provision the `H2DatabaseDS` non xa datasource. Depends on `h2database-driver` layer.
* `h2database-driver`: Provision the `h2database` driver. This layer installs the JBoss Modules module `com.h2database.h2database`.

Build time configuration
========================

The following variable can be set at Galleon provisioning time to override the
default driver version that is embedded in the feature pack.

Optional configuration
-----------------------

* `H2DATABASE_DRIVER_VERSION`

  * Description: The version of the `com.h2database:h2` Maven artifact.
    When set, this version is used instead of the default version bundled with the
    feature pack.
  * Default value: current version bundled with the feature pack
  * Required: False
  * System Property: `org.wildfly.datasources.h2database.driver.version`

Configuration
========

The following set of environment variables and corresponding Java system properties can be used to configure the datasource.

Optional configuration
==============

* `H2DATABASE_BACKGROUND_VALIDATION`

  * Description: When set to true database connections are validated periodically in a background thread prior to use. Defaults to false, meaning the `validate-on-match` method is enabled by default instead.  
  * Default Value: `false`
  * System Property: `org.wildfly.datasources.h2database.background-validation`

* `H2DATABASE_BACKGROUND_VALIDATION_MILLIS`

  * Description: Specifies frequency of the validation, in milliseconds, when the `background-validation` database connection validation mechanism is enabled.    
  * Default Value: `10000`
  * System Property: `org.wildfly.datasources.h2database.background-validation-millis`

* `H2DATABASE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to validate connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullValidConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullValidConnectionChecker`
  * System Property: `org.wildfly.datasources.h2database.valid-connection-checker-class-name`

* `H2DATABASE_DATASOURCE` or `OPENSHIFT_H2DATABASE_DATASOURCE`

  * Description: Datasource name used in the `jndi-name`.
  * Default Value: `H2DatabaseDS`
  * System Property: `org.wildfly.datasources.h2database.datasource`

* `H2DATABASE_ENABLED`

  * Description: Set to false to disable the datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.h2database.enabled`

* `H2DATABASE_EXCEPTION_SORTER`

  * Description: Specifies the exception sorter class that is used to properly detect and clean up after fatal database connection exceptions. Valid value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullExceptionSorter`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullExceptionSorter`
  * System Property: `org.wildfly.datasources.h2database.exception-sorter-class-name`

* `H2DATABASE_FLUSH_STRATEGY`

  * Description: Specifies how the datasource should be flushed in case of an error.    
  * Default Value: `FailingConnectionOnly`
  * System Property: `org.wildfly.datasources.h2database.flush-strategy`

* `H2DATABASE_IDLE_TIMEOUT_MINUTES`

  * Description: Specifies the maximum time, in minutes, a connection may be idle before being closed.
  * Default Value:`30`
  * System Property: `org.wildfly.datasources.h2database.idle-timeout-minutes`

* `H2DATABASE_JNDI`

  * Description: Defines the JNDI name for the datasource.
  * Default Value:` java:jboss/datasources/${H2DATABASE_DATASOURCE}`
  * System Property: `org.wildfly.datasources.h2database.jndi-name`

* `H2DATABASE_JTA`

  * Description: Defines Java Transaction API (JTA) option for the non-XA datasource.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.h2database.jta`

* `H2DATABASE_MAX_POOL_SIZE`

  * Description: Defines the maximum pool size option for the datasource.
  * Default Value: `20`
  * System Property: `org.wildfly.datasources.h2database.max-pool-size`

* `H2DATABASE_MIN_POOL_SIZE`

  * Description: Defines the minimum pool size option for the datasource.
  * Default Value: `0`
  * System Property: `org.wildfly.datasources.h2database.min-pool-size`

* `H2DATABASE_PASSWORD` (or `OPENSHIFT_H2DATABASE_DB_PASSWORD`)

  * Description: Defines the password for the datasource.
  * Default Value: `sa`
  * Required: True
  * System Property: `org.wildfly.datasources.h2database.password`

* `H2DATABASE_STALE_CONNECTION_CHECKER`

  * Description: Specifies a connection checker class that is used to check stale connections. Valid value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * Default Value: `org.jboss.jca.adapters.jdbc.extensions.novendor.NullStaleConnectionChecker`
  * System Property: `org.wildfly.datasources.h2database.stale-connection-checker-class-name`

* `H2DATABASE_TX_ISOLATION`

  * Description: Defines the `java.sql.Connection` transaction isolation level for the datasource.    
  * Default Value: `TRANSACTION_READ_COMMITTED`
  * System Property: `org.wildfly.datasources.h2database.transaction-isolation`

* `H2DATABASE_URL`

  * Description: Defines the connection URL for the datasource. 
  * Default Value: `jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
  * System Property: `org.wildfly.datasources.h2database.connection-url`

* `H2DATABASE_USER` (or `OPENSHIFT_H2DATABASE_DB_USERNAME`)

  * Description: Defines the username for the datasource. 
  * Default Value: `sa`
  * System Property: `org.wildfly.datasources.h2database.user-name`

* `H2DATABASE_VALIDATE_ON_MATCH`

  * Description: Indicates whether or not connection level validation should be done when a connection factory attempts to match a managed connection for a given set. This is typically exclusive to the use of background validation.
  * Default Value: `true`
  * System Property: `org.wildfly.datasources.h2database.validate-on-match`

