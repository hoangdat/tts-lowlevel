/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package XML_Processing;

/**
 *
 * @author thaodv-bkit
 */
/*
  Sort Java ArrayList in descending order using comparator example
  This java example shows how to sort elements of Java ArrayList in descending order
  using comparator and reverseOrder method of Collections class.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortArrayListInDecesdingOrder {

  public static void main(String[] args) {

    //create an ArrayList object
    ArrayList arrayList = new ArrayList();

    //Add elements to Arraylist
    arrayList.add("A");
    arrayList.add("D");
    arrayList.add("C");
    arrayList.add("B");
    arrayList.add("E");

    /*
      To get comparator that imposes reverse order on a Collection use
      static Comparator reverseOrder() method of Collections class
    */

    //Comparator comparator = Collections.reverseOrder();

    System.out.println("Before sorting ArrayList in descending order : "
                                                              + arrayList);

    /*
      To sort an ArrayList using comparator use,
      static void sort(List list, Comparator c) method of Collections class.
    */

    Collections.sort(arrayList);
    System.out.println("After sorting ArrayList in descending order : "
                                                              + arrayList);

  }
}

/*
Output would be
Before sorting ArrayList in descending order : [A, B, C, D, E]
After sorting ArrayList in descending order : [E, D, C, B, A]
*/
