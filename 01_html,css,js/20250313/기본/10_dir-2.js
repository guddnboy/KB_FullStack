const fs = require('fs');

if (!fs.existsSync(`${__dirname}/test2/test3/test4`)) {
  fs.mkdir(`${__dirname}/test2/test3/test4`, { recursive: true }, (err) => {
    if (err) console.error(err);
  });
}
