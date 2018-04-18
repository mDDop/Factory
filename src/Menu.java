import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    String nameOfFactoryInput;
    String idOfFactoryInput;

    public void addFactory(List<Factory> listOfFactorys){
        System.out.println("Podaj Nazwę fabryki: ");
        nameOfFactoryInput = scanner.nextLine();
        System.out.println("Podaj ID fabryki: ");
        idOfFactoryInput = scanner.nextLine();

        listOfFactorys.add(new Factory(nameOfFactoryInput, idOfFactoryInput));
    }
    public void addProduct(List<Factory> listOfFactorys){
        System.out.println("Do wyboru masz następujące fabryki: ");
        listOfFactorys.stream().forEach(s -> System.out.println(s.getName()));
        System.out.println("Podaj indeks fabryki do której dodać: ");
        nameOfFactoryInput = scanner.nextLine();
        System.out.println("Podaj nazwę, cenę, datę produkcji, TYP oddzielając je Enterem");
        listOfFactorys.get(Integer.valueOf(nameOfFactoryInput)).addProduct(new Product(scanner.nextLine(), Float.valueOf(scanner.nextLine()), scanner.nextLine(), Department.valueOf(scanner.nextLine())));

    }

    public void minMaxProducts(List<Factory> listOfFac){
        List<Product> maxListProduct = new ArrayList<>();
        for (int i = 0; i < listOfFac.size(); i++) {
            maxListProduct.addAll(listOfFac.get(i).products);
        }
        Collections.sort(maxListProduct);
        System.out.println("5 Najdroższych: ");
        maxListProduct.stream()
                .limit(5)
                .forEach(s -> System.out.println(s.getName() + " : " + s.getprice()));
        System.out.println("5 Najtańszych: ");
        maxListProduct.stream()
                .sorted((s, s1) -> Float.valueOf(s.getprice()).compareTo(s1.getprice()))
                .limit(5)
                .forEach(s -> System.out.println(s));
    }

    public void showFiveLowAndHigh(List<Factory> listOfFactorys, HighOrLow highOrLowEnum) {
        if (listOfFactorys.size() > 0) {
            System.out.println("Do wyboru masz następujące fabryki: ");
            for (int i = 0; i < listOfFactorys.size(); i++) {
                System.out.println(listOfFactorys.get(i).getName());
            }
            System.out.println("Podaj nazwę fabryki dla której wyświetlić: ");
            nameOfFactoryInput = scanner.nextLine();
            int counter = -1;
            for (int i = 0; i < listOfFactorys.size(); i++) {
                if (listOfFactorys.get(i).getName().equals(nameOfFactoryInput)) {
                    counter = i;
                }
            }
            if (counter >= 0) {
                List<Product> topFiveHigh = listOfFactorys.get(counter).products;
                if (highOrLowEnum.isHigh()) {
                    Collections.sort(topFiveHigh, new Factory.topHighestPrice());
                } else {
                    Collections.sort(topFiveHigh, new Factory.topLowestPrice());
                }
                for (Product product : topFiveHigh) {
                    System.out.println(product);
                }
            }
        } else {
            System.out.println("Brak dodanych magazynów");
        }
    }

    public List<Factory> removeFactory (List<Factory> listOfFactorys) {
        if (listOfFactorys.size() > 0) {
            System.out.println("Podaj Nazwę fabryki: ");
            nameOfFactoryInput = scanner.nextLine();
            listOfFactorys.removeIf(s -> s.getName().equals(nameOfFactoryInput));
        } else {
            System.out.println("Brak dodanych magazynów");
        }
        return listOfFactorys;
    }

    public List<Factory> readFile(List<Factory> listOfFactorys) {
        File file = new File("C:\\Users\\mddop\\Desktop\\AK\\FactoryData.txt");
        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int read;
            while ((read = fileInputStream.read()) != -1) {
                stringBuilder.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<String> inputListSplit;
            inputListSplit = Arrays.asList(stringBuilder.toString().split("<:>"));
            for (int i = 0; i < inputListSplit.size(); i++) {
                String[] nFactoryInput = inputListSplit.get(i).split("</>");
                if (nFactoryInput.length > 1) {
                    listOfFactorys.add(new Factory(nFactoryInput[0].toString(), nFactoryInput[1].toString()));
                    if (nFactoryInput.length > 2) {
                        String[] nProducts = nFactoryInput[2].split("<;>");
                        for (int j = 0; j < nProducts.length / 5; j++) {
                            listOfFactorys.get(i).addProduct(new Product(nProducts[j * 5].toString(), Float.valueOf(nProducts[j * 5 + 1]),
                                    nProducts[j * 5 + 2].toString(), Department.valueOf(nProducts[j * 5 + 3])));
                        }
                    }
                }
            }
        }
        return listOfFactorys;
    }
    public void writeToFile(List<Factory> listOfFactorys) {
        File file = new File("C:\\Users\\mddop\\Desktop\\AK\\FactoryData.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("".getBytes());
            fileOutputStream = new FileOutputStream(file, true);
            for (int i = 0; i < listOfFactorys.size(); i++) {
                fileOutputStream.write(listOfFactorys.get(i).toWrite().getBytes());
                fileOutputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void maxProducts(List<Factory> listOfFactorys) {
        if (listOfFactorys.size()>0) {
            Collections.sort(listOfFactorys);
            System.out.println(listOfFactorys.get(0).getName() + " produktów: " + listOfFactorys.get(0).getProductsNumber());
        }
    }

    public void printUnbooked(List<Factory> listOfFactorys) {
        List<Product> maxListProduct = new ArrayList<>();
        for (int i = 0; i < listOfFactorys.size(); i++) {
            maxListProduct.addAll(listOfFactorys.get(i).products);
        }
        maxListProduct.stream()
                .filter(s-> s.isBooked==false)
                .forEach(s -> System.out.println(s.getName() + " : " + s.getprice()));
    }
}
