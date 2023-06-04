package md.master.app.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import md.master.app.api.AppError;
import md.master.app.api.PageDto;
import md.master.app.api.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import md.master.app.api.ProductDto;
import md.master.app.core.convertors.ProductConverter;
import md.master.app.core.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Products" , description = "Methods to work with products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @Operation(
            summary = "Request to get page with filtered products",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PageDto.class))
                    )
            }
    )
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "1", name = "p") @Parameter(description = "value for page", required = false, allowEmptyValue = false,example = "1") Integer page,
            @RequestParam(required = false, name = "min_price") @Parameter(description = "value for min_price filter", required = false) Integer minPrice,
            @RequestParam(required = false, name = "max_price") @Parameter(description = "value for max_price filter", required = false) Integer maxPrice,
            @RequestParam(required = false, name = "title_part") @Parameter(description = "value for title filter", required = false) String titlePart
    ) {
        if (page < 1){
            page = 1;
        }
//        Specification<Product> spec = productService.createSpecByFilters(min_price,max_price, titlePart);
//        Page<ProductDto> JpaPage = productService.findAll(spec,page- 1).map(productConverter::entityToDto);
//        PageDto<ProductDto> out = new PageDto<>();
//        out.setPage(JpaPage.getNumber());
//        out.setItems(JpaPage.getContent());
//        out.setTotalPages(JpaPage.getTotalPages());
        return productService.findAll(minPrice, maxPrice, titlePart, page).map(p -> productConverter.entityToDto(p));
    }


    @Operation(
            summary = "Request to get product by id",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "not found", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable @Parameter(description = "product id", required = true, example = "1L") Long id) {
        return productConverter.entityToDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found,id =" + id)));
    }

    @Operation(
            summary = "create new product",
            responses = {
                    @ApiResponse(
                            description = "success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProducts(@RequestBody @Parameter(description = "product dto", required = true) ProductDto productDto){
        return productConverter.entityToDto(productService.createNewProduct(productDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Parameter(description = "product id", required = true, example = "1L") Long id) {
        productService.deleteById(id);
    }
}
