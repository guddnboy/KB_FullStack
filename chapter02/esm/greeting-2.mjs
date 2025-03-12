/* export default
- 내보내지는 변수 | 함수의 기본값을 설정
== 외부에서 import 시 가져오는 변수 | 함수가 고정되어 있음
*/

const hi = (name) => {
  console.log(`${name}님, 안녕하세요?`);
};

const goodbye = (name) => {
  console.log(`${name}님, 안녕히 가세요.`);
};

export default { hi, goodbye };
