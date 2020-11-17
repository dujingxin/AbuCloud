package cloud.cnki.core.exception.user;

/**
 * 验证码错误异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class CaptchaException extends UserException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException()
    {
        super("user.jcaptcha.error", null);
    }
}
