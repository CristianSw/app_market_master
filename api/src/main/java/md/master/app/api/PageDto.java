package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Page DTO model")
public class PageDto<E> {
    @Schema(description = "List of elements displayed on page")
    private List<E> items;
    @Schema(description = "Page number")
    private int page;
    @Schema(description = "Total page number")
    private int totalPages;

    public PageDto() {
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
