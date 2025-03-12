const getUsers = () => {
  const request = fetch('https://jsonplaceholder.typicode.com/users')
    .then((data) => data.json())
    .then((res) => {
      console.log(res);
    })
    .catch((err) => console.log(err));
};

getUsers();
