+++
title = "Spring Boot Start 配置"
weight = 4
+++

## 5.0.0-alpha

### 数据分片

#### 配置项说明

```properties
spring.shardingsphere.datasource.names= # 省略数据源配置

# 标准分片表配置
spring.shardingsphere.rules.sharding.tables.<table-name>.actual-data-nodes= # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持 inline 表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点，用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况

# 分库策略，缺省表示使用默认分库策略，以下的分片策略只能选其一

# 用于单分片键的标准分片场景
spring.shardingsphere.rules.sharding.tables.<table-name>.database-strategy.standard.<sharding-algorithm-name>.sharding-column= # 分片列名称
spring.shardingsphere.rules.sharding.tables.<table-name>.database-strategy.standard.<sharding-algorithm-name>.sharding-algorithm-name= # 分片算法名称

# 用于多分片键的复合分片场景
spring.shardingsphere.rules.sharding.tables.<table-name>.database-strategy.complex.<sharding-algorithm-name>.sharding-columns= # 分片列名称，多个列以逗号分隔
spring.shardingsphere.rules.sharding.tables.<table-name>.database-strategy.complex.<sharding-algorithm-name>.sharding-algorithm-name= # 分片算法名称

# 用于Hint 的分片策略
spring.shardingsphere.rules.sharding.tables.<table-name>.database-strategy.hint.<sharding-algorithm-name>.sharding-algorithm-name= # 分片算法名称

# 分表策略，同分库策略
spring.shardingsphere.rules.sharding.tables.<table-name>.table-strategy.xxx= # 省略

# 自动分片表配置
spring.shardingsphere.rules.sharding.auto-tables.<auto-table-name>.actual-data-sources= # 数据源名

spring.shardingsphere.rules.sharding.auto-tables.<auto-table-name>.sharding-strategy.standard.sharding-column= # 分片列名称
spring.shardingsphere.rules.sharding.auto-tables.<auto-table-name>.sharding-strategy.standard.sharding-algorithm= # 自动分片算法名称

# 分布式序列策略配置
spring.shardingsphere.rules.sharding.tables.<table-name>.key-generate-strategy.column= # 分布式序列列名称
spring.shardingsphere.rules.sharding.tables.<table-name>.key-generate-strategy.key-generator-name= # 分布式序列算法名称

spring.shardingsphere.rules.sharding.binding-tables[0]= # 绑定表规则列表
spring.shardingsphere.rules.sharding.binding-tables[1]= # 绑定表规则列表
spring.shardingsphere.rules.sharding.binding-tables[x]= # 绑定表规则列表

spring.shardingsphere.rules.sharding.broadcast-tables[0]= # 广播表规则列表
spring.shardingsphere.rules.sharding.broadcast-tables[1]= # 广播表规则列表
spring.shardingsphere.rules.sharding.broadcast-tables[x]= # 广播表规则列表

spring.shardingsphere.sharding.default-database-strategy.xxx= # 默认数据库分片策略
spring.shardingsphere.sharding.default-table-strategy.xxx= # 默认表分片策略
spring.shardingsphere.sharding.default-key-generate-strategy.xxx= # 默认分布式序列策略

# 分片算法配置
spring.shardingsphere.rules.sharding.sharding-algorithms.<sharding-algorithm-name>.type= # 分片算法类型
spring.shardingsphere.rules.sharding.sharding-algorithms.<sharding-algorithm-name>.props.xxx=# 分片算法属性配置

# 分布式序列算法配置
spring.shardingsphere.rules.sharding.key-generators.<key-generate-algorithm-name>.type= # 分布式序列算法类型
spring.shardingsphere.rules.sharding.key-generators.<key-generate-algorithm-name>.props.xxx= # 分布式序列算法属性配置
```

### 读写分离

#### 配置项说明

```properties
spring.shardingsphere.datasource.names= # 省略数据源配置

spring.shardingsphere.rules.replica-query.data-sources.<replica-query-data-source-name>.primary-data-source-name= # 主数据源名称
spring.shardingsphere.rules.replica-query.data-sources.<replica-query-data-source-name>.replica-data-source-names= # 从数据源名称，多个从数据源用逗号分隔
spring.shardingsphere.rules.replica-query.data-sources.<replica-query-data-source-name>.load-balancer-name= # 负载均衡算法名称

# 负载均衡算法配置
spring.shardingsphere.rules.replica-query.load-balancers.<load-balance-algorithm-name>.type= # 负载均衡算法类型
spring.shardingsphere.rules.replica-query.load-balancers.<load-balance-algorithm-name>.props.xxx= # 负载均衡算法属性配置
```

