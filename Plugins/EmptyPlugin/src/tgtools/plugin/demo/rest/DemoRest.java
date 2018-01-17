package tgtools.plugin.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tgtools.json.JSONException;
import tgtools.json.JSONObject;

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
    //访问路径：http://ip/EmptyWeb/myrest/DemoRest/hello
    @RequestMapping(value = "/hellojson",method= {RequestMethod.GET,RequestMethod.POST})
    public void hellojson(HttpServletRequest request, HttpServletResponse response) {
        try {
            JSONObject json =new JSONObject();
            json.put("aa","11");
            json.put("bb",22);
            json.put("cc","33");
            JSONObject d=new JSONObject();
            d.put("da","a");
            json.put("dd",d);
            response.setContentType("application/json");
            response.getOutputStream().write(json.toString().getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
