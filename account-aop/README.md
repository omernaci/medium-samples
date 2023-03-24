## Description

```java
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.omernaci.accountaop.service.AccountService.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Before " + joinPoint.getSignature().getName() + "()");
    }

    @AfterReturning(pointcut = "execution(* com.omernaci.accountaop.service.AccountService.*(..))",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("After " + joinPoint.getSignature().getName() + "(): " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.omernaci.accountaop.service.AccountService.*(..))",
            throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        logger.error("Exception in " + joinPoint.getSignature().getName() + "(): " + exception.getMessage());
    }

}
```

In this aspect, we use three different pointcuts to log before, after returning, 
and after throwing the account operations. We can then use the **AccountService** to perform account operations and the aspect will log the operations accordingly. For example:

```java
@Autowired
private AccountService accountService;

public void performOperations() {
    String accountNumber = "1234";
    double amount = 100.0;
    accountService.deposit(accountNumber, amount);
    accountService.withdraw(accountNumber, amount + 50);
    accountService.getBalance(accountNumber);
}

```