### 数据加密

#### 配置项说明

```properties
spring.shardingsphere.datasource.names= # 省略数据源配置

spring.shardingsphere.rules.encrypt.tables.<table-name>.columns.<column-name>.cipher-column= # 加密列名称
spring.shardingsphere.rules.encrypt.tables.<table-name>.columns.<column-name>.assisted-query-column= # 查询列名称
spring.shardingsphere.rules.encrypt.tables.<table-name>.columns.<column-name>.plain-column= # 原文列名称
spring.shardingsphere.rules.encrypt.tables.<table-name>.columns.<column-name>.encryptor-name= # 加密算法名称

# 加密算法配置
spring.shardingsphere.rules.encrypt.encryptors.<encrypt-algorithm-name>.type= # 加密算法类型
spring.shardingsphere.rules.encrypt.encryptors.<encrypt-algorithm-name>.props.xxx= # 加密算法属性配置
```

### 影子库

#### 配置项说明

```properties
spring.shardingsphere.datasource.names= # 省略数据源配置

spring.shardingsphere.rules.shadow.column= # 影子字段名称
spring.shardingsphere.rules.shadow.shadow-mappings.<product-data-source-name>= # 影子数据库名称
```

### 分布式治理

#### 配置项说明

```properties
spring.shardingsphere.governance.name= # 治理名称
spring.shardingsphere.governance.registry-center.type= # 治理持久化类型。如：Zookeeper, etcd, Apollo, Nacos
spring.shardingsphere.governance.registry-center.server-lists= # 治理服务列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
spring.shardingsphere.governance.registry-center.props= # 其它配置
spring.shardingsphere.governance.additional-config-center.type= # 可选的配置中心类型。如：Zookeeper, etcd, Apollo, Nacos
spring.shardingsphere.governance.additional-config-center.server-lists= # 可选的配置中心服务列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
spring.shardingsphere.governance.additional-config-center.props= # 可选的配置中心其它配置
spring.shardingsphere.governance.overwrite= # 本地配置是否覆盖配置中心配置。如果可覆盖，每次启动都以本地配置为准.
```

## ShardingSphere-4.x

### 数据分片

#### 配置项说明

