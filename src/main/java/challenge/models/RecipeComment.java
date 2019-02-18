package challenge.models;

/**
 * Classe para mapear o coment�rio da receita no MongoDB
 *
 */
public class RecipeComment {

    private String id;
    private String comment;

    public RecipeComment() {
    }

    public RecipeComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
