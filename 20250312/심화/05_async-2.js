const order = (status) => {
  return new Promise((resolve, reject) => {
    if (status) {
      resolve('피자를 주문했습니다.');
    } else {
      reject('피자를 주문하지 않습니다.');
    }
  });
};

order(true)
  .then((notice) => {
    console.log(notice);
  })
  .catch((error) => {
    console.log(error);
  });
