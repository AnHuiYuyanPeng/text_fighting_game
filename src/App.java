
public class App {
    public static void main(String[] args) {
        // 启动类
        // 这个类只负责启动整个程序 里面不写任何业务逻辑
        // 启动登录注册的页面
        // Login lg = new Login();
        // lg.start();
        Fighting fighting = new Fighting();
        fighting.gameStart("张三");
    }


}
