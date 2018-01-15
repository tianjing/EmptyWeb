package tgtools.plugin.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 13:51
 */
@Controller
@RequestMapping("/DemoRest")
public class DemoRest {
    //访问路径：http://ip/EmptyWeb/myrest/DemoRest/hello
    @RequestMapping(value = "/hello",method= {RequestMethod.GET,RequestMethod.POST})
    public void hello(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getOutputStream().write("hello".getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
