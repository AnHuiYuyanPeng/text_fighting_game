import java.util.Scanner;
import com.fighting.domain.PlayerCharacter;

public class Fighting {
    public void gameStart(String username) {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println(" 🎮 欢迎 " + username + " 来到文字格斗游戏 🎮      ");
        System.out.println("╚═══════════════════════════════════╝");

        // 创建玩家角色
        PlayerCharacter player = createPlayer(username);
        System.out.println("您的角色创建成功 ");
        for (String skill : player.skillList) {
            System.out.println("您的技能: " + skill);
        }

    }

    public void gameEnd(String username) {
        System.out.println("游戏结束");
    }

    // 创建玩家角色
    public PlayerCharacter createPlayer(String username) {
        System.out.println("请输入你的角色名称");
        System.out.println("您的角色名称: " + username);

        // 分配20属性点
        int attributePoints = 20;
        System.out.println("您有" + attributePoints + "点属性点, 请分配到以下属性: 生命值、攻击力、防御力");
        System.out.println("生命值每点+100生命值");
        System.out.println("攻击力每点+10攻击力");
        System.out.println("防御力每点+5防御力");
        Scanner hpScanner = new Scanner(System.in);
        String[] attributes = { "生命值", "攻击力", "防御力" };
        int[] attributeValues = new int[3];
        int i = 0;
        for (String attribute : attributes) {
            if (attribute.equals("防御力") && attributePoints != 0) {
                System.out.println("剩余" + attributePoints + "点属性点, 默认分配给防御力");
                attributeValues[i] = attributePoints;
            } else {
                int hp = 0;
                if (attributePoints != 0) {
                    System.out.println("请输入" + attribute + "的属性值");
                    hp = hpScanner.nextInt();
                }

                if (hp <= 0) {
                    System.out.println("属性值不能小于0, 默认分配为0");
                    attributeValues[i] = 0;
                }
                if (hp > attributePoints) {
                    System.out.println("属性值不能大于" + attributePoints + ", 默认分配为" + attributePoints);
                    attributeValues[i] = attributePoints;
                }
                attributePoints -= attributeValues[i];
                System.out.println("您分配了" + attributeValues[i] + "点" + attribute);
                i++;
            }

        }

        PlayerCharacter player = new PlayerCharacter(
                username,
                100 + attributeValues[0] * 100,
                10 + attributeValues[1] * 10,
                0 + attributeValues[2] * 5);

        player.skillList.add("舜狱影杀阵");
        player.skillList.add("万雷天牢引");
        player.skillList.add("暗影杀缭乱");
        player.skillList.add("慈悲渡魂落");
        return player;
    }

    // 创建敌人角色

}
