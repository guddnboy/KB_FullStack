const order = (coffee, callback) => {
  console.log(`${coffee} 주문 접수`);
  setTimeout(() => {
    callback(coffee);
  }, 3000);
};

const display = (result) => {
  console.log(`주문하신 음료 ${result} 가 나왔습니다.`);
};

order('녹차라떼', display);
