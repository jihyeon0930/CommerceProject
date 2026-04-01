import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem { //커머스 플랫폼 상품 관리, 사용자 입력 처리
    //속성(필드) - Product 객체들 관리하는 리스트 -> 카테고리 관리하는 클래스로 변경!!
    private List<Category> categories;
    private List<Product> cartItems = new ArrayList<>();

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

    //MAIN 상태
    public State showMain() {
        System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");
        System.out.println("0. 종료\t\t\t | 프로그램 종료");
        System.out.println("======================================");
        System.out.print("번호 선택 : ");
        try {
            int mainInput = Integer.parseInt(sc.nextLine());
            if (mainInput == 0) {
                return State.EXIT;
            }
            if (mainInput >= 1 && mainInput <= categories.size()) {
                selectedCategory = categories.get(mainInput - 1);
                return State.CATEGORY;
            }
            System.out.println("\n잘못된 입력 입니다.");
            return State.MAIN;
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력 입니다. 숫자를 입력해 주세요");
            return State.MAIN;
        }
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
        System.out.print("상세보가 할 상품 번호 선택 : ");
        int input = Integer.parseInt(sc.nextLine());
        try {
            input = sc.nextInt();
            if (input == 0) {
                return State.EXIT;
            }
            if (input > 0 && input < selectedCategory.getProducts().size()) {
                selectedProduct = selectedCategory.getProducts().get(input - 1);
                System.out.printf("\n선택한 상품: %s\t | %,12d원 | %s \t | 재고: %d\n",
                        selectedProduct.getName(),
                        selectedProduct.getPrice(),
                        selectedProduct.getEx(),
                        selectedProduct.getStock());
                return State.CART;
            } else {
                System.out.println("\n잘못된 입력입니다.");
                return State.PRODUCT;
            }
        } catch (InputMismatchException e) {
            System.out.println("\n잘못된 입력 입니다. 숫자를 입력해 주세요");
            return State.PRODUCT;
        }
    }

    //CART 상태 (장바구니)
    public State cart() {
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.print("확인(1) or 취소(2) 입력 : ");

        try {
            int input = Integer.parseInt(sc.nextLine());

            if (input == 1) {
                cartItems.add(selectedProduct);
                System.out.println("\n" + selectedProduct.getName() + "이(가) 장바구니에 추가되었습니다.");

                return State.MAIN;
            }

            if (input == 2) {
                return State.MAIN;
            }
        } catch (InputMismatchException e) {
            System.out.println("\n잘못된 입력입니다. 숫자를 입력해주세요.");
            return State.CART;
        }
        System.out.println("\n잘못된 입력입니다.");
        return State.CART;
    }

}