```properties
spring.shardingsphere.datasource.names= # 数据源名称，多数据源以逗号分隔

spring.shardingsphere.datasource.<data-source-name>.type= # 数据库连接池类名称
spring.shardingsphere.datasource.<data-source-name>.driver-class-name= # 数据库驱动类名
spring.shardingsphere.datasource.<data-source-name>.url= # 数据库 url 连接
spring.shardingsphere.datasource.<data-source-name>.username= # 数据库用户名
spring.shardingsphere.datasource.<data-source-name>.password= # 数据库密码
spring.shardingsphere.datasource.<data-source-name>.xxx= # 数据库连接池的其它属性

spring.shardingsphere.sharding.tables.<logic-table-name>.actual-data-nodes= # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持 inline 表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点，用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况

# 分库策略，缺省表示使用默认分库策略，以下的分片策略只能选其一

# 用于单分片键的标准分片场景
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.standard.sharding-column= # 分片列名称
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.standard.precise-algorithm-class-name= # 精确分片算法类名称，用于 = 和 IN。该类需实现 PreciseShardingAlgorithm 接口并提供无参数的构造器
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.standard.range-algorithm-class-name= # 范围分片算法类名称，用于 BETWEEN，可选。该类需实现 RangeShardingAlgorithm 接口并提供无参数的构造器

# 用于多分片键的复合分片场景
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.complex.sharding-columns= # 分片列名称，多个列以逗号分隔
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.complex.algorithm-class-name= # 复合分片算法类名称。该类需实现 ComplexKeysShardingAlgorithm 接口并提供无参数的构造器

# 行表达式分片策略
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.inline.sharding-column= # 分片列名称
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.inline.algorithm-expression= # 分片算法行表达式，需符合 groovy 语法

# Hint 分片策略
spring.shardingsphere.sharding.tables.<logic-table-name>.database-strategy.hint.algorithm-class-name= # Hint 分片算法类名称。该类需实现 HintShardingAlgorithm 接口并提供无参数的构造器

# 分表策略，同分库策略
spring.shardingsphere.sharding.tables.<logic-table-name>.table-strategy.xxx= # 省略

spring.shardingsphere.sharding.tables.<logic-table-name>.key-generator.column= # 自增列名称，缺省表示不使用自增主键生成器
spring.shardingsphere.sharding.tables.<logic-table-name>.key-generator.type= # 自增列值生成器类型，缺省表示使用默认自增列值生成器。可使用用户自定义的列值生成器或选择内置类型：SNOWFLAKE/UUID
spring.shardingsphere.sharding.tables.<logic-table-name>.key-generator.props.<property-name>= # 属性配置, 注意：使用 SNOWFLAKE 算法，需要配置 worker.id 与 max.tolerate.time.difference.milliseconds 属性。若使用此算法生成值作分片值，建议配置 max.vibration.offset 属性

spring.shardingsphere.sharding.binding-tables[0]= # 绑定表规则列表
spring.shardingsphere.sharding.binding-tables[1]= # 绑定表规则列表
spring.shardingsphere.sharding.binding-tables[x]= # 绑定表规则列表

spring.shardingsphere.sharding.broadcast-tables[0]= # 广播表规则列表
spring.shardingsphere.sharding.broadcast-tables[1]= # 广播表规则列表
spring.shardingsphere.sharding.broadcast-tables[x]= # 广播表规则列表

spring.shardingsphere.sharding.default-data-source-name= # 未配置分片规则的表将通过默认数据源定位
spring.shardingsphere.sharding.default-database-strategy.xxx= # 默认数据库分片策略，同分库策略
spring.shardingsphere.sharding.default-table-strategy.xxx= # 默认表分片策略，同分表策略
spring.shardingsphere.sharding.default-key-generator.type= # 默认自增列值生成器类型，缺省将使用 org.apache.shardingsphere.core.keygen.generator.impl.SnowflakeKeyGenerator。可使用用户自定义的列值生成器或选择内置类型：SNOWFLAKE/UUID
spring.shardingsphere.sharding.default-key-generator.props.<property-name>= # 自增列值生成器属性配置, 比如 SNOWFLAKE 算法的 worker.id 与 max.tolerate.time.difference.milliseconds

spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.master-data-source-name= # 详见读写分离部分
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[0]= # 详见读写分离部分
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[1]= # 详见读写分离部分
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[x]= # 详见读写分离部分
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-class-name= # 详见读写分离部分
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-type= # 详见读写分离部分

spring.shardingsphere.props.sql.show= # 是否开启 SQL 显示，默认值: false
spring.shardingsphere.props.executor.size= # 工作线程数量，默认值: CPU 核数
```

### 读写分离

#### 配置项说明

```properties
# 省略数据源配置，与数据分片一致

spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.master-data-source-name= # 主库数据源名称
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[0]= # 从库数据源名称列表
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[1]= # 从库数据源名称列表
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[x]= # 从库数据源名称列表
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-class-name= # 从库负载均衡算法类名称。该类需实现 MasterSlaveLoadBalanceAlgorithm 接口且提供无参数构造器
spring.shardingsphere.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-type= # 从库负载均衡算法类型，可选值：ROUND_ROBIN，RANDOM。若 `load-balance-algorithm-class-name` 存在则忽略该配置

spring.shardingsphere.props.sql.show= # 是否开启 SQL 显示，默认值: false
spring.shardingsphere.props.executor.size= # 工作线程数量，默认值: CPU 核数
spring.shardingsphere.props.check.table.metadata.enabled= # 是否在启动时检查分表元数据一致性，默认值: false
```

### 数据脱敏

#### 配置项说明

