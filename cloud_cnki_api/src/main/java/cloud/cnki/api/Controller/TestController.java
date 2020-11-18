package cloud.cnki.api.Controller;

import cloud.cnki.core.utils.IpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TestController
 *
 * @author durjx
 * @date 2020-11-12
 */
@RestController
public class TestController {
    @GetMapping("test")
    public String test(HttpServletRequest httpRequest){
        return "测试成功:"+   IpUtils.getIpAddr(httpRequest);
    }
}
