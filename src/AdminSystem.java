import java.util.List;
import java.util.Scanner;

public class AdminSystem {
    //속성
    private Scanner sc;
    private List<Category> categoryList;

    //생성자
    public AdminSystem(List<Category> categories, Scanner sc) {
        this.categoryList = categories;
        this.sc = sc;
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
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
        System.out.println("======================================");
        System.out.println("번호 선택 : ");

        return State.MAIN;
    }
}
