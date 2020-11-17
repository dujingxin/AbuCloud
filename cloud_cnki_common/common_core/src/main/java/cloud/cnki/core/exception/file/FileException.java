package cloud.cnki.core.exception.file;


import cloud.cnki.core.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
