package app;

import app.domain.Todo;
import ch.qos.logback.core.util.DelayStrategy;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.bson.Document.*;

public class App2 {
    public static void main(String[] args) {
        MongoCollection<Todo> collection = Database.getCollection("todo",Todo.class);

//        insertTodo(collection, "수업시간에 집중하기", "딴짓 그만");
//        insertManyTodo(collection, "샘플", 5);
//        selectAllTodo(collection);

//        selectTodo(collection, "68354c75713a5d3531ea2069" );
//        updateTodo(collection, "68354c75713a5d3531ea2069", true);

//        updateAllTodo(collection, true);
//        deleteTodo(collection, "68354c75713a5d3531ea2069");
        deleteAllTodo(collection);
        Database.close();
    }

    private static void insertTodo(
            MongoCollection<Todo> collection, String title, String desc){
        Todo todo = new Todo(null, title, desc, false);

        InsertOneResult result = collection.insertOne(todo);

        System.out.println("todo 추가 성공 ==> _id : " + result.getInsertedId());
    }

    private static void insertManyTodo(MongoCollection<Todo> collection, String str, int count){

        List<Todo> todoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Todo todo = new Todo(null, str + (i + 1), "샘플" + (i + 1), false);
            todoList.add(todo);
        }
        collection.insertMany(todoList);
    }

    private static void selectTodo(MongoCollection<Todo> collection, String id){
        Bson query = eq("_id", new ObjectId(id));
        Todo todo = collection.find(query).first();

        if (todo == null) {
            System.out.println("_id가 일치하는 문서가 없습니다.");
            return;
        }

        System.out.println("_id : "+ todo.getId());
        System.out.println("title : "+ todo.getTitle());
        System.out.println("desc : "+ todo.getDesc());
        System.out.println("done : "+ todo.isDone());
    }

    private static void selectAllTodo(MongoCollection<Todo> collection){
        List<Todo> todoList = new ArrayList<>();
        collection.find().into(todoList);

        todoList.forEach(System.out::println);
    }

    private static void updateTodo(MongoCollection<Todo> collection, String id, boolean done){
        Bson query = eq("_id", new ObjectId(id));
        Bson update = Updates.set("done", done);
        UpdateResult result = collection.updateOne(query, update);
        if (result.getModifiedCount() == 0){
            System.out.println("일치하는 _id를 가지는 문서가 없거나 수정 사항 없음");
            return;
        }
        System.out.println("=== 수정 성공 ===");
        selectTodo(collection, id);
    }

    private static void updateAllTodo( MongoCollection<Todo> collection, boolean done){
        Bson query = Filters.empty();
        Bson update = Updates.set("done", done);
        UpdateResult result = collection.updateMany(query, update);
        if (result.getModifiedCount() == 0){
            System.out.println("변경 사항 없음");
            return;
        }

        System.out.println("=== 일정 수정 성공 ===");
        selectAllTodo(collection);
    }

    private static void deleteTodo(MongoCollection<Todo> collection, String id){
        Bson query = eq("_id", new ObjectId(id));
        DeleteResult result = collection.deleteOne(query);

        if (result.getDeletedCount() == 0){
            System.out.println("일치하는 _id 없음");
            return;
        }
        System.out.println("=== 삭제 성공 ===");
    }

    private static void deleteAllTodo (MongoCollection<Todo> collection){
        Bson query = Filters.empty();
        DeleteResult result = collection.deleteMany(query);

        System.out.println("삭제된 문서의 개수 : " + result.getDeletedCount());
    }

}