import javax.print.attribute.standard.PDLOverrideSupported;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Factory implements Comparable<Factory>, Comparator<Factory> {
    String name;
    String idOfFactory;
    List<Product> products;


    public Factory(String name, String idOfFactory) {
        this.name = name;
        this.idOfFactory = idOfFactory;
        products = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdOfFactory() {
        return idOfFactory;
    }

    public void setIdOfFactory(String idOfFactory) {
        this.idOfFactory = idOfFactory;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getProductsNumber() {
        return products.size();
    }

    @Override
    public int compare(Factory o1, Factory o2) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factory factory = (Factory) o;
        return Objects.equals(name, factory.name) &&
                Objects.equals(idOfFactory, factory.idOfFactory) &&
                Objects.equals(products, factory.products);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, idOfFactory, products);
    }

    @Override
    public String toString() {
        return "Factory{" +
                "name='" + name + '\'' +
                ", idOfFactory='" + idOfFactory + '\'' +
                ", products=" + products +
                '}';
    }
    public String toWrite() {
        StringBuilder productsString = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            productsString.append(products.get(i).getName());
            productsString.append("<;>");
            productsString.append(products.get(i).getprice());
            productsString.append("<;>");
            productsString.append(products.get(i).getProductionDate());
            productsString.append("<;>");
            productsString.append(products.get(i).getDepartmentName());
            productsString.append("<;>");
            productsString.append(products.get(i).isBooked());
            productsString.append("<;>");

        }
        return ("" + name + "</>" + idOfFactory + "</>" + productsString + "<:>");
    }

    protected void addProduct(Product productToAdd) {
        products.add(productToAdd);
    }

    @Override
    public int compareTo(Factory o) {
        return -Integer.valueOf(products.size()).compareTo(o.getProductsNumber());
    }

    public static class topHighestPrice implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return -Float.valueOf(o1.getprice()).compareTo(Float.valueOf(o2.getprice()));
        }
    }
    public static class topLowestPrice implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return Float.valueOf(o1.getprice()).compareTo(Float.valueOf(o2.getprice()));
        }
    }
}