```properties
# 省略数据源配置，与数据分片一致

spring.shardingsphere.encrypt.encryptors.<encryptor-name>.type= # 加解密器类型，可自定义或选择内置类型：MD5/AES
spring.shardingsphere.encrypt.encryptors.<encryptor-name>.props.<property-name>= # 属性配置, 注意：使用 AES 加密器，需要配置 AES 加密器的 KEY 属性：aes.key.value
spring.shardingsphere.encrypt.tables.<table-name>.columns.<logic-column-name>.plainColumn= # 存储明文的字段
spring.shardingsphere.encrypt.tables.<table-name>.columns.<logic-column-name>.cipherColumn= # 存储密文的字段
spring.shardingsphere.encrypt.tables.<table-name>.columns.<logic-column-name>.assistedQueryColumn= # 辅助查询字段，针对 ShardingQueryAssistedEncryptor 类型的加解密器进行辅助查询
spring.shardingsphere.encrypt.tables.<table-name>.columns.<logic-column-name>.encryptor= # 加密器名字
```

### 治理

#### 配置项说明

```properties
# 省略数据源、数据分片、读写分离和数据脱敏配置

spring.shardingsphere.orchestration.name= # 治理实例名称
spring.shardingsphere.orchestration.overwrite= # 本地配置是否覆盖注册中心配置。如果可覆盖，每次启动都以本地配置为准
spring.shardingsphere.orchestration.registry.type= # 配置中心类型。如：zookeeper
spring.shardingsphere.orchestration.registry.server-lists= # 连接注册中心服务器的列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
spring.shardingsphere.orchestration.registry.namespace= # 注册中心的命名空间
spring.shardingsphere.orchestration.registry.digest= # 连接注册中心的权限令牌。缺省为不需要权限验证
spring.shardingsphere.orchestration.registry.operation-timeout-milliseconds= # 操作超时的毫秒数，默认 500 毫秒
spring.shardingsphere.orchestration.registry.max-retries= # 连接失败后的最大重试次数，默认 3 次
spring.shardingsphere.orchestration.registry.retry-interval-milliseconds= # 重试间隔毫秒数，默认 500 毫秒
spring.shardingsphere.orchestration.registry.time-to-live-seconds= # 临时节点存活秒数，默认 60 秒
spring.shardingsphere.orchestration.registry.props= # 配置中心其它属性
```

## ShardingSphere-3.x

### 数据分片

#### 配置项说明

