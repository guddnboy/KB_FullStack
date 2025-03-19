async function init() {
  try {
    const request = await fetch('https://jsonplaceholder.typicode.com/users');
    const data = await request.json();
    console.log(data);
  } catch (err) {
    console.log(err);
  }
}

init();
