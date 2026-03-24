import java.util.Scanner;
import java.util.ArrayList;
import com.fighting.domain.User;

public class Login {
    public void start() {

        ArrayList<User> users = new ArrayList<>();

        while (true) {
            System.out.println("登录页面打开了");
            System.out.println("╔═══════════════════════════════════╗");
            System.out.println("      🎮 欢迎来到文字格斗游戏 🎮      ");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.println("请选择操作: 1. 登录 2. 注册 3. 退出 ");
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            switch (choice) {
                case "1" -> login(users);
                case "2" -> register(users);
                case "3" -> exit();
                default -> System.out.println("输入错误，请重新输入");
            }
            break;
        }

    }

    // 用户登陆
    public void login(ArrayList<User> users) {

    }

    // 用户注册
    public void register(ArrayList<User> users) {
        // 创建User对象
        User user = new User();
        // 键盘录入用户名
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你的用户名");
            String userName = sc.next();
            // 校验用户名的唯一、长度必须3-16位、只能有数字、字母组成不能是纯数字
            if (!checkUserName(3, 16, userName)) {
                System.out.println("用户名长度必须3-16位");
                continue;
            }
            // 校验用户名是否唯一
            if (contains(users, userName)) {
                System.out.println("用户名已存在");
                continue;
            }
            user.setUsername(userName);
            break;
        }

        // 键盘录入密码
        while (true) {
            System.out.println("请输入你的密码");
            String passWord = sc.next();
            if (!checkUserName(3, 16, passWord)) {
                System.out.println("密码长度必须3-16位");
                continue;
            }
            if (!checkUserPassword(passWord)) {
                System.out.println("密码只能有数字、字母组成不能是纯数字");
                continue;
            }
            System.out.println("请再次输入你的密码");
            String passwordAgain = sc.next();
            if (!passwordAgain.equals(passWord)) {
                System.out.println("密码不一致");
                continue;
            }
            user.setPassword(passWord);
            break;
        }
        users.add(user);
        System.out.println("注册成功");
        return;
    }

    private boolean contains(ArrayList<User> users, String userName) {
        for (User u : users) {
            if (userName.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    // 校验用户名和密码的长度
    private boolean checkUserName(int minLength, int maxLength, String userName) {
        return userName.length() >= minLength && userName.length() <= maxLength;
    }

    // 校验密码的格式
    private boolean checkUserPassword(String pass) {
        // 密码必须同时包含数字和字母组成,不能是纯数字和纯字母
        return pass.matches("^[a-zA-Z0-9]+$") && !pass.matches("^[0-9]+$") && !pass.matches("^[a-zA-Z]+$");
    }

    // 用户退出
    public void exit() {
        System.out.println("退出成功");
        System.exit(0);
    }
}
