MyBatis SQL Mapper Framework for Java
=====================================

[![Coverage Status](https://coveralls.io/repos/mybatis/mybatis-3/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/mybatis-3?branch=master)
[![Stack Overflow](http://img.shields.io/:stack%20overflow-mybatis-brightgreen.svg)](http://stackoverflow.com/questions/tagged/mybatis)

---
## mybatis-3源码分析
> 1.mybatis运行流程大致了解
> 2.TypeHandler自己定义   
> 3.plugin自己定义  
> 4.源码追踪，debug大致看一下关键代码   

---

##### 1.解析xml,全局配置
XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);     

##### 2.xml解析对应的java对象Configuration  
protected Environment environment;    
protected Properties variables = new Properties();   
protected final Map<String, MappedStatement> mappedStatements = new StrictMap<MappedStatement>   
protected final Map<String, Cache> caches = new StrictMap<>("Caches collection");   
protected final Map<String, ResultMap> resultMaps = new StrictMap<>("Result Maps collection");   
protected final Map<String, ParameterMap> parameterMaps = new StrictMap<>("Parameter Maps collection");    

##### 3.获取sqlSqssion   
```html
private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
Transaction tx = null;
try {
  final Environment environment = configuration.getEnvironment();
  final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
  tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
  final Executor executor = configuration.newExecutor(tx, execType);
  return new DefaultSqlSession(configuration, executor, autoCommit);
} catch (Exception e) {
  closeTransaction(tx); // may have fetched a connection so lets call close()
  throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
} finally {
  ErrorContext.instance().reset();
}
}
```

##### 4.创建代理对象    
BunkTypeMapper bunkTypeMapper = sqlSession.getMapper(BunkTypeMapper.class); 

```html
public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
  return mapperRegistry.getMapper(type, sqlSession);
} 

public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
    if (mapperProxyFactory == null) {
      throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
    }
    try {
      return mapperProxyFactory.newInstance(sqlSession);
    } catch (Exception e) {
      throw new BindingException("Error getting mapper instance. Cause: " + e, e);
    }
} 


public RoutingStatementHandler(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
    switch (ms.getStatementType()) {
      case STATEMENT:
        delegate = new SimpleStatementHandler(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        break;
      case PREPARED:
        delegate = new PreparedStatementHandler(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        break;
      case CALLABLE:
        delegate = new CallableStatementHandler(executor, ms, parameter, rowBounds, resultHandler, boundSql);
        break;
      default:
        throw new ExecutorException("Unknown statement type: " + ms.getStatementType());
    }
} 
```
---
##### 5.Mapper接口是怎么给Spring进行管理的？
> 5.1 xml挂载点，MapperScannerConfigurer
> 5.2 注解方式，MapperScannerRegistrar

---
##### 6.sqlSession生命周期在加事务有什么变化？ 
在没有Transaction情况下生命周期是request/method级别的
在有Transaction情况下生命周期是Transaction范围里面的

---
