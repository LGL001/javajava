package StarRail_김건규;

import java.util.PriorityQueue;
import java.util.Scanner;

// --- 1. 아이템(광추) 클래스 ---
abstract class LightCone {
    String name;
    int bonusAtk;

    public LightCone(String name, int bonusAtk) {
        this.name = name;
        this.bonusAtk = bonusAtk;
    }

    // 광추 특수 능력 (오버라이딩용)
    public double getSpecialMultiplier(Unit wearer, Unit target) {
        return 1.0;
    }
}

class SwornToBarbarism extends LightCone {
    public SwornToBarbarism() {
        super("흘러가는 강가를 따라", 50);
    }

    @Override
    public double getSpecialMultiplier(Unit wearer, Unit target) {
        // 적 체력이 50% 이상이면 데미지 1.2배
        if (target.hp >= (target.maxHp * 0.5)) {
            System.out.println("✨ [광추 효과] 적 체력이 많아 추가 피해 발동!");
            return 1.2;
        }
        return 1.0;
    }
}

// --- 2. 유닛(캐릭터/몬스터) 공통 클래스 ---
abstract class Unit implements Comparable<Unit> {
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int speed;
    protected int baseAtk;
    protected double actionValue;
    protected LightCone equipment; // 장착한 광추

    public Unit(String name, int hp, int speed, int baseAtk) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.speed = speed;
        this.baseAtk = baseAtk;
        this.actionValue = 10000.0 / speed;
    }

    public abstract void useSkill(Unit target);

    public void equipLightCone(LightCone cone) {
        this.equipment = cone;
        System.out.println("📦 " + this.name + "이(가) [" + cone.name + "] 광추를 장착했습니다!");
    }

    public int getTotalAttack() {
        int total = this.baseAtk;
       //광추가 있다면 광추 공격력(bonusAtk)을 더함 (능력치 보정)
        if (this.equipment != null) total += this.equipment.bonusAtk;
        return total;
    }

    // 데미지 계산 공식
    protected void dealDamage(Unit target, double skillMultiplier) {
        double rawDamage = getTotalAttack() * skillMultiplier;

       //광추의 특수 능력(getSpecialMultiplier)을 가져와 데미지 배율을 변경함
        if (this.equipment != null) {
            rawDamage *= this.equipment.getSpecialMultiplier(this, target);
        }

        // 치명타 (20% 확률)
        if (Math.random() < 0.2) {
            rawDamage *= 1.5;
            System.out.println("⚡ 치명타 발생!");
        }

        target.takeDamage((int) rawDamage);
    }

    public void attack(Unit target) {
        System.out.println("⚔️ [" + this.name + "] 일반 공격!");
        dealDamage(target, 1.0); // 평타는 계수 1.0
    }

    public void takeDamage(int dmg) {
        this.hp -= dmg;
        System.out.println("   -> 💥 " + this.name + " 피해량: " + dmg + " (남은 HP: " + this.hp + ")");
    }

    public void resetActionValue() {
        this.actionValue += 10000.0 / this.speed;
    }

    public boolean isDead() { return this.hp <= 0; }
    public String getName() { return name; }
    public double getActionValue() { return actionValue; }

    @Override
    public int compareTo(Unit o) {
        // 1. 행동 게이지 비교
        int compare = Double.compare(this.actionValue, o.actionValue);
        // 2. 만약 게이지가 같다면, 속도가 빠른 순서(내림차순)로 정렬
        if (compare == 0) {
            return Integer.compare(o.speed, this.speed);
        }
        return compare;
    }
}

// --- 3. 캐릭터 및 몬스터 구현 ---
class DanHeng extends Unit {
    public DanHeng() {
        super("아케론", 120, 110, 60); 
    }

    @Override
    public void useSkill(Unit target) {
        System.out.println("🌪️ [" + this.name + "] 전투 스킬: 풍상!");
        dealDamage(target, 2.5); // 스킬 계수 2.5
    }
}

class VoidRanger extends Unit {
    public VoidRanger() {
        super("보이드 레인저", 500, 90, 40);
    }

    @Override
    public void useSkill(Unit target) {
        System.out.println("👾 [" + this.name + "] 입자 포격!");
        dealDamage(target, 1.2);
    }
}

// --- 4. 메인 실행 클래스 (반드시 public이어야 함) ---
public class BattleSystem {
    public static void main(String[] args) {
        PriorityQueue<Unit> turnQueue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);

        // 캐릭터 생성 및 광추 장착
        DanHeng player = new DanHeng();
        player.equipLightCone(new SwornToBarbarism()); // 광추 장착!

        VoidRanger enemy = new VoidRanger();

        turnQueue.add(player);
        turnQueue.add(enemy);

        System.out.println("\n=== 🚀 스타레일 모의 전투 시작 (광추 적용됨) ===");

        while (!player.isDead() && !enemy.isDead()) {
            Unit currentUnit = turnQueue.poll();
            
            System.out.println("\n---------------------------------------");
            System.out.printf("⏳ [%s]의 턴 (행동 수치: %.0f)\n", currentUnit.getName(), currentUnit.getActionValue());

            if (currentUnit instanceof DanHeng) {
                System.out.println("1. 일반 공격  2. 전투 스킬");
                System.out.print("선택 >> ");
                try {
                    int choice = scanner.nextInt();
                    if (choice == 2) currentUnit.useSkill(enemy);
                    else currentUnit.attack(enemy);
                } catch (Exception e) {
                    scanner.nextLine(); // 버퍼 비우기
                    System.out.println("잘못된 입력입니다. 일반 공격을 수행합니다.");
                    currentUnit.attack(enemy);
                }
            } else {
                System.out.println("⚠️ 적이 공격합니다!");
                // 적은 랜덤하게 스킬 사용
                if (Math.random() < 0.3) currentUnit.useSkill(player);
                else currentUnit.attack(player);
            }

            if (!enemy.isDead() && !player.isDead()) {
                currentUnit.resetActionValue();
                turnQueue.add(currentUnit);
            }
            
            try { Thread.sleep(800); } catch (Exception e) {}
        }

        System.out.println("\n=== 🏁 전투 종료 ===");
        if (player.isDead()) System.out.println("패배...");
        else System.out.println("승리!! 적을 격파했습니다.");
        
        scanner.close();
    }
}