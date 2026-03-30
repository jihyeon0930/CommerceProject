import java.util.ArrayList;
import java.util.List;

public class CommerceSystem { //커머스 플랫폼 상품 관리, 사용자 입력 처리
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
    public void start() {
        int index = 1;
        for (Category c : categores) {
            System.out.println("[ " + c.getCategoryName() + " ]");
            for (Product p : c.getProducts()) {
                System.out.printf("%d. %s\t | %,12d원 | %s \t | %d\n",
                        index++,
                        p.getName(),
                        p.getPrice(),
                        p.getEx(),
                        p.getStock());
            }
            System.out.println("0. 종료\t\t\t | 프로그램 종료");
        }
    }


}
