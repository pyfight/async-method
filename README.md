# async-method

```java
public class UserHttpService {
    public User getById(Long id) {
        // ...
        return user;
    }
}
```

可以使用``AsyncExecutor`` 进行异步调用，直接返回Future<T>对象

```java

// new或spring注入
UserHttpService userHttpService = new UserHttpService();
AsyncExecutor asyncExecutor = new AsyncExecutor(); 

Future<User> userFuture = asyncExecutor.async(userHttpService::getById, id);
User user = userFuture.get();
```
