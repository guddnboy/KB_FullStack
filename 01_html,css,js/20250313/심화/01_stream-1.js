const fs = require('fs');

const rs = fs.createReadStream(`${__dirname}/readMe.txt`, 'utf8');

rs.on('data', (chunk) => {
  console.log('Chunk : ');
  console.log(chunk);
})
  .on('end', () => {
    console.log('읽기 종료');
  })
  .on('error', (error) => {
    console.error(error);
  });
