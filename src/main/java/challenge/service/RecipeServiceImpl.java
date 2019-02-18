package challenge.service;

import challenge.models.Recipe;
import challenge.models.RecipeComment;
import challenge.repository.RecipeCommentRepository;
import challenge.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	RecipeRepository repository;

	@Autowired
	RecipeCommentRepository recipeCommentRepository;

	@Override
	public Recipe save(Recipe recipe) {
		return repository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		recipe.setId(id);
		repository.save(recipe);
	}

	@Override
	public void delete(String id) {
		repository.deleteById(id);
	}

	@Override
	public Recipe get(String id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return repository.findByIngredientsIn(ingredient);
	}

	@Override
	public List<Recipe> search(String search) {
		return repository.findByTitleIgnoreCaseContainingOrDescriptionIgnoreCaseContainingOrderByTitleAsc(search);
	}

	@Override
	public void like(String id, String userId) {
		Recipe recipe = repository.findById(id).get();

		if (recipe.getLikes() == null) {
			recipe.setLikes(new ArrayList<String>());
		}
		recipe.getLikes().add(userId);

		repository.save(recipe);
	}

	@Override
	public void unlike(String id, String userId) {
		Optional<Recipe> recipe = repository.findById(id);

		if (recipe.isPresent()) {
			Recipe aux = recipe.get();

			if (aux.getLikes().contains(userId)) {
				aux.getLikes().remove(userId);
				repository.save(aux);
			}
		}
	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		RecipeComment recipeComment = recipeCommentRepository.save(comment);

		Recipe recipe = repository.findById(id).get();

		if (recipe.getComments() == null) {
			recipe.setComments(new ArrayList<>());
		}
		recipe.getComments().add(recipeComment);

		repository.save(recipe);
		return recipeComment;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {
		Optional<RecipeComment> recipeComment = recipeCommentRepository.findById(commentId);

		if (recipeComment.isPresent()) {
			RecipeComment aux = recipeComment.get();

			aux.setComment(comment.getComment());
			recipeCommentRepository.save(aux);
		}
	}

	@Override
	public void deleteComment(String id, String commentId) {
		recipeCommentRepository.deleteById(commentId);
	}

}
