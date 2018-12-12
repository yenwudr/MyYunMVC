package com.yun.mvc.framework.context;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class YunApplicationContext {

    private Map<String,Object> instanceMapping=new ConcurrentHashMap<String, Object>();

    private List<String > classCache= new ArrayList<String>();


    public YunApplicationContext(String location) {
        InputStream is = null;
        try {
            //  定位
            is = this.getClass().getClassLoader().getResourceAsStream(location);
            // 加载
            Properties config = new Properties();
            config.load(is);
            //注册，把所有的class找出来
            String packageName = config.getProperty("scanPackage");
            doRegiser(packageName);

            // 初始化，只要循环class
            doCreateBean();
            //注入
            populate();
            //先加载配置文件
            //定位，加载，注册，注册，注入
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 把符合条件的所有class全部找出来注册到缓存
     * @param packageName
     */
    private void doRegiser(String packageName) {
        URL resource = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File dir = new File(resource.getFile());

        for (File file : dir.listFiles()){
            //如果是一个文件夹，继续递归
            if (file.isDirectory()){
                doRegiser(packageName+"."+file.getName());
            }else{
                classCache.add(packageName+"."+file.getName().replace(".class",""));
            }
        }

    }

    private void doCreateBean(){
        //检查有没有注册信息
        if (classCache.size()==0){
            return;
        }

        try {

            for (String className : classCache) {
                System.out.println();
                //知道有一个套路，使用cglib 还是 jdk
                Class<?> clazz = Class.forName(className);

                //判断哪些类需要初始化
                //添加了注解 @service，@controller
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 注入
     */
    private void populate(){

    }

    public Object getBean(String name){
        return null;
    }

    public Map<String,Object> getAll(){
        return instanceMapping;

    }
}
