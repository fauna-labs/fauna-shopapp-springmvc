package com.parkhomenko.fauna.repository;

import com.faunadb.client.FaunaClient;
import com.faunadb.client.types.Value;
import com.parkhomenko.fauna.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.faunadb.client.query.Language.*;

@Repository
public class ProductRepository {

    private final FaunaClient faunaClient;

    @Autowired
    public ProductRepository(FaunaClient faunaClient) {
        this.faunaClient = faunaClient;
    }

    /*
    Map(
      Paginate(Match(Index("all_Products"))),
      Lambda("product",
        Let(
          {
            productDoc: Get(Var("product"))
          },
          {
            id: Select(["ref", "id"], Var("productDoc")),
            category: Let(
              {
                categoryDoc: Get(Select(["data", "category_id"], Var("productDoc")))
              },
              {
                id: Select(["ref", "id"], Var("categoryDoc")),
                name: Select(["data", "name"], Var("categoryDoc"))
              }
            ),
            name: Select(["data", "name"], Var("productDoc")),
            price: Select(["data", "price"], Var("productDoc")),
            description: Select(["data", "description"], Var("productDoc")),
            quantity: Select(["data", "quantity"], Var("productDoc"))
          }
        )
      )
    )
     */
    public List<Product> allProducts() throws ExecutionException, InterruptedException {
        Value products = faunaClient.query(
                SelectAsIndex(Path("data"),
                        Map(
                                Paginate(Match(Index(Value("all_Products")))),
                                Lambda(
                                        Value("product"),
                                        Let(
                                                "productDoc", Get(Var("product"))
                                        ).in(
                                                Obj(
                                                        Map.of(
                                                            "id", Select(Path("ref", "id"), Var("productDoc")),
                                                            "category", Let(
                                                                    "categoryDoc", Get(
                                                                            Select(Path("data", "category_id"), Var("productDoc"))
                                                                        )
                                                                    ).in(
                                                                            Obj(
                                                                                    "id", Select(Path("ref", "id"), Var("categoryDoc")),
                                                                                    "name", Select(Path("data", "name"), Var("categoryDoc"))
                                                                            )
                                                                    ),
                                                            "name", Select(Path("data", "name"), Var("productDoc")),
                                                            "price", Select(Path("data", "price"), Var("productDoc")),
                                                            "description", Select(Path("data", "description"), Var("productDoc")),
                                                            "quantity", Select(Path("data", "quantity"), Var("productDoc"))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        ).get();

        return List.copyOf(products.collect(Product.class));
    }
}
