import java.util.Scanner;

import com.fighting.domain.EnemyCharacter;
import com.fighting.domain.PlayerCharacter;

import java.util.ArrayList;
import java.util.Random;

public class Fighting {
    public void gameStart(String username) {
        System.out.println("╔═══════════════════════════════════╗");
        System.out.println(" 🎮 欢迎 " + username + " 来到文字格斗游戏 🎮      ");
        System.out.println("╚═══════════════════════════════════╝");

        // 创建玩家角色
        PlayerCharacter player = createPlayer(username);
        System.out.println("您的角色创建成功 ");

        // 创建敌人角色
        ArrayList<EnemyCharacter> enemies = new ArrayList<>();
        enemies.add(new EnemyCharacter("德玛西亚之力", 80, 15, 10, "大宝剑"));
        enemies.add(new EnemyCharacter("德邦总管", 60, 20, 5, "三连捅"));
        enemies.add(new EnemyCharacter("德玛西亚皇子", 120, 10, 20, "EQ二连击"));
        enemies.add(new EnemyCharacter("光辉女郎", 70, 25, 8, "让他们都亮起来吧"));

        // 准备战斗
        // 记录与第几个敌人战斗
        int enemyIndex = 0; // 从0开始
        // 记录胜利几场
        int winCount = 0;

        // 只要还存活就循环与敌人战斗
        game: while (player.isAlive()) {
            if (winCount != 0) {
                // 每场敌人血量增加10 攻击力增加3 防御力增加2
                enemies.get(enemyIndex).setMaxHP(enemies.get(enemyIndex).getMaxHP() + 10);
                enemies.get(enemyIndex).setHP(enemies.get(enemyIndex).getMaxHP());
                enemies.get(enemyIndex).setAttack(enemies.get(enemyIndex).getAttack() + 3);
                enemies.get(enemyIndex).setDefense(enemies.get(enemyIndex).getDefense() + 2);
                // 每场战斗前防御状态重置
                enemies.get(enemyIndex).defending = false;
            }
            // 选择敌人
            EnemyCharacter enemy = enemies.get(enemyIndex);

            // 战斗开始
            System.out.println("================================================");
            System.out.println("您遇到了" + enemy.getName() + "，敌人血量: " + enemy.getHP() + "，敌人攻击力: " + enemy.getAttack()
                    + "，敌人防御力: " + enemy.getDefense() + "，敌人技能: " + enemy.getSkill());
            System.out.println("================================================");

            // 战斗回合
            int round = 1;
            while (player.isAlive()) {
                System.out.println("第" + round + "回合");
                // 打印敌我双方的血条
                System.out.println(getBloodBar(player.getName(), player.getHP(), player.getMaxHP()));
                System.out.println(getBloodBar(enemy.getName(), enemy.getHP(), enemy.getMaxHP()));
                System.out.println("================================================");

                // 玩家攻击
                playerAttack(player, enemy);
                // 判断敌人是否死亡
                if (!enemy.isAlive()) {
                    System.out.println("您击败了" + enemy.getName());
                    if (enemyIndex >= enemies.size() - 1) {
                        System.out.println("您击败了所有敌人，游戏胜利");
                        gameEnd(winCount + 1);
                        break game;
                    }
                    // 询问是否进入下一场战斗
                    System.out.println("是否进入下一场战斗？(y/n)");
                    Scanner scanner = new Scanner(System.in);
                    String choice = scanner.next();
                    if (choice.equals("y")) {
                        winCount++;
                        enemyIndex++;
                        // 跳出内层回合循环，让外层重新执行 enemies.get(enemyIndex) 才能切换敌人
                        break;
                    } else if (choice.equals("n")) {
                        gameEnd(winCount);
                        break game;
                    } else {
                        System.out.println("输入错误，默认继续战斗");
                        winCount++;
                        enemyIndex++;
                        // 跳出内层回合循环，让外层重新执行 enemies.get(enemyIndex) 才能切换敌人
                        break;
                    }
                }
                // 敌人攻击
                enemyAttack(enemy, player);
                // 判断玩家是否死亡
                if (!player.isAlive()) {
                    System.out.println("您被" + enemy.getName() + "击败了");
                    gameEnd(winCount);
                    break;
                }
                round++;
            }

        }
        
    }

    public void gameEnd(int winCount) {
        System.out.println("游戏结束");
        System.out.println("您总共胜利了" + winCount + "场");
        System.out.println("感谢您的光临，再见！");
        System.exit(0);
    }

    // 创建玩家角色
    public PlayerCharacter createPlayer(String username) {
        System.out.println("请输入你的角色名称");
        System.out.println("您的角色名称: " + username);

        // 分配20属性点
        int attributePoints = 20;
        System.out.println("您有" + attributePoints + "点属性点, 请分配到以下属性: 生命值、攻击力、防御力");
        System.out.println("生命值每点+10生命值");
        System.out.println("攻击力每点+2攻击力");
        System.out.println("防御力每点+1防御力");
        Scanner hpScanner = new Scanner(System.in);
        String[] attributes = { "生命值", "攻击力", "防御力" };
        int[] attributeValues = new int[3];
        int attributeIndex = 0;
        for (String attribute : attributes) {
            System.out.println("请输入" + attribute + "的属性值");
            int input = hpScanner.nextInt();
            // 如果是负数
            if (input < 0) {
                System.out.println("属性值不能小于0, 默认分配为0");
                input = 0;
            }
            // 如果是大于属性点
            if (input > attributePoints) {
                System.out.println("属性值不能大于" + attributePoints + ", 默认分配为" + attributePoints);
                input = attributePoints;
            }

            // 计算剩余属性点
            attributePoints -= input;
            // 赋值给属性值数组
            attributeValues[attributeIndex] = input;
            System.out.println("您分配了" + input + "点" + attribute);
            attributeIndex++;
        }

        PlayerCharacter player = new PlayerCharacter(
                username,
                100 + attributeValues[0] * 10,
                10 + attributeValues[1] * 2,
                0 + attributeValues[2] * 1);

        player.skillList.add("普通攻击");
        player.skillList.add("万雷天牢引");
        player.skillList.add("暗影杀缭乱");
        player.skillList.add("慈悲渡魂落");
        return player;
    }

