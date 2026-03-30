import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("[ 실시간 커머스 플랫폼 ]");

        //전자제품 상품 리스트 생성
        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronics.add(new Product("iPhone 16", 1350000, "Apple의 초신 스마트폰", 20));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        electronics.add(new Product("irPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));

        //Category 객체 생성
        Category electronicsCategory = new Category("전자제품", electronics);

        //CommerceSystem에 전달 (Category 리스트 관리)
        List<Category> categores = new ArrayList<>();
        categores.add(electronicsCategory);
//        categores.add(clothessCategory); //의류 카테고리 추가
        CommerceSystem commerceSystem = new CommerceSystem(categores);
        commerceSystem.start();


        int inputNum = sc.nextInt();
        if (inputNum==0) {
            System.out.println("프로그램 종료");
            System.exit(0);
        }
    }
}
