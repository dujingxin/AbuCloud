package cloud.cnki.core.exception.user;

/**
 * 用户不存在异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
