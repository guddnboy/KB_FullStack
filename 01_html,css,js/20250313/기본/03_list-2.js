const fs = require('fs');

const dir = fs.readdir('./', (err, dir) => {
  if (err) console.error(err);
  console.log(dir);
});