```properties
sharding.jdbc.datasource.names= # 数据源名称，多数据源以逗号分隔

sharding.jdbc.datasource.<data-source-name>.type= # 数据库连接池类名称
sharding.jdbc.datasource.<data-source-name>.driver-class-name= # 数据库驱动类名
sharding.jdbc.datasource.<data-source-name>.url= # 数据库 url 连接
sharding.jdbc.datasource.<data-source-name>.username= # 数据库用户名
sharding.jdbc.datasource.<data-source-name>.password= # 数据库密码
sharding.jdbc.datasource.<data-source-name>.xxx= # 数据库连接池的其它属性

sharding.jdbc.config.sharding.tables.<logic-table-name>.actual-data-nodes= # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持 inline 表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点。用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况

# 分库策略，缺省表示使用默认分库策略，以下的分片策略只能选其一

# 用于单分片键的标准分片场景
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.standard.sharding-column= # 分片列名称
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.standard.precise-algorithm-class-name= # 精确分片算法类名称，用于 = 和 IN。该类需实现 PreciseShardingAlgorithm 接口并提供无参数的构造器
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.standard.range-algorithm-class-name= # 范围分片算法类名称，用于 BETWEEN，可选。该类需实现 RangeShardingAlgorithm 接口并提供无参数的构造器

# 用于多分片键的复合分片场景
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.complex.sharding-columns= # 分片列名称，多个列以逗号分隔
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.complex.algorithm-class-name= # 复合分片算法类名称。该类需实现 ComplexKeysShardingAlgorithm 接口并提供无参数的构造器

# 行表达式分片策略
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.inline.sharding-column= # 分片列名称
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.inline.algorithm-expression= # 分片算法行表达式，需符合 groovy 语法

# Hint 分片策略
sharding.jdbc.config.sharding.tables.<logic-table-name>.database-strategy.hint.algorithm-class-name= # Hint 分片算法类名称。该类需实现 HintShardingAlgorithm 接口并提供无参数的构造器

# 分表策略，同分库策略
sharding.jdbc.config.sharding.tables.<logic-table-name>.table-strategy.xxx= # 省略

sharding.jdbc.config.sharding.tables.<logic-table-name>.key-generator-column-name= # 自增列名称，缺省表示不使用自增主键生成器
sharding.jdbc.config.sharding.tables.<logic-table-name>.key-generator-class-name= # 自增列值生成器类名称，缺省表示使用默认自增列值生成器。该类需提供无参数的构造器

sharding.jdbc.config.sharding.tables.<logic-table-name>.logic-index= # 逻辑索引名称，对于分表的 Oracle/PostgreSQL 数据库中 DROP INDEX XXX 语句，需要通过配置逻辑索引名称定位所执行 SQL 的真实分表

sharding.jdbc.config.sharding.binding-tables[0]= # 绑定表规则列表
sharding.jdbc.config.sharding.binding-tables[1]= # 绑定表规则列表
sharding.jdbc.config.sharding.binding-tables[x]= # 绑定表规则列表

sharding.jdbc.config.sharding.broadcast-tables[0]= # 广播表规则列表
sharding.jdbc.config.sharding.broadcast-tables[1]= # 广播表规则列表
sharding.jdbc.config.sharding.broadcast-tables[x]= # 广播表规则列表

sharding.jdbc.config.sharding.default-data-source-name= # 未配置分片规则的表将通过默认数据源定位
sharding.jdbc.config.sharding.default-database-strategy.xxx= # 默认数据库分片策略，同分库策略
sharding.jdbc.config.sharding.default-table-strategy.xxx= # 默认表分片策略，同分表策略
sharding.jdbc.config.sharding.default-key-generator-class-name= # 默认自增列值生成器类名称，缺省使用 io.shardingsphere.core.keygen.DefaultKeyGenerator。该类需实现 KeyGenerator 接口并提供无参数的构造器

sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.master-data-source-name= # 详见读写分离部分
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[0]= # 详见读写分离部分
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[1]= # 详见读写分离部分
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[x]= # 详见读写分离部分
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-class-name= # 详见读写分离部分
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-type= # 详见读写分离部分
sharding.jdbc.config.config.map.key1= # 详见读写分离部分
sharding.jdbc.config.config.map.key2= # 详见读写分离部分
sharding.jdbc.config.config.map.keyx= # 详见读写分离部分

sharding.jdbc.config.props.sql.show= # 是否开启 SQL 显示，默认值: false
sharding.jdbc.config.props.executor.size= # 工作线程数量，默认值: CPU 核数

sharding.jdbc.config.config.map.key1= # 用户自定义配置
sharding.jdbc.config.config.map.key2= # 用户自定义配置
sharding.jdbc.config.config.map.keyx= # 用户自定义配置
```

### 读写分离

#### 配置项说明

```properties
# 省略数据源配置，与数据分片一致

sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.master-data-source-name= # 主库数据源名称
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[0]= # 从库数据源名称列表
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[1]= # 从库数据源名称列表
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.slave-data-source-names[x]= # 从库数据源名称列表
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-class-name= # 从库负载均衡算法类名称。该类需实现 MasterSlaveLoadBalanceAlgorithm 接口且提供无参数构造器
sharding.jdbc.config.sharding.master-slave-rules.<master-slave-data-source-name>.load-balance-algorithm-type= # 从库负载均衡算法类型，可选值：ROUND_ROBIN，RANDOM。若 `load-balance-algorithm-class-name` 存在则忽略该配置

sharding.jdbc.config.config.map.key1= # 用户自定义配置
sharding.jdbc.config.config.map.key2= # 用户自定义配置
sharding.jdbc.config.config.map.keyx= # 用户自定义配置

sharding.jdbc.config.props.sql.show= # 是否开启 SQL 显示，默认值: false
sharding.jdbc.config.props.executor.size= # 工作线程数量，默认值: CPU 核数
sharding.jdbc.config.props.check.table.metadata.enabled= # 是否在启动时检查分表元数据一致性，默认值: false
```

### 治理

#### 配置项说明

