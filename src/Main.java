import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
        System.out.printf("1. %-12s | %12s | %-30s\n", "Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰");
        System.out.printf("2. %-12s | %12s | %-30s\n", "iPhone 16", "1,350,000원", "Apple의 초신 스마트폰");
        System.out.printf("3. %-12s | %12s | %-30s\n", "MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북");
        System.out.printf("4. %-12s | %12s | %-30s\n", "irPods Pro", "350,00원", "노이즈 캔슬링 무선 이어폰");
        System.out.printf("0. %-12s| 프로그램 종료\n", "종료");
        int inputNum = sc.nextInt();
        if (inputNum==1) {
            return;
        }
    }
}
