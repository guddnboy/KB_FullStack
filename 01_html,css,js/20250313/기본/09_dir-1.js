const fs = require('fs');

if (!fs.existsSync(`${__dirname}/test`)) {
  fs.mkdir(`${__dirname}/test`, (err) => {
    if (err) console.error(err);
  });
}
