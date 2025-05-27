package app.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Todo {
    private ObjectId id;
    private String title;
    private String desc;
    private boolean done;
}