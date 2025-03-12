function displayA() {
  console.log('A');
}

function displayB() {
  // 2000ms 후에 내부 코드를 실행하겠다.
  // [비동기] 그동안 다른 거 하고 있어라!
  setTimeout(() => {
    console.log('B');
  }, 2000);
}

function displayC() {
  console.log('C');
}

displayA();
displayB();
displayC();
