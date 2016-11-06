import app.Transplanter;

/**
 * Created by worker on 2016/4/29.
 */
public class AppTest {
    public static void main(String[] args){
        boolean[] indicator = {true, true, true};
        if(indicator[0]){
            testPython();
        }
        if(indicator[1]){
            testCpp();
        }
        if(indicator[2]){
            testMatlab();
        }
    }

    private static void testPython(){
        System.out.println("test----python!");
        Transplanter.operatePython("example/python\\hello.py", null, null);
        Transplanter.operatePython("example/python/hello.py", "sayHello", null);
        Transplanter.operatePython("example/python/hello.py", "sayHelloTo", "'world'");
    }

    private static void testCpp(){
        System.out.println("test----Cpp!");
        Transplanter.operateCpp("example/cpp/hello.cpp", "void hi", null);
        Transplanter.operateCpp("example/cpp/hello.dll", "string sayHello", null);
        Transplanter.operateCpp("example/cpp/hello.dll", "void sayHelloTo", "\"world\"");
        Transplanter.operateCpp("example/cpp/hello.dll", "void figureShow", "2, 0.897");
        Transplanter.operateCpp("example/cpp/hello.dll", "void add", null);
        Transplanter.operateCpp("example/cpp/hello.dll", "int num", null);
        Transplanter.operateCpp("example/cpp/hello.c", "void hi", null);
    }

    private static void testMatlab(){
        System.out.println("test----Matlab!");
        Transplanter.operateMatlab("example/matlab/hello.m", null, null);
        Transplanter.operateMatlab("example/matlab/draw.m", "draw", null);
        Transplanter.operateMatlab("example/matlab/magicsquare.m", "magicsquare", "5");
    }
}
