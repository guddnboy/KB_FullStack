package sec04;
import static com.mongodb.client.model.Filters.gt;
import app.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

public class UpdateMany {
    public static void main(String[] args) {
        MongoCollection<Document> collection = Database.getCollection("users");
        int age = 16;
        Bson query = gt("age", age);
        Bson updates = Updates.combine(

                Updates.set("name", "modify name"),
                Updates.currentTimestamp("lastUpdated"));

        UpdateResult result = collection.updateMany(query, updates);
        System.out.println("==> UpdateManyResult : " + result.getModifiedCount());
        Database.close();
    }
}