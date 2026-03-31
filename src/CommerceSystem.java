import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem { //커머스 플랫폼 상품 관리, 사용자 입력 처리
    Scanner sc = new Scanner(System.in);


    //속성(필드) - Product 객체들 관리하는 리스트 -> 카테고리 관리하는 클래스로 변경!!
    private List<Category> categores = new ArrayList<>();

    //생성자
    public CommerceSystem(List<Category> categores) {
        this.categores = categores;
    }

    //상품 리스트 반환 게터
    public List<Category> getCategores() {
        return categores;
    }

    //기능
    //전체 상품 출력
    public void start(int choice) {
        Category c = categores.get(choice - 1); //선택 카테고리만
        while (true) {
            System.out.println("\n[ " + c.getCategoryName() + " ]");
            int index = 1;
            for (Product p : c.getProducts()) {
                System.out.printf("%d. %s\t | %,12d원 | %s\n",
                        index++,
                        p.getName(),
                        p.getPrice(),
                        p.getEx());
            }
            System.out.println("0. 뒤로가기");


            while (true) {
                int input;
                try {
                    input = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력입니다.");
                    continue;
                }
                if (input == 0) {
                    return; //뒤로가기
                }
                if (input > 0 && input <= c.getProducts().size()) {
                    Product selected = c.getProducts().get(input - 1);
                    System.out.printf("선택한 상품: %s\t | %,12d원 | %s \t | 재고: %d\n",
                            selected.getName(),
                            selected.getPrice(),
                            selected.getEx(),
                            selected.getStock());
                    return;
                } else {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }


    }


}
