var util = require('util')

var logArray = function (items, left, right) {
  util.print('[ ');
  for (i = left; i < right; i++) {
    util.print(items[i], ', ');
  }
  util.print(']\n');
}

var swap = function (items, left, right) {
  var tmp = items[left];
  items[left] = items[right];
  items[right] = tmp;
}

var partition = function (items, beg, end, pivot) {
  var moves = beg;
  for(i = beg; i < end; i++) {
    if (items[i] <= pivot) {
      swap(items, moves, i);
      moves++
    }
  }
  return moves;
}

var qs = function (items, beg, len) {
  if (len <= 1) {
    return;
  }
  var end = len - 1 + beg;
  var pivPos = Math.floor((Math.random() * (end - beg)) + 1) + beg; 
  var pivot = items[pivPos];

  swap(items, pivPos, end);
  moves = partition(items, beg, end, pivot);
  swap(items, moves, end);

  qs(items, beg, moves - beg);
  qs(items, moves+1, end-moves);
}


var testRandom1 = function () {
  var items = [4, 2, 6, 5, 3, 9];
  var sorted = [ 2, 3, 4, 5, 6, 9]
  
  qs(items, 0, items.length);
  for (i = 0; i < items.length; i++) {
    if (items[i] != sorted[i]) {
      console.log('test failed.');
      break;
    }
  }
}

var testRandom2 = function () {
var items = [9, 2, 7, 4, 4, 6, 5]
  var sorted = [ 2, 4, 4, 5, 6, 7, 9];
  
  qs(items, 0, items.length);
  for (i = 0; i < items.length; i++) {
    if (items[i] != sorted[i]) {
      console.log('test failed.');
      break;
    }
  }
}

var test1 = function () {
  var items = [1];
  qs(items, 0, items.length);
  if (items[0] != 1) {
    console.log('test failed.');
  }
}

var test2 = function () {
  var items = [999, 1];
  qs(items, 0, items.length);
  if (items[0] != 1 || items[1] != 999) {
    console.log('test failed.');
  }
}

var test100 = function () {
  var items = [];
  var c = 0;
  for (i = 0; i < 100; i++) {
    items[i] = i;
  } 
  qs(items, 0, items.length);
  for (i = 0; i < 100; i++) {
    if (items[i] != i) {
      console.log('test failed.');
      break;
    }
  }
}

var test100Inverse = function () {
  var items = [];
  var c = 0;
  for (i = 99; i >= 0; i--) {
    items[c++] = i;
  } 
  qs(items, 0, items.length);
  for (i = 0; i < 99; i++) {
    if (items[i] != i) {
      console.log('test failed.');
      break;
    }
  }
}

console.log('Running testRandom1');
testRandom1();

console.log('Running testRandom2');
testRandom2();

console.log('Running test1');
test1();

console.log('Running test2');
test2();

console.log('Running test100');
test100();

console.log('Running test100Inverse');
test100Inverse();