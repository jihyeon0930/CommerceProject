import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //전자제품 상품 리스트 생성
    private static List<Product> createElectronics() {
        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronics.add(new Product("iPhone 16", 1350000, "Apple의 초신 스마트폰", 20));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        electronics.add(new Product("irPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));
        return electronics;
    }


    private static List<Category> createCategories() {
        //Category 객체 생성
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", createElectronics()));
        categories.add(new Category("의류", createElectronics()));
        categories.add(new Category("식품", createElectronics()));
        return categories;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //CommerceSystem에 전달 (Category 리스트 관리)
        CommerceSystem commerceSystem = new CommerceSystem(createCategories());

        while (true){
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 종료\t\t\t | 프로그램 종료");

            int mainInput = sc.nextInt();
            switch (mainInput) {
                case 1: commerceSystem.start(1); break;
                case 2: commerceSystem.start(2); break;
                case 3: commerceSystem.start(3); break;
                case 0:
                    System.out.println(":::프로그램 종료");
                    return; //while 탈출
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해 주세요.");
                    continue;
            }
        }
    }
}
