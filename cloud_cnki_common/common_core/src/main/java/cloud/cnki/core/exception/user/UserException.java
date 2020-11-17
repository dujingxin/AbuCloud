package cloud.cnki.core.exception.user;


import cloud.cnki.core.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
