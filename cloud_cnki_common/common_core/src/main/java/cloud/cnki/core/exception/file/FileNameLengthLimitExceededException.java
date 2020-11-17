package cloud.cnki.core.exception.file;

/**
 * 文件名称超长限制异常类
 *
 * @author durjx
 * @date 2020-11-09
 */
public class FileNameLengthLimitExceededException extends FileException
{
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength)
    {
        super("upload.filename.exceed.length", new Object[] { defaultFileNameLength });
    }
}
