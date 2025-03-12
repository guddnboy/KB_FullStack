async function init() {
  // [비동기 동작] fetch(주소) : 외부로 부터 응답을 가져옴
  // await : 비동기 응답이 처리 될 때까지 대기 -> 동기로 변경
  const response = await fetch('https://jsonplaceholder.typicode.com/users');

  // response.ok : 응답 성공 시 true, 실패 시 false
  console.log(response.ok);
  if (response.ok) {
    const result = await response.json();
    result.forEach((element) => {
      console.log(element.name);
    });
  } else {
  }

  console.log('프로그램 종료');
}

init();
