import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");

        List<Product> products = new ArrayList<>();
        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        products.add(new Product("iPhone 16", 1350000, "Apple의 초신 스마트폰", 20));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        products.add(new Product("irPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));
        //상품 출력
        CommerceSystem commerceSystem = new CommerceSystem(products); //값 넣어줌
        commerceSystem.start();


        int inputNum = sc.nextInt();
        if (inputNum==1) {
            return;
        }
    }
}
