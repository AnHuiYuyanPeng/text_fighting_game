import java.util.Scanner;
import java.util.ArrayList;
import com.fighting.domain.User;
import com.fighting.domain.UserStatus;
import java.util.Random;

public class Login {
    public void start() {

        ArrayList<User> users = new ArrayList<>();
        while (true) {
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
        }
    }

    // 用户登陆
    public void login(ArrayList<User> users) {
        System.out.println("请输入你的用户名");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        // 不存在则提示用户名不存在 存在的话则查看用户的状态是否在线
        if (!contains(users, username)) {
            System.out.println("用户名不存在");
            return;
        } else {
            String status = getUserStatus(users, username);
            if (status.equals(UserStatus.LOCKED.getStatus())) {
                System.out.println("用户被锁定");
                return;
            }
        }

        // 输入密码 错误三次锁定
        int lockedCount = 0;
        while (true) {
            System.out.println("请输入密码");
            String password = sc.next();
            // 输入验证码
            while (true) {
                String codeStr = getCode();
                System.out.println("验证码为: " + codeStr);
                System.out.println("请输入验证码");
                String code = sc.next();
                if (code.equalsIgnoreCase(codeStr)) {
                    break;
                } else {
                    System.out.println("验证码错误 请重新输入");
                    continue;
                }
            }
            if (password.equals(getPassword(users, username))) {
                lockedCount = 0; // 重置锁定次数
                break;
            } else {
                lockedCount++;
                System.out.println("密码错误 请重新输入 你还有" + (3 - lockedCount) + "次机会");
                if (lockedCount >= 3) {
                    System.out.println("密码错误三次 用户被锁定");
                    for (User u : users) {
                        if (username.equals(u.getUsername())) {
                            u.setStatus(UserStatus.LOCKED);
                        }
                    }
                    break;
                }
                continue;
            }
        }

        if (lockedCount >= 3)
            return;
        System.out.println("登录成功");
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
        user.setStatus(UserStatus.OFFLINE);
        users.add(user);
        System.out.println("注册成功");
        return;
    }

    // 校验用户名是否存在
    private boolean contains(ArrayList<User> users, String userName) {
        for (User u : users) {
            if (userName.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    // 获取用户密码
    private String getPassword(ArrayList<User> users, String username) {
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                return u.getPassword();
            }
        }
        return null;
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

    // 生成验证码
    private String getCode() {
        /*
         * 生成一个5位数的随机数
         * 1. 由四位大写或者小写字母和意为数字组成, 同一个字母可以重复
         * 2. 数字可以随机出现任意位置
         * 3. 比如aq1ai
         */
        ArrayList<Character> code = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            code.add((char) (i + 'a'));
            code.add((char) (i + 'A'));
        }
        Random random = new Random();
        StringBuilder codeStr = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(code.size());
            char c = code.get(index);
            codeStr.append(c);
        }

        codeStr.append(random.nextInt(10));

        // 打乱codeStr中的字符顺序
        for (int i = 0; i < codeStr.length(); i++) {
            int index = random.nextInt(codeStr.length());
            char temp = codeStr.charAt(i);
            codeStr.setCharAt(i, codeStr.charAt(index));
            codeStr.setCharAt(index, temp);
        }

        return codeStr.toString();
    }

    // 获取当前用户的登录状态
    private String getUserStatus(ArrayList<User> users, String username) {
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                return u.getStatus();
            }
        }
        return null;
    }

}