```properties
# 省略数据源、数据分片和读写分离配置

sharding.jdbc.config.sharding.orchestration.name= # 数据治理实例名称
sharding.jdbc.config.sharding.orchestration.overwrite= # 本地配置是否覆盖注册中心配置。如果可覆盖，每次启动都以本地配置为准
sharding.jdbc.config.sharding.orchestration.registry.server-lists= # 连接注册中心服务器的列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
sharding.jdbc.config.sharding.orchestration.registry.namespace= # 注册中心的命名空间
sharding.jdbc.config.sharding.orchestration.registry.digest= # 连接注册中心的权限令牌。缺省为不需要权限验证
sharding.jdbc.config.sharding.orchestration.registry.operation-timeout-milliseconds= # 操作超时的毫秒数，默认 500 毫秒
sharding.jdbc.config.sharding.orchestration.registry.max-retries= # 连接失败后的最大重试次数，默认 3 次
sharding.jdbc.config.sharding.orchestration.registry.retry-interval-milliseconds= # 重试间隔毫秒数，默认 500 毫秒
sharding.jdbc.config.sharding.orchestration.registry.time-to-live-seconds= # 临时节点存活秒数，默认 60 秒
```

## ShardingSphere-2.x

### 分库分表

#### 配置项说明

```properties
# 忽略数据源配置

sharding.jdbc.config.sharding.default-data-source-name= # 未配置分片规则的表将通过默认数据源定位
sharding.jdbc.config.sharding.default-database-strategy.inline.sharding-column= # 分片列名称
sharding.jdbc.config.sharding.default-database-strategy.inline.algorithm-expression= # 分片算法行表达式，需符合 groovy 语法
sharding.jdbc.config.sharding.tables.t_order.actualDataNodes= # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持 inline 表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点，用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况
sharding.jdbc.config.sharding.tables.t_order.tableStrategy.inline.shardingColumn= # 分片列名称
sharding.jdbc.config.sharding.tables.t_order.tableStrategy.inline.algorithmInlineExpression= # 分片算法行表达式，需符合 groovy 语法
sharding.jdbc.config.sharding.tables.t_order.keyGeneratorColumnName= # 自增列名称，缺省表示不使用自增主键生成器

sharding.jdbc.config.sharding.tables.<logic-table-name>.key-generator-column-name= # 自增列名称，缺省表示不使用自增主键生成器
sharding.jdbc.config.sharding.tables.<logic-table-name>.key-generator-class-name= # 默认自增列值生成器类型
```

### 读写分离

#### 配置项说明

```properties
# 忽略数据源配置

sharding.jdbc.config.masterslave.load-balance-algorithm-type= # 从库负载均衡算法类型，可选值：ROUND_ROBIN，RANDOM。若 `load-balance-algorithm-class-name` 存在则忽略该配置
sharding.jdbc.config.masterslave.name= # 主节点名称
sharding.jdbc.config.masterslave.master-data-source-name= # 主数据源的名称
sharding.jdbc.config.masterslave.slave-data-source-names= # 从数据源的名称
```

### 编排治理

#### 配置项说明

```properties
# 忽略数据源配置

sharding.jdbc.config.orchestration.name= # 数据治理实例名称
sharding.jdbc.config.orchestration.overwrite= # 本地配置是否覆盖注册中心配置。如果可覆盖，每次启动都以本地配置为准

sharding.jdbc.config.sharding.orchestration.name= # 数据治理实例名称
sharding.jdbc.config.sharding.orchestration.overwrite= # 本地配置是否覆盖注册中心配置。如果可覆盖，每次启动都以本地配置为准
sharding.jdbc.config.sharding.orchestration.registry.server-lists= # 连接注册中心服务器的列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
sharding.jdbc.config.sharding.orchestration.registry.namespace= # 注册中心的命名空间
sharding.jdbc.config.sharding.orchestration.registry.digest= # 连接注册中心的权限令牌。缺省为不需要权限验证
sharding.jdbc.config.sharding.orchestration.registry.operation-timeout-milliseconds= # 操作超时的毫秒数，默认 500 毫秒
sharding.jdbc.config.sharding.orchestration.registry.max-retries= # 连接失败后的最大重试次数，默认 3 次
sharding.jdbc.config.sharding.orchestration.registry.retry-interval-milliseconds= # 重试间隔毫秒数，默认 500 毫秒
sharding.jdbc.config.sharding.orchestration.registry.time-to-live-seconds= # 临时节点存活秒数，默认 60 秒

# Zookeeper 配置
sharding.jdbc.config.orchestration.zookeeper.namespace= # Zookeeper 注册中心的命名空间
sharding.jdbc.config.orchestration.zookeeper.server-lists= # Zookeeper 注册中心服务器的列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181

# Etcd 配置
sharding.jdbc.config.orchestration.etcd.server-lists= # Etcd 注册中心服务器的列表。包括 IP 地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
```
