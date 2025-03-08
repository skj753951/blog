API网关模块 (gateway)
技术栈：Spring Cloud Gateway + Nginx 
职责：
统一路由转发（/api/user/** → 用户服务）
跨域处理与请求限流
JWT令牌校验与权限过滤
负载均衡与服务熔断

用户服务模块 (user-service)
技术栈：Spring Boot + Spring Security + JWT
职责：
用户注册/登录（含GitHub OAuth2）
角色权限管理（RBAC模型）
个人资料修改与头像上传
操作日志记录与审计

内容服务模块 (content-service)
技术栈：Spring Boot + MyBatis-Plus + Elasticsearch
职责：
文章CRUD与状态管理（发布/草稿/删除）
分类与标签系统的维护
全文搜索实现（MySQL/ES双引擎）
热门文章缓存策略（Redis ZSET）

互动服务模块 (interact-service)
技术栈：Spring Boot + RabbitMQ
职责：
评论/回复的发布与嵌套展示
敏感词实时过滤（DFA算法）
点赞/收藏异步处理
站内消息通知（邮件/WebSocket）

文件服务模块 (file-service)
技术栈：Spring Boot + MinIO + FFmpeg
职责：
图片/视频上传与OSS存储管理
文件元数据记录（MD5去重）
图片压缩与水印处理
视频转码与封面生成

公共核心模块 (common-core)
技术栈：Spring Boot Starter
包含组件：
统一响应封装（Result<T>）
全局异常处理（@ControllerAdvice）
工具类库（日期处理、加密解密）
数据校验框架（Validation + 自定义注解）
Swagger接口文档配置
分布式ID生成器（Snowflake）

四、基础设施模块 (infrastructure)
技术栈：Docker + Prometheus + ELK
包含服务：
MySQL集群：主从读写分离
Redis哨兵：缓存热点数据
MinIO存储：私有云对象存储
RabbitMQ：异步任务队列
ELK日志：集中化日志管理
Prometheus：系统健康监控