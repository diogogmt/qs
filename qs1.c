#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void swap (int* items, int left, int right) {
  int tmp = items[left];
  items[left] = items[right];
  items[right] = tmp;
}

int partition (int* items, int beg, int end, int pivot) {
  int moves = beg;
  int i;
  for(i = beg; i < end; i++) {
    if (items[i] <= pivot) {
      swap(items, moves++, i);
    }
  }
  return moves;
}

void qs (int* items, int beg, int len) {
  if (len <= 1) {
    return;
  }
  int end = len - 1 + beg;
  int pivPos = rand() % (end - beg) + beg;
  int pivot = items[pivPos];

  swap(items, pivPos, end);
  int moves = partition(items, beg, end, pivot);
  swap(items, moves, end);

  qs(items, beg, moves - beg);
  qs(items, moves + 1, end - moves);
}


void testRandom1() {
  int items[6] = { 4, 2, 6, 5, 3, 9 };
  int sorted[6] = { 2, 3, 4, 5, 6, 9 };
  
  int length = sizeof(items) / sizeof(int);
  qs(items, 0, length);
  int i;
  for (i = 0; i < length; i++) {
    if (items[i] != sorted[i]) {
      printf("test failed.");
      break;
    }
  }
}

void testRandom2() {
  int items[7] = { 9, 2, 7, 4, 4, 6, 5 };
  int sorted[7] = { 2, 4, 4, 5, 6, 7, 9 };
  
  int length = sizeof(items) / sizeof(int);
  qs(items, 0, length);
  int i;
  for (i = 0; i < length; i++) {
    if (items[i] != sorted[i]) {
      printf("test failed.");
      break;
    }
  }
}

void test1() {
  int items[1] = { 1 };
  int length = sizeof(items) / sizeof(int);
  qs(items, 0, length);
  if (items[0] != 1) {
    printf("test failed.");
  }
}

void test2() {
  int items[2] = { 999, 1 };
  int length = sizeof(items) / sizeof(int);
  qs(items, 0, length);
  if (items[0] != 1 || items[1] != 999) {
    printf("test failed.");
  }
}

void testRand(int size) {
  int* items = (int*)malloc(sizeof(int) * size);
  int i, c;
  for (i = 0; i < size; i++) {
    items[i] = rand();
  } 

  int length = size;

  clock_t begin, end;
  double time_spent;
  begin = clock();
  qs(items, 0, length);
  end = clock();

  printf("Time takend in sec %lf\n", (double)(end - begin) / CLOCKS_PER_SEC);

  // for (i = 0; i < size; i++) {
  //   printf("%d\n", items[i]);
  // }
}

void testOrd(int size) {
  int* items = (int*)malloc(sizeof(int) * size);
  int i, c;
  for (i = 0; i < size; i++) {
    items[i] = i;
  } 

  int length = size;

  clock_t begin, end;
  double time_spent;
  begin = clock();
  qs(items, 0, length);
  end = clock();

  printf("Time takend in sec %lf\n", (double)(end - begin) / CLOCKS_PER_SEC);

  for (i = 0; i < size; i++) {
    if (items[i] != i) {
      printf("test failed.\n");
      break;
    }
  }
}

void testRev(int size) {
  int* items = (int*)malloc(sizeof(int) * size);
  int i, c;
  for (i = size - 1, c = 0; c < size; i--, c++) {
    items[c] = i;
  } 

  int length = size;

  clock_t begin, end;
  double time_spent;
  begin = clock();
  qs(items, 0, length);
  end = clock();

  printf("Time takend in sec %lf\n", (double)(end - begin) / CLOCKS_PER_SEC);

  for (i = 0; i < size; i++) {
    if (items[i] != i) {
      printf("test failed.\n");
      break;
    }
  }
}



int main() {
  // printf("Running testRandom1\n");
  // testRandom1();

  // printf("Running testRandom2\n");
  // testRandom2();

  // printf("Running test1\n");
  // test1();

  // printf("Running test2\n");
  // test2();

  // Rand test
  // printf("Running testRand x1\n");
  // testRand(10);

  // printf("Running testRand x2\n");
  // testRand(100);

  // printf("Running testRand x3\n");
  // testRand(1000);

  // printf("Running testRand x4\n");
  // testRand(10000);

  // printf("Running testRand x5\n");
  // testRand(100000);

  // printf("Running testRand x6\n");
  // testRand(1000000);

  // printf("Running testRand x7\n");
  // testRand(10000000);

  // printf("Running testRand x8\n");
  // testRand(100000000);

  // printf("Running testRand x9\n");
  // testRand(1000000000);


  // Ord test
  // printf("Running testOrd x1\n");
  // testOrd(10);

  // printf("Running testOrd x2\n");
  // testOrd(100);

  // printf("Running testOrd x3\n");
  // testOrd(1000);

  // printf("Running testOrd x4\n");
  // testOrd(10000);

  // printf("Running testOrd x5\n");
  // testOrd(100000);

  // printf("Running testOrd x6\n");
  // testOrd(1000000);

  // printf("Running testOrd x7\n");
  // testOrd(10000000);

  // printf("Running testOrd x8\n");
  // testOrd(100000000);

  // printf("Running testOrd x9\n");
  // testOrd(1000000000);



  // Reverse test
  // printf("Running testRev x1\n");
  // testRev(10);

  // printf("Running testRev x2\n");
  // testRev(100);

  // printf("Running testRev x3\n");
  // testRev(1000);

  // printf("Running testRev x4\n");
  // testRev(10000);

  // printf("Running testRev x5\n");
  // testRev(100000);

  // printf("Running testRev x6\n");
  // testRev(1000000);

  // printf("Running testRev x7\n");
  // testRev(10000000);

  // printf("Running testRev x8\n");
  // testRev(100000000);

  printf("Running testRev x9\n");
  testRev(1000000000);

  return 0;
}
