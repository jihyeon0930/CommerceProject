import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CommerceSystem {
    //속성(필드) - Product 객체들 관리하는 리스트
    private List<Product> products = new ArrayList<>();

    //생성자
    public CommerceSystem(List<Product> products) {
        this.products = products;
    }

    //상품 리스트 반환 게터
    public List<Product> getProducts() {
        return products;
    }

    //기능
    //전체 상품 출력
    public void start() {
        int index = 1;
        for (Product p : products) {
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
