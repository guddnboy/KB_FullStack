const c = require('ansi-colors');

const hello = (name) => {
  console.log(`${c.greenBright(name)} 님, 안녕하세요?`);
};

hello('홍길동');
