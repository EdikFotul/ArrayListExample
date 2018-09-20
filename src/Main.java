import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        OwnArrayList<Integer> list = new OwnArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
            System.out.println(list.get(i));
        }

        Iterator<Integer> iterator =  list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }


//        list.add(1,100);
//        list.add(5, 100);
//
//
//        System.out.println("index of 100 = " + list.indexOf(100));
//        System.out.println("SIZE After Add = " + list.size());
//        for (int i = 0; i < 11; i++) {
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("Last index of 100 = " + list.lastIndexOf(100));
////
////        list.remove(1);
//
//          System.out.println("--------------------------");
////        System.out.println("SIZE After Remove = " + list.size());
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println(list.get(i));
//        }



    }
}
