import java.util.ArrayList;
import java.util.List;

public class Category { //Product 클래스 관리
    //속성(필드)
    private String categoryName; //카테고리 이름
    private List<Product> products; //해당 카테고리에 속한 상품들

    //생성자
    public Category(String categoryName, List<Product> products) {
        this.categoryName = categoryName;
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    //기능

}
