import "@typespec/http";
import "@typespec/openapi3";
import "@typespec/openapi";

using TypeSpec.Http;
using OpenAPI;

@service({
  title: "products",
})
namespace products{

  model ProductDto {
    id: int64;
    name: string;
    description?: string;
    price: decimal;
    available: boolean;
  }

  model ProductCreateRequest {
    name: string;
    description?: string;
    price: decimal;
  }

  model ProductUpdateRequest {
    name: string;
    description?: string;
    price: decimal;
    available: boolean;
  }

  @tag("Product")
  @route("/products")
  interface ProductService {

    @get
    op listProducts(): ProductDto[];

    @get
    @route("/{productId}")
    op getProduct(productId: int64): ProductDto;

    @post
    op createProduct(@body product: ProductCreateRequest): int64;

    @put
    @route("/{productId}")
    op updateProduct(productId: int64, @body product: ProductUpdateRequest): void;

    @delete
    @route("/{productId}")
    op deleteProduct(productId: int64): void;
  }
}