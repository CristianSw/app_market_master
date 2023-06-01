package md.master.app.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resource not found exception DTO model")
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){super(message);
    }
}
