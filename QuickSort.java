import java.util.*;

public class QuickSort {

  static private Random generator = new Random();

  static public void swap(int[] items, int left, int right) {
    int tmp = items[left];
    items[left] = items[right];
    items[right] = tmp;
  }

  /*
    @items - array of integers
    @beg - index position pointing to the beginning of the array being partitioned
    @end - end position of the array being partitioned
    @pivot - selected pivot for the iteration

    @moves - pointer tracking items to be swapped
  */
  static public int partition(int[] items, int beg, int end, int pivot) {
    /* assign @beg to moves to save the state of how many items were already partioned */
    int moves = beg;
    int i;
    for(i = beg; i < end; i++) {
      if (items[i] <= pivot) {
        swap(items, moves++, i);
      }
    }
    return moves;
  }

  /*
    @items - array of integers
    @beg - index position pointing to the beginning of the array being sorted
    @len - length of the array being sorted

    @end - index of the last element of the array
    @pivtoPos - index position of the select pivot of the array
    @pivot - value of the pivot used by the algorithm
  */
  static public void qs(int[] items, int beg, int len) {
    if (len <= 1) return;

    /* Besides subtracting 1 from len we also add beg since beg could have different values than just 0
    */
    int end = len - 1 + beg;

    /* First subtract beg from end to find how many items are being sorted
        Second add 1 to the random result the random number generator goes from 0-n
        Third add beg to the result to adjust to the correct position of the array
    */
    int pivPos = (generator.nextInt(end - beg) + 1) + beg;
    int pivot = items[pivPos];


    /* Move the pivot to the end of the array */
    swap(items, pivPos, end);
    int moves = partition(items, beg, end, pivot);
    /* Moves the pivot to the middle of the partition */
    swap(items, moves, end);

    /* Recursive call moving towards the left side of the array */
    qs(items, beg, moves - beg);
    /* Recursive call moving towards the right side of the array
       One is added to the number of moves since @beg should represent the first element in the new array
      */
    qs(items, moves + 1, end - moves);
  }

  static public void testRandom1() {
    int items[] = new int[] { 4, 2, 6, 5, 3, 9 };
    int sorted[] = new int[] { 2, 3, 4, 5, 6, 9 };
    
    int length = items.length;
    qs(items, 0, length);
    int i;
    for (i = 0; i < length; i++) {
      if (items[i] != sorted[i]) {
        System.out.println("test failed.");
        break;
      }
    }
  }

  static public void testRandom2() {
    int items[] = new int[] { 9, 2, 7, 4, 4, 6, 5 };
    int sorted[] = new int[]  { 2, 4, 4, 5, 6, 7, 9 };
    
    int length = items.length;
    long begin = System.currentTimeMillis();
    qs(items, 0, length);
    System.out.println("Time taken in milli " + (System.currentTimeMillis() - begin));
    int i;
    for (i = 0; i < length; i++) {
      if (items[i] != sorted[i]) {
        System.out.println("test failed.");
        break;
      }
    }
  }

  static public void test1() {
    int items[] = new int[] { 1 };
    int length = items.length;
    qs(items, 0, length);
    if (items[0] != 1) {
      System.out.println("test failed.");
    }
  }

  static public void test2() {
    int items[] = new int[] { 999, 1 };
    int length = items.length;
    qs(items, 0, length);
    if (items[0] != 1 || items[1] != 999) {
      System.out.println("test failed.");
    }
  }

  static public void testRand(int SIZE) {
    int items[] = new int[SIZE];
    int i;
    for (i = 0; i < SIZE; i++) {
      items[i] = generator.nextInt();
      // System.out.println("items[i]: " + items[i]);
    } 

    int length = SIZE;
    long begin = System.currentTimeMillis();
    qs(items, 0, length);
    System.out.println("Time taken in milli " + (System.currentTimeMillis() - begin));
    System.out.println("Time taken in sec " + (System.currentTimeMillis() - begin) / 1000);

    // for (i = 0; i < SIZE; i++) {
    //   System.out.println(items[i]);
    // }
  }

