package com.parkhomenko.fauna.repository;

import com.faunadb.client.FaunaClient;
import com.faunadb.client.types.Value;
import com.parkhomenko.fauna.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;

@Repository
public class CategoryRepository {

    private final FaunaClient faunaClient;

    @Autowired
    public CategoryRepository(FaunaClient faunaClient) {
        this.faunaClient = faunaClient;
    }

    /*
    FQL statement:
    ==============================================================
    Map(
      Paginate(Match(Index("all_Categories"))),
      Lambda("category", Get(Var("category")))
    )
    ==============================================================
     */
    public List<String> allCategoryNames() throws ExecutionException, InterruptedException {
        Value categories = faunaClient.query(
                SelectAsIndex(Path("data", "data", "name"),
                    Map(
                            Paginate(Match(Index(Value("all_Categories")))),
                            Lambda(Value("category"), Get(Var("category")))
                    )
                )
        ).get();

        return List.copyOf(categories.collect(String.class));
    }

    /*
    FQL statement:
    ==============================================================
    Map(
      Paginate(Match(Index("all_Categories"))),
      Lambda("category",
        Let(
          {
            categoryDoc: Get(Var("category"))
          },
          {
            id: Select(["ref", "id"], Var("categoryDoc")),
            name: Select(["data", "name"], Var("categoryDoc"))
          }
        )
      )
    )
    ==============================================================
     */
    public List<Category> allCategories() throws ExecutionException, InterruptedException {
        Value categories = faunaClient.query(
                SelectAsIndex(Path("data"),
                    Map(
                            Paginate(Match(Index(Value("all_Categories")))),
                            Lambda(
                                    Value("category"),
                                    Let(
                                            "categoryDoc", Get(Var("category"))
                                    ).in(
                                            Obj(
                                                "id", Select(Path("ref", "id"), Var("categoryDoc")),
                                                "name", Select(Path("data", "name"), Var("categoryDoc"))
                                            )
                                    )
                            )
                    )
                )
        ).get();

        return List.copyOf(categories.collect(Category.class));
    }
}
