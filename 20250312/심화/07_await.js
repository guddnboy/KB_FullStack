async function init() {
  const request = await fetch('https://jsonplaceholder.typicode.com/users');
  try {
    const data = await request.json();
    console.log(data);
  } catch (err) {
    console.log(err);
  }
}

init();
