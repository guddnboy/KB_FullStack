const fs = require('fs');

const data = fs.readFileSync(`${__dirname}/example.txt`, 'utf-8');

fs.writeFile(`${__dirname}/example-1.txt`, data, (err) => {
  if (err) console.error(err);
});
