import java.util.*;

public class CommerceSystem { //커머스 플랫폼 상품 관리, 사용자 입력 처리
    //속성(필드) - Product 객체들 관리하는 리스트 -> 카테고리 관리하는 클래스로 변경!!
    private List<Category> categories;
    private Map<Product, Integer> cartItems = new HashMap<>();

    private Scanner sc;
    private Category selectedCategory;
    private Product selectedProduct;

    //생성자
    public CommerceSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
    }

    //상품 리스트 반환 게터
    public List<Category> getCategores() {
        return categories;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    //기능
    //입력 처리 메서드
    private int getIntInput(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해 주세요.");
            }
        }
    }

    //MAIN 상태
    public State showMain() {
        AdminSystem adminSystem = new AdminSystem(categories, sc);

        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");
        System.out.println("0. 종료\t\t\t | 프로그램 종료");
        if (!cartItems.isEmpty()) {
            System.out.println("\n[ 주문 관리 ]");
            System.out.println("4. 장바구니 확인 \t| 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소 \t| 진행중인 주문을 취소합니다.");
        }
        System.out.println("\n\n[ 관리자 전용 ]");
        System.out.println("6. 관리자 모드");
        System.out.println("======================================");

        int mainInput = getIntInput("번호 선택 : ");

        if (mainInput == 0) {
            return State.EXIT;
        }
        if (mainInput >= 1 && mainInput <= categories.size()) {
            selectedCategory = categories.get(mainInput - 1);
            return State.CATEGORY;
        }
        if (!cartItems.isEmpty() && mainInput == 4) {
            return order();
        }
        if (mainInput == 6) {
            return adminSystem.adminModePass();
        }

        System.out.println("\n유효하지 않은 카테고리 번호 입력.");
        return State.MAIN;
    }

    //CATEGORY 상태 (원래 start) - 전체 상품 출력
    public State showCategory() {
        if (selectedCategory == null) {
            System.out.println("선택된 카테고리가 없습니다.");
            return State.MAIN;
        }
        System.out.println("\n[ " + selectedCategory.getCategoryName() + " ]");
        int index = 1;
        for (Product p : selectedCategory.getProducts()) {
            System.out.printf("%d. %s\t | %,12d원 | %s\n",
                    index++,
                    p.getName(),
                    p.getPrice(),
                    p.getEx());
        }
        System.out.println("0. 뒤로가기");
        return State.PRODUCT;
    }

    //PRODUCT 상태 (선택 상품 출력 - 상세보기)
    public State selectProduct() {
        System.out.println("======================================");

        int input = getIntInput("상세보가 할 상품 번호 선택 : ");

        if (input == 0) {
            return State.MAIN;
        }
        if (input > 0 && input <= selectedCategory.getProducts().size()) {
            selectedProduct = selectedCategory.getProducts().get(input - 1);
            System.out.printf("\n선택한 상품: %s\t | %,12d원 | %s \t | 재고: %d\n",
                    selectedProduct.getName(),
                    selectedProduct.getPrice(),
                    selectedProduct.getEx(),
                    selectedProduct.getStock());
            return State.CART;
        }

        System.out.println("\n유효하지 않은 상품 번호 입력.");
        return State.PRODUCT;
    }

    //CART 상태 (장바구니)
    public State cart() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");

        int input = getIntInput("확인(1) or 취소(2) 입력 : ");

        if (input == 1) {
            if (selectedProduct.getStock() <= 0) {
                System.out.println("!수량이 부족해 장바구니에 추가할 수 없습니다.");
                return State.CATEGORY;
            } else {
                if (cartItems.containsKey(selectedProduct)) {
                    cartItems.put(selectedProduct, cartItems.get(selectedProduct) + 1);
                } else {
                    cartItems.put(selectedProduct, 1);
                }
                System.out.println("\n" + selectedProduct.getName() + "이(가) 장바구니에 추가되었습니다.");
                return State.MAIN;
            }
        }
        if (input == 2) {
            return State.MAIN;
        }

        System.out.println("\n유효하지 않은 번호 입력.");
        return State.CART;
    }

    //주문관리 메뉴
    public State order() {
        System.out.println("\n아래와 같이 주문 하시겠습니다?");
        System.out.println("[ 장바구니 내역 ]");
        int totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product p = entry.getKey();
            int quantity = entry.getValue();

            totalPrice += p.getPrice() * entry.getValue();

            System.out.printf("%s\t | %,12d원 | %s \t | 수량: %d\n",
                    p.getName(),
                    p.getPrice(),
                    p.getEx(),
                    quantity);
        }
        System.out.printf("\n[ 총 주문 금액 ]\n %,d원\n\n", totalPrice);

        int input = getIntInput("1. 주문 확정    2. 메인으로 돌아가기");

        switch (input) {
            case 1:
                System.out.println("주문이 완료되었습니다! 총 금액: " + totalPrice + "원");
                //재고 업데이트
                for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                    Product p = entry.getKey();
                    int quantity = entry.getValue();

                    int newStock = p.getStock() - quantity;
                    System.out.printf("%s\t 재고가 " + p.getStock() + "개 -> " + newStock + "개로 업데이트 되었습니다.\n",
                            p.getName());
                    p.setStock(newStock);
                }
                cartItems.clear();
                break;
            case 2:
                return State.MAIN;
            default:
                System.out.println("유효하지 않은 번호 입력");
        }
        return State.MAIN;
    }
}
