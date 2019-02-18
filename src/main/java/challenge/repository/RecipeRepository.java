package challenge.repository;

import challenge.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    List<Recipe> findByIngredientsIn(String ingredient);

    List<Recipe> findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrderByTitleAsc(String search);

}
