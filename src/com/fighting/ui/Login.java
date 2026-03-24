import java.util.Scanner;

import com.fighting.domain.User;

public class Login {
    public void start() {
        System.out.println("登录页面打开了");
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println("      🎮 欢迎来到文字格斗游戏 🎮      ");
        System.out.println("╚═══════════════════════════════════╝");
        System.out.println("请选择操作: 1. 登录 2. 注册 3. 退出 ");
        Scanner sc = new Scanner(System.in);
        String choice = sc.next();
        switch (choice) {
            case "1" -> login();
            case "2" -> register();
            case "3" -> exit();
            default -> System.out.println("输入错误，请重新输入");
        }
    }
    // 用户登陆
    public void login() {
        User user = new User();
        user.setId(User.generateId());
        user.setUsername("admin");
        user.setPassword("123456");
        user.setStatus("1");
        System.out.println("登录成功");
    }
    // 用户注册
    public void register() {
        
    }
    // 用户退出
    public void exit() {
        System.out.println("退出成功");
        System.exit(0);
    }
}