  static public void testOrd(int SIZE) {
    int items[] = new int[SIZE];
    int i;
    for (i = 0; i < SIZE; i++) {
      items[i] = i;
    } 

    int length = SIZE;
    long begin = System.currentTimeMillis();
    qs(items, 0, length);
    System.out.println("Time taken in milli " + (System.currentTimeMillis() - begin));
    System.out.println("Time taken in sec " + (System.currentTimeMillis() - begin) / 1000);

    for (i = 0; i < SIZE; i++) {
      if (items[i] != i) {
        System.out.println("test failed.\n");
        break;
      }
    }
  }

  static public void testRev(int SIZE) {
    int items[] = new int[SIZE];
    int i, c;
    for (i = SIZE-1, c = 0; c < SIZE; i--, c++) {
      items[c] = i;
      // System.out.print(items[c] + ", ");
    } 

    int length = SIZE;
    long begin = System.currentTimeMillis();
    qs(items, 0, length);
    System.out.println("Time taken in milli " + (System.currentTimeMillis() - begin));
    System.out.println("Time taken in sec " + (System.currentTimeMillis() - begin) / 1000);

    begin = System.currentTimeMillis();
    Arrays.sort(items);
    System.out.println("Time taken in milli " + (System.currentTimeMillis() - begin));
    System.out.println("Time taken in sec " + (System.currentTimeMillis() - begin) / 1000);
    for (i = 0; i < SIZE; i++) {
      // System.out.print(items[i] + ", ");
      if (items[i] != i) {
        System.out.println("test failed.\n");
        break;
      }
    }
  }

  static public void main(String args[])  throws Exception {

    System.out.println("Running testRandom1\n");
    testRandom1();

    System.out.println("Running testRandom2\n");
    testRandom2();

    System.out.println("Running test1\n");
    test1();

    System.out.println("Running test2\n");
    test2();


    // Rand test
    System.out.println("Running testRand x1\n");
    testRand(10);

    System.out.println("Running testRand x2\n");
    testRand(100);

    System.out.println("Running testRand x3\n");
    testRand(1000);

    System.out.println("Running testRand x4\n");
    testRand(10000);

    System.out.println("Running testRand x5\n");
    testRand(100000);

    System.out.println("Running testRand x6\n");
    testRand(100000);

    System.out.println("Running testRand x7\n");
    testRand(1000000);

    System.out.println("Running testRand x8\n");
    testRand(10000000);

    System.out.println("Running testRand x9\n");
    testRand(100000000);

    // Need to increase heap space
    System.out.println("Running testRand x10\n");
    testRand(1000000000);


    // Ord test
    System.out.println("\n\nRunning testOrd x1");
    testOrd(10);

    System.out.println("\n\nRunning testOrd x2");
    testOrd(100);

    System.out.println("\n\nRunning testOrd x3");
    testOrd(1000);

    System.out.println("\n\nRunning testOrd x4");
    testOrd(10000);

    System.out.println("\n\nRunning testOrd x5");
    testOrd(100000);

    System.out.println("\n\nRunning testOrd x6");
    testOrd(100000);

    System.out.println("\n\nRunning testOrd x7");
    testOrd(1000000);

    System.out.println("\n\nRunning testOrd x8");
    testOrd(10000000);

    System.out.println("\n\nRunning testOrd x9");
    testOrd(100000000);

    // // Need to increase heap space
    System.out.println("Running testOrd x10");
    testOrd(1000000000);



    // Rev test
    System.out.println("Running testRev x1\n");
    testRev(10);

    System.out.println("Running testRev x2\n");
    testRev(100);

    System.out.println("Running testRev x3\n");
    testRev(1000);

    System.out.println("Running testRev x4\n");
    testRev(10000);

    System.out.println("Running testRev x5\n");
    testRev(100000);

    System.out.println("Running testRev x6\n");
    testRev(100000);

    System.out.println("Running testRev x7\n");
    testRev(1000000);

    System.out.println("Running testRev x8\n");
    testRev(10000000);

    System.out.println("Running testRev x9\n");
    testRev(100000000);

    // // Need to increase heap space
    System.out.println("Running testRev x10\n");
    testRev(1000000000);
  }
}
