package cloud.cnki.core.exception.user;

/**
 * 用户账号已被删除
 *
 * @author durjx
 * @date 2020-11-09
 */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
