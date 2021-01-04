问： 01-docs/sql/*.sql 和 saas-tenant/saas-tenant-server/src/main/resources/sqls/*.sql 有什么区别？ 
答：01-docs/sql/*.sql 存放的是系统内置的数据库脚本，脚本里面出除了 表结构 和一些 必要数据 ，还 维护了一些丰富的 业务数据 ，主要用于启动项目 。 saas-tenant/saas-tenant-server/sqls/*.sql 里面的脚本用于项目启动后，配置 SCHEMA 模 式，新建租户时使用。
saas_base.sql 存放的是新租户基础库的 表结构 saas_base_data.sql 存放的是新租户基础库的 必要数据 ， 没有 docs/sql/*.sql 里面的 业务数据 ！！！ 
saas_extend.sql 存放的是新租户扩展库的 表结构 saas_extend_data.sql 存放的是新租户扩展库的 必要数据 ， 没有 docs/sql/*.sql 里面的 业务数据 ！！！ 
脚本里面的 SELECT 1 用于防止代码执行空脚本时报错，就随便放入一条无关紧要的sql （可以改成其他 无关紧要的sql）

saas-authority-server 、 saas-gateway-server 、 saas-msgs-server 等任意一个服务 src/main/resources/sqls/*.sql 下的脚本文件用于 DATASOURCE 模式，新建租户时使用。
SCEHMA模式是在tenant服务一次性将整个服务脚本初始化，DATASOURCE模式是每个服务、每个租户独立数 据源，所以得分散在各自的服务初始化自己服务的脚本和数据
