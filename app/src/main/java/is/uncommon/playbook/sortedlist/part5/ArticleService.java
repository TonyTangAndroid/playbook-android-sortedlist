package is.uncommon.playbook.sortedlist.part5;

import java.util.List;

import io.reactivex.Single;
import is.uncommon.playbook.sortedlist.Article;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ArticleService {
    @GET("/articles")
    Single<List<Article>> getArticles(@Query("page") int pageNum,
                                      @Query("pageSize") int pageSize);
}
