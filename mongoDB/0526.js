use my_database
use tutorial

db.users.insertOne({username: "smith"})
db.users.insertOne({username: "jones"})

db.users.find({})

db.users.findOne({})


db.users.find({username: "jones"})

db.users.find({$or: [{username: "smith"}, {username: "jones"}]})

db.users.updateOne({username: "jones"}, {$set: {username: "johnson", country: "Canada"}})

db.users.replaceOne({username: "smith"}, {username: "smith", country: "Canada"})

db.users.replaceOne({username: "smith"}, { country: "Canada"})

db.users.updateMany({country: "Canada"}, {$set: {username : "smith", country: "Canada"}})

db.users.updateMany({username: "smith"}, {$unset: {country: 1}})

show dbs

show collections

db.stats()
db.users.stats()

db.users.deleteOne({username: "smith"})
db.users.find({})

db.users.deleteMany({})

db.users.drop()

// 쿼리 작성하기
use test

const documents = [];
for(let i = 0; i < 20000; i++) {
    documents.push({num: i, name: '스마트폰' + i});
}
db.product.insertMany(documents);
db.product.countDocuments()

db.product.createIndex({num: -1})

db.product.find().sort({num: -1})
db.product.find().sort({num: -1}).limit(10)

db.product.find()
    .sort({num: -1})
    .skip(50)
    .limit(10)

db.product.find({num:{$lt: 15}, num: {$gt: 19995}})

db.product.find({
    $or: [
        {name: "스마트폰10"},
        {name: "스마트폰100"},
        {name: "스마트폰1000"}
    ]
}).pretty()

db.product.find(
    {num: {$lt: 5}},
    {name: 1, _id: 0}
)