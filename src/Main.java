import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //전자제품 상품 리스트 생성
    private static List<Product> createElectronics() {
        List<Product> electronics = new ArrayList<>();
        electronics.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronics.add(new Product("iPhone 16", 1350000, "Apple의 초신 스마트폰", 0));
        electronics.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 30));
        electronics.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 40));
        return electronics;
    }

    //의류 상품 리스트 생성
    private static List<Product> createClothes() {
        List<Product> clothes = new ArrayList<>();
        clothes.add(new Product("봄 니트", 30000, "소재 : 울", 50));
        clothes.add(new Product("봄 자캣", 150000, "소재 : 나일론", 30));
        clothes.add(new Product("청바지", 40000, "소재 : 합성", 100));
        return clothes;
    }

    //식품 상품 리스트 생성
    private static List<Product> createFood() {
        List<Product> food = new ArrayList<>();
        food.add(new Product("김치찌개 밀키트", 1200000, "국산", 17));
        food.add(new Product("부대찌개 밀키트", 1350000, "미국산", 21));
        return food;
    }

    private static List<Category> createCategories() {
        //Category 객체 생성
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", createElectronics()));
        categories.add(new Category("의류", createClothes()));
        categories.add(new Category("식품", createFood()));
        return categories;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Category> categories = createCategories();
        //CommerceSystem에 전달 (Category 리스트 관리)
        CommerceSystem commerceSystem = new CommerceSystem(categories, sc);
        AdminSystem adminSystem = new AdminSystem(categories, sc);

        State state = State.MAIN;

        while (state != State.EXIT) {
            state = switch (state) {
                case MAIN -> commerceSystem.showMain();
                case CATEGORY -> commerceSystem.showCategory();
                case PRODUCT -> commerceSystem.selectProduct();
                case CART -> commerceSystem.cart();
                case ORDER -> commerceSystem.order();
                case ADMIN -> adminSystem.adminMode();
                default -> state;
            };
        }
        System.out.println(":::프로그램 종료");
    }
}
