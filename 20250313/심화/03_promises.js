const fs = require('fs').promises;

fs.readdir(__dirname)
  .then((data) => {
    console.log(data);
  })
  .catch((err) => {
    console.error(err);
  });
