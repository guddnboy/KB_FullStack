const path = require('path');

const result = path.join('some', 'work', 'ex.txt');

console.log(result);

const absoluteDir = path.dirname(__filename);
console.log(`파일 절대 경로: ${absoluteDir}`);

const onlyPath = path.dirname(__dirname);
console.log(`경로만: ${onlyPath}`);

const fileName = path.basename(__filename);
console.log(`파일 이름: ${fileName}`);

const exceptExt = path.basename(__filename, '.js');
console.log(`파일 이름(확장자 제외): ${exceptExt}`);

const extName = path.extname(__filename);
console.log(`파일 확장자: ${extName}`);

const parsePath = path.parse(__filename);
console.log(parsePath);
