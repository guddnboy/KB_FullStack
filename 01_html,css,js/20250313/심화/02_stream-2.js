const fs = require('fs');
const rs = fs.createReadStream(`${__dirname}/readMe.txt`, 'utf8');
const ws = fs.createWriteStream(`${__dirname}/writeMe.txt`);

rs.pipe(ws);
