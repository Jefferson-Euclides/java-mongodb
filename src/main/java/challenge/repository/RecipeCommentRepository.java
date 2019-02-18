package challenge.repository;

import challenge.models.RecipeComment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeCommentRepository extends MongoRepository<RecipeComment, String> {
}
