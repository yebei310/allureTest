import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;

//@Feature("前线突击测试")
public class Test03 {

    @Test(description = "侯征测试103")
//    @Story("测试发券")
    @Description("主要测试四种券发送")
    @Step("测试步骤....")
    public void failedTest(){
        Assert.assertEquals(2,2);

        System.out.println( Test03.class.getResource("") );
        System.out.println(System.currentTimeMillis());
    }
}
