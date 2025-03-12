// 외부에서 해당 모듈의 hi, goodbye를 import 가능하게 함.
const hi = (name) => {
  console.log(`${name}님, 안녕하세요?`);
};

const goodbye = (name) => {
  console.log(`${name}님, 안녕히 가세요.`);
};

// export { hi, goodbye };