    // 定义一个方法打印敌我双方的血条方块
    public String getBloodBar(String name, int HP, int maxHP) {
        // 满血状态打印20个方块
        int bloodBarLength = 20;
        int filled = (int) ((HP * 1.0 / maxHP) * bloodBarLength);
        StringBuilder bloodBar = new StringBuilder();
        bloodBar.append(name).append("：[");

        // 利用循环拼接方块和空格
        for (int i = 0; i < bloodBarLength; i++) {
            if (i < filled) {
                bloodBar.append("█");
            } else {
                bloodBar.append(" ");
            }
        }
        bloodBar.append("]").append(HP).append("/").append(maxHP).append("HP");
        return bloodBar.toString();
    }

    // 玩家攻击
    public void playerAttack(PlayerCharacter player, EnemyCharacter enemy) {
        System.out.println("======玩家攻击=======");
        System.out.println("1. 普通攻击");
        System.out.println("2. 万雷天牢引");
        System.out.println("3. 暗影杀缭乱");
        System.out.println("4. 慈悲渡魂落");
        System.out.println("请选择技能");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        switch (choice) {
            case "1":
                System.out.println("您使用了普通攻击");
                int damage = calculateDamage(player.getAttack(), enemy.getDefense());
                enemy.isDamage(damage);
                System.out.println("您对" + enemy.getName() + "造成了" + damage + "点伤害");
                break;
            case "2":
                // 如果玩家血量小于10点 则不使用万雷天牢引
                if (player.getHP() < 10) {
                    System.out.println("您的血量不足10点，无法使用万雷天牢引");
                    break;
                }
                System.out.println("您使用了万雷天牢引");
                int damage2 = calculateDamage((int) (player.getAttack() * 1.8), enemy.getDefense());
                enemy.isDamage(damage2);
                System.out.println("您对" + enemy.getName() + "造成了" + damage2 + "点伤害");
                // 使用万雷天牢引后 玩家血量减少5点
                player.isDamage(5);
                System.out.println("使用万雷天牢引后 您受到了" + 5 + "点伤害");
                break;
            case "3":
                // 如果玩家血量小于20点 则不使用暗影杀缭乱
                if (player.getHP() < 20) {
                    System.out.println("您的血量不足20点，无法使用暗影杀缭乱");
                    break;
                }
                System.out.println("您使用了暗影杀缭乱");
                int damage3 = calculateDamage(player.getAttack() * 2, enemy.getDefense());
                enemy.isDamage(damage3);
                System.out.println("您对" + enemy.getName() + "造成了" + damage3 + "点伤害");
                // 使用暗影杀缭乱后 玩家血量减少10点
                player.isDamage(10);
                System.out.println("使用暗影杀缭乱后 您受到了" + 10 + "点伤害");
                break;
            case "4":
                System.out.println("您使用了慈悲渡魂落");
                // 使用慈悲渡魂落后 玩家血量恢复50点
                player.isFullHP(50);
                System.out.println("使用慈悲渡魂落后 您恢复了" + 50 + "点血量");
                break;
            default:
                System.out.println("输入错误，默认使用普通攻击");
                break;
        }
    }

    // 敌人攻击
    public void enemyAttack(EnemyCharacter enemy, PlayerCharacter player) {
        System.out.println("======敌人攻击=======");

        // 进行几率的计算是普通攻击还是技能攻击
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        String skill = "普通攻击";
        if (randomNumber < 5) {
            skill = enemy.getSkill();
        }

        switch (skill) {
            case "普通攻击":
                System.out.println("敌人使用了普通攻击");
                int damage = calculateDamage(enemy.getAttack(), player.getDefense());
                player.isDamage(damage);
                System.out.println("敌人对您造成了" + damage + "点伤害");
                break;
            case "大宝剑":
                System.out.println("敌人使用了技能 大宝剑");
                int damage1 = calculateDamage((int) (enemy.getAttack() * 1.2), player.getDefense());
                player.isDamage(damage1);
                System.out.println("敌人对您造成了" + damage1 + "点伤害");
                break;
            case "三连捅":
                System.out.println("敌人使用了技能 三连捅");
                int damage2 = calculateDamage((int) (enemy.getAttack() * 1.4), player.getDefense());
                player.isDamage(damage2);
                System.out.println("敌人对您造成了" + damage2 + "点伤害");
                break;
            case "让他们都亮起来吧":
                System.out.println("敌人使用了技能 让他们都亮起来吧");
                enemy.defending = true;
                System.out.println("敌人进入了防御状态");
                break;
            case "EQ二连击":
                System.out.println("敌人使用了技能 EQ二连击");
                int damage3 = calculateDamage((int) (enemy.getAttack() * 1.6), player.getDefense());
                player.isDamage(damage3);
                System.out.println("敌人对您造成了" + damage3 + "点伤害");
                break;
        }
    }

    // 计算双方攻击造成的伤害
    public int calculateDamage(int attack, int defense) {
        int damage = attack - defense;
        if (damage < 1) {
            damage = 1;
        }
        return damage;
    }
}
