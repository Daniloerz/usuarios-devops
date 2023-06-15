package com.example.usuariopowerUp.infrastructure.input.rest;


import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.infrastructure.input.enums.RoleEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final IUsuarioHandler usuarioHandler;


    @Operation(summary = "Add a new owner user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner user created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exist", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content)
    })

    @PostMapping("/{idAdmin}/propietario")
    public ResponseEntity<Void> createOwnerUser(@Parameter(description = "Id usuario administrador")
                                                    @PathVariable Integer idAdmin,
                                                @RequestBody UsuarioRequestDto usuarioRequestDto) {

        usuarioHandler.savePropietario(idAdmin, usuarioRequestDto, RoleEnum.PROPIETARIO.getDbName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new employee user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee user created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exist", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized access", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)

    })
    @PostMapping("/{idPropietario}/empleado")
    public ResponseEntity<Void> createEmployeeUser(@Parameter(description = "Id usuario propietario")
                                                       @PathVariable Integer idPropietario,
                                                  @RequestBody UsuarioRequestDto usuarioRequestDto) {
        usuarioHandler.saveEmpleado(idPropietario, usuarioRequestDto, RoleEnum.EMPLEADO.getDbName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new client user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client user created", content = @Content),
            @ApiResponse(responseCode = "201", description = "User already exist", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/cliente")
    public ResponseEntity<Void> createClientUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        usuarioHandler.saveCliente(usuarioRequestDto, RoleEnum.CLIENTE.getDbName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(schema = @Schema(implementation = UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid petition", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findUsuarioById(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioHandler.findUserById(id));
    }

}