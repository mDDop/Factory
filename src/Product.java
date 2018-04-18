import java.util.Comparator;
import java.util.Objects;

public class Product implements Comparable<Product>{
    String name;
    float price;
    String productionDate;
    Department departmentName;
    boolean isBooked;

    public Product(String name, float price, String productionDate, Department departmentName) {
        this.name = name;
        this.price = price;
        this.productionDate = productionDate;
        this.departmentName = departmentName;
        this.isBooked = isBooked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getprice() {
        return price;
    }

    public void setprice(float price) {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public Department getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(Department departmentName) {
        this.departmentName = departmentName;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                isBooked == product.isBooked &&
                Objects.equals(name, product.name) &&
                Objects.equals(productionDate, product.productionDate) &&
                departmentName == product.departmentName;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, productionDate, departmentName, isBooked);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", productionDate='" + productionDate + '\'' +
                ", departmentName=" + departmentName +
                ", isBooked=" + isBooked +
                '}';
    }

    @Override
    public int compareTo(Product o) {
        return -Float.valueOf(price).compareTo(o.getprice());
    }
}
