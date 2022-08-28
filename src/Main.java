import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
/*
Дана база данных о продажах некоторого интернет-магазина. Каждая строка входных данных представляет собой запись вида
Покупатель товар количество, где Покупатель — имя покупателя (строка без пробелов),
товар — название товара (строка без пробелов),
количество — количество приобретенных единиц товара.

Создайте список всех покупателей, а для каждого покупателя подсчитайте количество приобретенных им единиц каждого вида товаров.

Входные данные
Вводятся сведения о покупках в указанном формате.

Выходные данные
Выведите список всех покупателей в лексикографическом порядке, после имени каждого покупателя выведите двоеточие,
затем выведите список названий всех приобретенных данным покупателем товаров в лексикографическом порядке,
после названия каждого товара выведите количество единиц товара, приобретенных данным покупателем.
Информация о каждом товаре выводится в отдельной строке.
 */
public class Main {

    public static void main(String[] args) {
        class Product {

            public String productName;
            public int quantity;

            public Product(String productName, Integer quantity) {
                this.productName = productName;
                this.quantity = quantity;
            }
        }
        class User {
            public String customer;
            public TreeSet<Product> productList;//=new TreeSet<>();

            public    User(String customer) {
                this.customer = customer;
            }
        }




        class UserNameComparator implements Comparator<User> {
            public int compare(User a, User b) {
                return a.customer.compareTo(b.customer);
            }
        }

        class ProductNameComparator implements Comparator<Product> {
            public int compare(Product a, Product b) {
                return a.productName.compareTo(b.productName);
            }
        }

        Comparator<User> uncomp = new UserNameComparator();
        Comparator<Product> prcomp = new ProductNameComparator();
        TreeSet<User> tree = new TreeSet<>(uncomp);

        Scanner scan = new Scanner(System.in);
        Boolean b = true;
        String input;
        List<String> s = new ArrayList<>();
        while (b) {
            input=scan.nextLine();
            if (input == null || "".equals(input)){
                break;}
            s.add(input);
        }

//        Main main=new Main();
        boolean needToAdd = false;
        for (String q : s) {
            String cusu = q.split(" ")[0];
            String prod = q.split(" ")[1];
            Integer quant = Integer.parseInt(q.split(" ")[2]);

            Product p = new Product(prod, quant);

            Iterator<User> iter = tree.iterator();

            boolean hasn;
            while (hasn = iter.hasNext()) {
                User myu = iter.next();
                if (myu.customer.equals(cusu)) {


                    Iterator<Product> iterProd = myu.productList.iterator();
                    boolean hasprod;
                    while (hasprod = iterProd.hasNext()) {
                        Product myproduct = iterProd.next();


                        if (myproduct.productName.equals(p.productName)) {
                            myproduct.quantity += p.quantity;

                            hasprod = true;
                        } else {
                            hasprod = false;
                        }
                    }
                    if (!hasprod) {
                        myu.productList.add(p);

                    }

                    if (myu.productList.size() < 1) {
                        myu.productList.add(p);

                    }
                } else {
                    needToAdd = true;

                }
            }

            if ((needToAdd) || !hasn) {

                String cust = q.split(" ")[0];
                String pro = q.split(" ")[1];
                Integer quan = Integer.parseInt(q.split(" ")[2]);
                Product pq = new Product(pro, quan);


                User uu1 = new User(cust);
                uu1.productList = new TreeSet<>(prcomp);

                uu1.productList.add(pq);
                tree.add(uu1);
                needToAdd = false;
            }

        }


        for (User suser : tree) {
            System.out.println(suser.customer + ":");
            for (Product op : suser.productList) {
                System.out.println(op.productName + " " + op.quantity);
            }
        }

    }
}