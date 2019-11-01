package common.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    //加载读取properties文件
    public Properties prop;
    //构造函数
    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/common/config/config.properties");
            prop.load(fis);
        }catch(FileNotFoundException f){
            f.printStackTrace();
        }catch (IOException i){
            i.printStackTrace();
        }
    }
}
