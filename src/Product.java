public class Product {
    //속성 (필드)
    private String name;
    private int price;
    private String ex;
    private int stock;

    //생성자 -> 값 셋팅
    public Product(String name, int price, String ex, int stock) {
        this.name = name;
        this.price = price;
        this.ex = ex;
        this.stock = stock;
    }

    //getter : 생성자로 값 세팅 (setter은 생성 후 값 변경시 필요-재고 수량 변동등..)
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getEx() {
        return ex;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
