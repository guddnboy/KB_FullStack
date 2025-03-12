function pizzaOrder(pizzaName) {
  const pizzaList = ['치즈 피자', '불고기 피자', '페퍼로니 피자'];

  // Promise 객체 : 비동기 코드 수행 후 결과 반환을 약속하는 객체
  // Closer가 사용되었음
  return new Promise((resolve, reject) => {
    if (pizzaList.includes(pizzaName)) {
      // Promise 성공 시 함수 반환
      resolve(() => {
        console.log(`${pizzaName} 주문 성공!`);

        setTimeout(() => {
          console.log(`${pizzaName} 배달을 시작했습니다!`);
        }, 3000);
      });
    } else {
      // Promise 실패 시 '값' 반환
      reject(`주문 실패. ${pizzaName}은 존재하지 않습니다.);
      }`);
    }
  });
}

export default pizzaOrder;
