package com.fc.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBases {
    //加载读取配置文件properties
    public Properties prop;
    //构造函数
    public TestBases(){
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/fc/config/config.properties");
            prop.load(fis);
        }catch (FileNotFoundException e ){
            e.printStackTrace();
        } catch (IOException e1){
            e1.printStackTrace();
        }

    }
}
