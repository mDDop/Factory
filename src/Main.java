import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Factory> listOfFactorys = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu();
        boolean menuCounter = true;
        do {
            MenuPrints.startMenu();
            String chosedOption = scanner.nextLine();
            switch (Integer.valueOf(chosedOption)) {
                case 1: {
                    menu.addFactory(listOfFactorys);
                    continue;
                }
                case 2: {
                    menu.addProduct(listOfFactorys);
                    continue;
                }
                case 3: {
                    menu.showFiveLowAndHigh(listOfFactorys, HighOrLow.TOPFIVEHIGHPRICES);
                    continue;
                }
                case 4: {
                    menu.showFiveLowAndHigh(listOfFactorys, HighOrLow.TOPFIVELOWPRICES);
                    continue;
                }
                case 5: {
                    listOfFactorys = menu.removeFactory(listOfFactorys);
                    continue;
                }
                case 6: {
                    menu.writeToFile(listOfFactorys);
                    continue;
                }
                case 7: {
                    listOfFactorys = menu.readFile(listOfFactorys);
                    continue;
                }
                case 8: {
                    menu.maxProducts(listOfFactorys);
                    continue;
                }
                case 9: {
                    menu.minMaxProducts(listOfFactorys);
                    continue;
                }
                case 10: {
                    menu.printUnbooked(listOfFactorys);
                    continue;
                }
                case 20: {
                    menuCounter = false;
                    break;
                }
            }
        } while (menuCounter);
    }
}
