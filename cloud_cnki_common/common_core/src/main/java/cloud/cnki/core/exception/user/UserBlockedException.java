package cloud.cnki.core.exception.user;

/**
 * 用户锁定异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class UserBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserBlockedException()
    {
        super("user.blocked", null);
    }
}
