import order from './pizza_order.mjs';

order('치즈 피자')
  .then((fn) => {
    // fn: resolve()를 통해 반환된 함수
    fn();
  })
  .catch((error) => {
    // err : reject()를 통해 반환된 값(문자열)
    console.log(error);
  });
