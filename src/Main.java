import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        OwnArrayList<Integer> list = new OwnArrayList<>();
        System.out.println("List 1");
        for (int i = 1; i < 3; i++) {
            list.add(i);
            System.out.println(list.get(i - 1));
        }
        System.out.println("SIZE = " + list.size());


        System.out.println("-------------------------------------");


        OwnArrayList<Integer> list2 = new OwnArrayList<>();
        for (int i = 11; i < 23; i += 11) {
            list2.add(i);
        }
        System.out.println("List 2");
        for (Integer element : list2) {
            System.out.println(element);
        }
        System.out.println("SIZE = " + list2.size());
        System.out.println("-------------------------------------");


        list.addAll(2, list2);


        for (Integer element : list) {
            System.out.println(element);
        }
        System.out.println("size= " + list.size());

    }
}


