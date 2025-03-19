let p = new Promise((resolve, reject) => {
  resolve('first');
});

p.then((msg) => {
  console.log(msg);
  return 'second';
})
  .then((msg) => {
    console.log(msg);
    console.log('third');
  })
  .catch((error) => {
    console.log('오류 발생 ==>  ' + error);
  });
