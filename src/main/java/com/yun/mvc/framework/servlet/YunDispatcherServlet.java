package com.yun.mvc.framework.servlet;


import com.yun.mvc.framework.context.YunApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class YunDispatcherServlet extends HttpServlet {

    private static final  String LOCATION = "contextConfigLocation";

    private Map<String , Handler> handlerMapping= new HashMap<String, Handler>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("this is yun springMVC");

        //IOC 容器必须要先初始化
        YunApplicationContext context = new YunApplicationContext(config.getInitParameter(LOCATION));

        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        initHandlerMappings(context);
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        initViewResolvers(context);
        initFlashMapManager(context);
    }


    //请求解析
    private void initMultipartResolver(YunApplicationContext context) {
    }
    //多语言、国际化
    private void initLocaleResolver(YunApplicationContext context) {
    }
    //
    private void initThemeResolver(YunApplicationContext context) {

    }
    //
    private void initHandlerMappings(YunApplicationContext context) {

    }
    //适配器
    private void initHandlerAdapters(YunApplicationContext context) {

    }
    //解析异常
    private void initHandlerExceptionResolvers(YunApplicationContext context) {

    }
    //视图转发（根据视图名字匹配带一个具体的模板）
    private void initRequestToViewNameTranslator(YunApplicationContext context) {

    }
    //
    private void initViewResolvers(YunApplicationContext context) {

    }
    //
    private void initFlashMapManager(YunApplicationContext context) {

    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * 调用自己写的controller方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("diaoyong ");
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
           resp.getWriter().write("500 Exception ,Msg:"+Arrays.toString(e.getStackTrace()));
        }
    }

    private Handler getHandler(HttpServletRequest request){

        return null;
    }

    private HandlerAdapter getHandlerAdapter(Handler handler){
        return null;
    }

    private  void doDispatch(HttpServletRequest req, HttpServletResponse resp)throws  Exception{

        //先取出来一个handler，从handleMapping
        Handler handler = getHandler(req);
        if (handler==null){
            resp.getWriter().write("404 Not Found");
            return;
        }

        //在取出一个适配器
        HandlerAdapter adapter = getHandlerAdapter(handler);
        //再有适配器去调取具体的方法
        adapter.handle(req,resp,handler);

    }

    /**
     * 方法适配器
     */
    private class HandlerAdapter{

       public void handle(HttpServletRequest req, HttpServletResponse resp,Handler handler){

       }
    }


    private class  Handler{

    }




}
