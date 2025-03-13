const fs = require('fs');

const content = fs.readFile(
  `${__dirname}/example.txt`,
  'utf-8',
  (err, text) => {
    if (err) console.error(err);
    console.log(text);
  }
);
