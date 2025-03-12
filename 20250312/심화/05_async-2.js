const pizza = new Promise((resolve, reject) => {
  let likePizza = true;

  if (likePizza) {
    resolve('피자를 주문 했습니다.');
  } else {
    reject('피자를 주문하지 않습니다.');
  }
});

pizza
  .then((message) => {
    console.log(message);
  })
  .catch((err) => {
    console.error(err);
  });
