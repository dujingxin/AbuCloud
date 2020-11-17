package cloud.cnki.core.exception.user;

/**
 * 用户错误最大次数异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class UserPasswordRetryLimitExceedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount)
    {
        super("user.password.retry.limit.exceed", new Object[] { retryLimitCount });
    }
}
