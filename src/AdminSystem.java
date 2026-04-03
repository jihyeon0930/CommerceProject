import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminSystem {
    //속성
    private final Scanner sc;
    private final List<Category> categories;

    private Category selectedCategory;

    //생성자
    public AdminSystem(List<Category> categories, Scanner sc) {
        this.categories = categories;
        this.sc = sc;
    }

    public List<Category> getCategories() {
        return categories;
    }


    //기능

    //관리자 모드 패스
    public State adminModePass() {
        String adminPass = "1234";
        int inputCount = 0;

        while (inputCount < 3) {
            System.out.print("관리자 비밀번호 입력 : ");
            String inputAdminPass = sc.nextLine();

            if (adminPass.equals(inputAdminPass)) {
                System.out.println("관리자 인증 성공");
                return State.ADMIN;
            } else {
                inputCount++;
                System.out.println("비밀번호 불잁치. 재입력 필요 (" + inputCount + ")");
            }
        }
        System.out.println("관리자 인증 실패");
        return State.MAIN;
    }

    //관리자 모드 상태
    public State adminMode() {
        System.out.println("[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
        System.out.println("======================================");
        while (true) {
            int adminInput = Utils.getIntInput(sc, "번호 선택 : ");
            switch (adminInput) {
                case 1:
                    return addProduct();
                case 2:
                    return updateProduct();
                case 3:
                    return removeProduct();
                case 4:
                    return showAllProduct();
                case 0:
                    return State.MAIN;
                default:
                    System.out.println("유효하지 않은 번호 입력. 재입력 필요");
            }
        }
    }

    //상품 추가
    public State addProduct() {
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        int index = 1;
        for (Category c : categories) {
            System.out.println(index++ + ". " + c.getCategoryName());
        }
        System.out.println("0. 뒤로가기");

        while (true) {
            int adminInput = Utils.getIntInput(sc, "번호 선택 : ");

            if (adminInput == 0) {
                return State.ADMIN;
            }

            if (adminInput <= categories.size() && adminInput >= 1) {
                selectedCategory = categories.get(adminInput - 1);
                break;
            } else {
                System.out.println("유효하지 않은 번호입니다. 재입력 해주세요");
            }
        }

        System.out.println("\n[ " + selectedCategory.getCategoryName() + "카테고리에 상품 추가 ]");

        String inputNewProductName;
        while (true) {
            System.out.print("상품명: ");
            final String nameToCheck = sc.nextLine();
            if ("0".equals(nameToCheck)) {
                return State.ADMIN;
            }
            boolean isDuplicate = selectedCategory.getProducts().stream()
                    .anyMatch(p -> p.getName().replaceAll("\\s", "").equalsIgnoreCase(nameToCheck.replaceAll("\\s", "")));
            if (!isDuplicate) {
                inputNewProductName = nameToCheck;
                break;
            }
            System.out.println("이미 존재하는 상품명 입니다. 다른 이름을 입력해주세요. (취소: 0 입력)");
        }

        int inputNewProductPrice = Utils.getIntInput(sc, "가격: ");
        System.out.print("설명: ");
        String inputNewProductEx = sc.nextLine();
        int inputNewProductStock = Utils.getIntInput(sc, "재고 수량: ");

        System.out.printf("\n%s | %,d원 | %s | 재고: %d개\n",
                inputNewProductName,
                inputNewProductPrice,
                inputNewProductEx,
                inputNewProductStock
        );

        System.out.println("위 정보로 상품을 추가하시겠습니까?");

        while (true) {
            int input = Utils.getIntInput(sc, "확인(1) or 취소(2) 입력 : ");

            if (input == 1) {
                selectedCategory.getProducts().add(
                        new Product(inputNewProductName, inputNewProductPrice, inputNewProductEx, inputNewProductStock)
                );
                System.out.println("\n상품이 성공적으로 추가되었습니다!");
                return State.ADMIN;
            }
            if (input == 2) {
                System.out.println("\n상품 동록이 취소되었습니다.");
                return State.ADMIN;
            }
            System.out.println("유효하지 않은 번호를 입력하였습니다. 재입력 필요");
        }
    }

    //상품 수정
    public State updateProduct() {
        return State.ADMIN;
    }

    //상품 삭제
    public State removeProduct() {
        return State.ADMIN;
    }

    //전체 상품 현황
    public State showAllProduct() {
        return State.ADMIN;
    }


}
