function displayA() {
  console.log('A');
}
function displayB(callback) {
  console.log('<<2초 경과 후>>');

  setTimeout(() => {
    console.log('B');
    callback();
  }, 2000);
}
function displayC() {
  console.log('C');
}

displayA();
displayB(displayC);
